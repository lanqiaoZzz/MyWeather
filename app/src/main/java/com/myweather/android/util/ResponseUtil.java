package com.myweather.android.util;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.myweather.android.db.City;
import com.myweather.android.db.County;
import com.myweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseUtil {

    // 解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);

                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 解析和处理服务器返回的市级数据
    public static boolean handleCityResponse(String response, int provinceID) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);

                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceID(provinceID);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 解析和处理服务器返回的县级数据
    public static boolean handleCountyResponse(String response, int cityID) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();

                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityID);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 将返回的 JSON 数据解析成相应的实体类
    public static <T> T handleWeatherResponse(String response, Class<T> clazz) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String jsonContent = jsonObject.toString();
            return new Gson().fromJson(jsonContent, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
