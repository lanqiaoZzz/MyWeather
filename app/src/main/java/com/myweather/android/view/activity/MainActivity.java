package com.myweather.android.view.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.myweather.android.R;
import com.myweather.android.util.Utility;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("weather_id", null) != null) {
            // 之前选择过城市
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }

//        Map<String, ?> map = prefs.getAll();
//        if (Utility.isAllValueNotNull(map)) {
//            Intent intent = new Intent(this, WeatherActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }
}
