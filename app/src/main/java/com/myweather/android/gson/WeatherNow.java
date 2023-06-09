package com.myweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class WeatherNow {

    @SerializedName("code")
    public String status;

    public Now now;

    public class Now {

        @SerializedName("temp")
        public String temperature;

        public String icon;

        @SerializedName("text")
        public String info;
    }
}
