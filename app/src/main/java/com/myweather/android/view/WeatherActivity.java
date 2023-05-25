package com.myweather.android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.myweather.android.R;
import com.myweather.android.gson.AirNow;
import com.myweather.android.gson.Daily;
import com.myweather.android.gson.WeatherForecast;
import com.myweather.android.gson.WeatherNow;
import com.myweather.android.util.ContentUtil;
import com.myweather.android.util.HttpUtil;
import com.myweather.android.util.MultiRequestCallback;
import com.myweather.android.util.ResponseUtil;
import com.myweather.android.util.Utility;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;

    private TextView countyText;
    private TextView degreeText;
    private TextView weatherInfoText;
    private TextView airInfoText;

    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;

    public SwipeRefreshLayout swipeRefresh;
    private String mWeatherID;

    public DrawerLayout drawerLayout;
    private Button navButton;

    private static String countyName;


    public static void setCountyName(String countyName) {
        WeatherActivity.countyName = countyName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherLayout = findViewById(R.id.weather_layout);
        weatherLayout.setVisibility(View.INVISIBLE);

        titleCity =  findViewById(R.id.title_city);
        titleUpdateTime = findViewById(R.id.title_update_time);

        countyText = findViewById(R.id.county_text);
        degreeText = findViewById(R.id.degree_text);
        weatherInfoText = findViewById(R.id.weather_info_text);
        airInfoText = findViewById(R.id.air_info_text);

        forecastLayout = findViewById(R.id.forecast_layout);
        aqiText =  findViewById(R.id.aqi_text);
        pm25Text =  findViewById(R.id.pm25_text);
        comfortText = findViewById(R.id.comfort_text);
        carWashText = findViewById(R.id.car_wash_text);
        sportText = findViewById(R.id.sport_text);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        drawerLayout = findViewById(R.id.drawer_layout);
        navButton = findViewById(R.id.nav_button);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> map = prefs.getAll();
        if (Utility.isAllValueNotNull(map)) {
            // 有缓存时直接解析天气数据
            for (String key: map.keySet()) {
                if (key.equals("county_name")) {
                    countyName = map.get(key).toString();
                    countyText.setText(countyName);
                } else if (key.equals("weather_id")) {
                    mWeatherID = map.get(key).toString();
                }
                else {
                    showWeatherInfo(map.get(key).toString());
                }
            }
            weatherLayout.setVisibility(View.VISIBLE);
        } else {
            // 无缓存时去服务器查询天气
            String weatherID = getIntent().getStringExtra("weather_id");
            requestWeather(weatherID);
        }

        // 手动刷新
        swipeRefresh.setOnRefreshListener(() -> requestWeather(mWeatherID));

        navButton.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    // 根据天气 ID 请求并展示城市天气信息
    public void requestWeather(final String weatherID) {
        mWeatherID = weatherID;

        String weatherNowUrl = "https://devapi.qweather.com/v7/weather/now?location=" +
                weatherID + "&key=" + ContentUtil.API_KEY;
        String airNowUrl = "https://devapi.qweather.com/v7/air/now?location=" +
                weatherID + "&key=" + ContentUtil.API_KEY;
        String weatherForecastUrl = "https://devapi.qweather.com/v7/weather/7d?location=" +
                weatherID + "&key=" + ContentUtil.API_KEY;

        List<String> urlList = new ArrayList<>();
        urlList.add(weatherNowUrl);
        urlList.add(airNowUrl);
        urlList.add(weatherForecastUrl);

        HttpUtil.sendMultiOkHttpRequest(urlList, new MultiRequestCallback() {
            @Override
            public void onResponse(List<Response> responseList) throws IOException {
                for(Response response: responseList) {
                    String responseText = response.body().string();
                    showWeatherInfo(responseText);
                }

                // 保存 城市信息 与 weatherID
                SharedPreferences.Editor editor = PreferenceManager.
                        getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("county_name", countyName);
                editor.putString("weather_id", weatherID);
                editor.apply();

                runOnUiThread(() -> {
                    countyText.setText(countyName);
                    weatherLayout.setVisibility(View.VISIBLE);
                    swipeRefresh.setRefreshing(false);
                });
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    swipeRefresh.setRefreshing(false);
                });
            }
        });
    }

    public void showWeatherInfo(String responseText) {
        if (responseText.contains("feelsLike")) {
            // 实时天气信息
            showWeatherNowInfo(responseText);
        } else if (responseText.contains("air")) {
            // 空气质量信息
            showAirNowInfo(responseText);
        } else if (responseText.contains("daily")) {
            // 天气预测信息
            showWeatherForecastInfo(responseText);
        }
    }

    // 展示城市实时天气信息
    public void showWeatherNowInfo(String responseText) {
        WeatherNow weatherNow =
                ResponseUtil.handleWeatherResponse(responseText, WeatherNow.class);
        runOnUiThread(() -> {
            if (weatherNow != null && weatherNow.status.equals("200")) {
                // 保存实时天气信息
                SharedPreferences.Editor editor = PreferenceManager.
                        getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("weather_now", responseText);
                editor.apply();

                String degree = weatherNow.now.temperature;
                String weatherInfo = weatherNow.now.info;

                degreeText.setText(degree);
                weatherInfoText.setText(weatherInfo);
            } else {
                Toast.makeText(WeatherActivity.this,
                        "获取实时天气信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showAirNowInfo(String responseText) {
        AirNow airNow =
                ResponseUtil.handleWeatherResponse(responseText, AirNow.class);
        runOnUiThread(() -> {
            if (airNow != null && airNow.status.equals("200")) {
                SharedPreferences.Editor editor = PreferenceManager.
                        getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("air_now", responseText);
                editor.apply();

                String airInfo = "  空气" + airNow.now.category;
                airInfoText.setText(airInfo);
            } else {
                Toast.makeText(WeatherActivity.this,
                        "获取空气质量信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 展示城市天气预报信息
    public void showWeatherForecastInfo(String responseText) {
        WeatherForecast weatherForecast =
                ResponseUtil.handleWeatherResponse(responseText, WeatherForecast.class);
        runOnUiThread(() -> {
            if (weatherForecast != null && weatherForecast.status.equals("200")) {
                SharedPreferences.Editor editor = PreferenceManager.
                        getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("weather_forecast", responseText);
                editor.apply();

                forecastLayout.removeAllViews();
                for(int i = 1; i < weatherForecast.weatherForecastList.size(); i++) {
                    Daily weatherForecastItem = weatherForecast.weatherForecastList.get(i);

                    View view = LayoutInflater.from(this).
                            inflate(R.layout.forecast_item, forecastLayout, false);
                    TextView dateText = view.findViewById(R.id.date_text);
                    TextView infoText = view.findViewById(R.id.info_text);
                    TextView maxminText = view.findViewById(R.id.max_min_text);

                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("M月d日");
                    try {
                        Date date = inputDateFormat.parse(weatherForecastItem.fxDate);
                        String dateStr = outputDateFormat.format(date);
                        dateText.setText(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    infoText.setText(weatherForecastItem.textDay);

                    String maxmin = weatherForecastItem.tempMax + " / " + weatherForecastItem.tempMin + "℃";
                    maxminText.setText(maxmin);

                    forecastLayout.addView(view);
                }
            } else {
                Toast.makeText(WeatherActivity.this,
                        "获取天气预测信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}