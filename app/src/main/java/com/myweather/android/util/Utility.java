package com.myweather.android.util;

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
}
