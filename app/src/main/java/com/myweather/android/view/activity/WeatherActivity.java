package com.myweather.android.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.myweather.android.R;
import com.myweather.android.gson.AirNow;
import com.myweather.android.gson.Daily;
import com.myweather.android.gson.Hourly;
import com.myweather.android.gson.HourlyForecast;
import com.myweather.android.gson.WeatherForecast;
import com.myweather.android.gson.WeatherNow;
import com.myweather.android.util.ContentUtil;
import com.myweather.android.util.HttpUtil;
import com.myweather.android.util.IconUtil;
import com.myweather.android.util.MultiRequestCallback;
import com.myweather.android.util.ResponseUtil;
import com.myweather.android.util.Utility;
import com.myweather.android.view.AirQualityView;
import com.myweather.android.view.SunArcView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ImageView backImg;

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;

    private TextView countyText;
    private TextView degreeText;
    private TextView weatherInfoText;
    private TextView airInfoText;

    private LinearLayout hourlyForecastLayout;

    private LinearLayout forecastLayout;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;

    private AirQualityView airQualityView;
    private TextView aqiText;
    private TextView aqiInfoText;

    private SunArcView sunArcView;

    public SwipeRefreshLayout swipeRefresh;
    public TextView updateInfoText;

    private String mWeatherID;

    public DrawerLayout drawerLayout;
    private Button navButton;

    private static String countyName;
    private Date sunriseDate;
    private Date sunsetDate;

    public static void setCountyName(String countyName) {
        WeatherActivity.countyName = countyName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        frameLayout = findViewById(R.id.frame_layout);
        backImg = findViewById(R.id.back_image);

        weatherLayout = findViewById(R.id.weather_layout);
        weatherLayout.setVisibility(View.INVISIBLE);

        titleCity =  findViewById(R.id.title_city);
        titleUpdateTime = findViewById(R.id.title_update_time);

        countyText = findViewById(R.id.county_text);
        degreeText = findViewById(R.id.degree_text);
        weatherInfoText = findViewById(R.id.weather_info_text);
        airInfoText = findViewById(R.id.air_info_text);

        hourlyForecastLayout = findViewById(R.id.hourly_forecast_layout);

        forecastLayout = findViewById(R.id.forecast_layout);

        airQualityView = findViewById(R.id.air_quality_view);
        aqiText = findViewById(R.id.aqi_text);
        aqiInfoText = findViewById(R.id.aqi_info_text);

        sunArcView = findViewById(R.id.sun_arc_view);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        updateInfoText = findViewById(R.id.update_info_text);

        drawerLayout = findViewById(R.id.drawer_layout);
        navButton = findViewById(R.id.nav_button);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> map = prefs.getAll();
        if (Utility.isAllValueNotNull(map)) {
            countyName = map.get("county_name").toString();
            requestWeather(map.get("weather_id").toString());
        } else {
            requestWeather(getIntent().getStringExtra("weather_id"));
        }

        // 手动刷新
        swipeRefresh.setOnRefreshListener(() -> {
            requestWeather(mWeatherID);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            String curTime = format.format(calendar.getTime());
            updateInfoText.setText(curTime + "  更新成功");
            updateInfoText.setVisibility(View.VISIBLE);

            new Handler().postDelayed(() -> {
                updateInfoText.setVisibility(View.INVISIBLE);
            }, 2000);
        });

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
        String hourlyForecastUrl = "https://devapi.qweather.com/v7/weather/24h?location=" +
                weatherID + "&key=" + ContentUtil.API_KEY;

        List<String> urlList = new ArrayList<>();
        urlList.add(weatherNowUrl);
        urlList.add(airNowUrl);
        urlList.add(weatherForecastUrl);
        urlList.add(hourlyForecastUrl);

        HttpUtil.sendMultiOkHttpRequest(urlList, new MultiRequestCallback() {
            @Override
            public void onResponse(List<Response> responseList) throws IOException {
                // 调整返回数据的顺序：将天气预报信息置于最前面
                List<String> responseTextList = new ArrayList<>(4);
                for(Response response: responseList) {
                    String responseText = response.body().string();
                    if (responseText.contains("daily")) {
                        responseTextList.add(0, responseText);
                    } else {
                        responseTextList.add(responseText);
                    }
                }

                for(String responseText: responseTextList) {
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
        } else if (responseText.contains("hourly")) {
            // 逐小时预报信息
            showHourlyForecastInfo(responseText);
        }
    }

    // 展示城市实时天气信息
    private void showWeatherNowInfo(String responseText) {
        WeatherNow weatherNow =
                ResponseUtil.handleWeatherResponse(responseText, WeatherNow.class);
        runOnUiThread(() -> {
            if (weatherNow != null && weatherNow.status.equals("200")) {
                String degree = weatherNow.now.temperature;
                String weatherInfo = weatherNow.now.info;
                degreeText.setText(degree);
                weatherInfoText.setText(weatherInfo);

                Date now = new Date();

                if (now.after(sunriseDate) && now.before(sunsetDate)) {
                    // 白天
                    backImg.setImageResource(IconUtil.getDayBack(weatherNow.now.icon));
                    // frameLayout.setBackgroundResource(IconUtil.getDayBack(weatherNow.now.icon));
                } else {
                    // 夜晚
                    // frameLayout.setBackgroundResource(IconUtil.getNightBack(weatherNow.now.icon));
                    backImg.setImageResource(IconUtil.getNightBack(weatherNow.now.icon));
                }
            } else {
                Toast.makeText(WeatherActivity.this,
                        "获取实时天气信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 展示城市天气质量信息
    private void showAirNowInfo(String responseText) {
        AirNow airNow =
                ResponseUtil.handleWeatherResponse(responseText, AirNow.class);
        runOnUiThread(() -> {
            if (airNow != null && airNow.status.equals("200")) {
                String airInfo = "  空气" + airNow.now.category;
                airInfoText.setText(airInfo);

                airQualityView.setCurrentAQI(Integer.parseInt(airNow.now.aqi));
                aqiText.setText("当前 AQI 指数为 " + airNow.now.aqi);
                aqiInfoText.setText(Utility.getAQIText(Integer.parseInt(airNow.now.aqi)).second);

            } else {
                Toast.makeText(WeatherActivity.this,
                        "获取空气质量信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 展示城市天气预报信息
    private void showWeatherForecastInfo(String responseText) {
        WeatherForecast weatherForecast =
                ResponseUtil.handleWeatherResponse(responseText, WeatherForecast.class);
        runOnUiThread(() -> {
            if (weatherForecast != null && weatherForecast.status.equals("200")) {
                Daily weatherToday = weatherForecast.weatherForecastList.get(0);
                sunArcView.setSunriseTime(weatherToday.sunrise);
                sunArcView.setSunsetTime(weatherToday.sunset);

                sunriseDate = Utility.strToDate(weatherToday.sunrise);
                sunsetDate = Utility.strToDate(weatherToday.sunset);

                forecastLayout.removeAllViews();
                for (int i = 1; i < weatherForecast.weatherForecastList.size(); i++) {
                    Daily weatherForecastItem = weatherForecast.weatherForecastList.get(i);

                    View view = LayoutInflater.from(this).
                            inflate(R.layout.forecast_item, forecastLayout, false);
                    TextView dayOfWeekText = view.findViewById(R.id.day_of_week_text);
                    TextView dateText = view.findViewById(R.id.date_text);
                    ImageView iconWeather = view.findViewById(R.id.icon_weather);
                    TextView maxminText = view.findViewById(R.id.max_min_text);

                    try {
                        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = inputDateFormat.parse(weatherForecastItem.fxDate);

                        if(i == 1) {
                            dayOfWeekText.setText("明天");
                        } else {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                            switch (dayOfWeek) {
                                case 0:
                                    dayOfWeekText.setText("周日");
                                    break;
                                case 1:
                                    dayOfWeekText.setText("周一");
                                    break;
                                case 2:
                                    dayOfWeekText.setText("周二");
                                    break;
                                case 3:
                                    dayOfWeekText.setText("周三");
                                    break;
                                case 4:
                                    dayOfWeekText.setText("周四");
                                    break;
                                case 5:
                                    dayOfWeekText.setText("周五");
                                    break;
                                default:
                                    dayOfWeekText.setText("周六");
                            }
                        }

                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("M月d日");
                        dateText.setText(outputDateFormat.format(date));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    iconWeather.setImageResource(IconUtil.getDayIcon(weatherForecastItem.iconDay));

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

    private void showHourlyForecastInfo(String responseText) {
        HourlyForecast hourlyForecast =
                ResponseUtil.handleWeatherResponse(responseText, HourlyForecast.class);
        runOnUiThread(() -> {
            if (hourlyForecast != null && hourlyForecast.status.equals("200")) {
                hourlyForecastLayout.removeAllViews();
                for (Hourly hourlyForecastItem: hourlyForecast.hourlyForecastList) {
                    View view = LayoutInflater.from(this).
                            inflate(R.layout.hourly_forecast_item, hourlyForecastLayout, false);
                    TextView hourText = view.findViewById(R.id.hour_text);
                    ImageView iconHourlyWeather = view.findViewById(R.id.icon_hourly_weather);
                    TextView tempText = view.findViewById(R.id.temp_text);

                    hourText.setText(hourlyForecastItem.fxTime.substring(11, 16));

                    Date current = Utility.strToDate(hourlyForecastItem.fxTime.substring(11, 16));
                    if (current.after(sunriseDate) && current.before(sunsetDate)) {
                        // 白天
                        iconHourlyWeather.setImageResource(IconUtil.getDayIcon(hourlyForecastItem.icon));
                    } else {
                        //夜晚
                        iconHourlyWeather.setImageResource(IconUtil.getNightIcon(hourlyForecastItem.icon));
                    }

                    tempText.setText(hourlyForecastItem.temp + "℃");

                    hourlyForecastLayout.addView(view);
                }

            } else {
                Toast.makeText(WeatherActivity.this,
                        "获取天气预测信息失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
}