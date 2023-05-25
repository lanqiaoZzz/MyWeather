package com.myweather.android.util;

import java.util.Map;

public class Utility {

    public static boolean isAllValueNotNull(Map<?, ?> map) {
        for(Object value: map.values()) {
            if (value == null) {
                return false;
            }
        }
        return true;
    }
}
