package com.tonal.test.utils;

/**
 * Created by sonal on 4/13/18.
 */

public class APIUtils {
    private static String baseUrl = "http://api.openweathermap.org/data/2.5/";
    private static String APP_ID = "8164a613b8e6973b5c91067d6a5e1c25";

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getAppId() {
        return APP_ID;
    }
}