package com.myweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HourlyForecast {

    @SerializedName("code")
    public String status;

    @SerializedName("hourly")
    public List<Hourly> hourlyForecastList;
}
