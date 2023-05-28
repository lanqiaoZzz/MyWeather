package com.myweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class AirNow {

    @SerializedName("code")
    public String status;

    public Now now;

    public class Now {
        public String aqi;
        public String category;
    }
}
