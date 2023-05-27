package com.myweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Now {

    @SerializedName("temp")
    public String temperature;

    public String icon;

    @SerializedName("text")
    public String info;
}
