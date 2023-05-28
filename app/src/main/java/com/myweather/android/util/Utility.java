package com.myweather.android.util;

import android.util.Pair;

import java.util.Map;

public class Utility {

    public static boolean isAllValueNotNull(Map<?, ?> map) {
        if (map.size() == 0) return false;

        for(Object value: map.values()) {
            if (value == null) {
                return false;
            }
        }
        return true;
    }

    public static Pair<String, String> getAQIText(int aqi) {
        String aqiText, aqiInfoText;
        if (aqi <= 50) {
            aqiText = "优";
            aqiInfoText = "空气很好，建议多拥抱自然，呼吸新鲜空气";
        } else if (aqi <= 100) {
            aqiText = "良";
            aqiInfoText = "空气良好，建议适当开展户外活动";
        } else if (aqi <= 150) {
            aqiText = "轻度污染";
            aqiInfoText = "空气轻度污染，建议减少户外活动时间";
        } else if (aqi <= 200) {
            aqiText = "中度污染";
            aqiInfoText = " ";
        } else if (aqi <= 300) {
            aqiText = "重度污染";
            aqiInfoText = " ";
        } else {
            aqiText = "严重污染";
            aqiInfoText = " ";
        }
        return new Pair<String, String>(aqiText, aqiInfoText);
    }
}
