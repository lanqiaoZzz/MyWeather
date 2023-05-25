package com.myweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecast {

    @SerializedName("code")
    public String status;

    @SerializedName("daily")
    public List<Daily> weatherForecastList;
}
