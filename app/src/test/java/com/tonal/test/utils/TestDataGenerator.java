package com.tonal.test.utils;

import com.tonal.test.data.model.City;
import com.tonal.test.data.model.DailyWeather;
import com.tonal.test.data.model.Results;
import com.tonal.test.data.model.Temperature;
import com.tonal.test.data.model.WeatherData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    /**
     * Generates a dummy result for weather
     */
    public static DailyWeather generateWeatherResults() {

        return new DailyWeather(1523847600, 19.58, 15.28, 80);
    }

    /**
     * Generates a dummy result for city
     */
    public static City generateCity() {

        return new City("Richmond", "US");

    }

    /**
     * Generates a list of weather data with {count} items
     *
     * @param count - number of dummy results to generate
     */
    public static List<DailyWeather> generateWeatherDataList(int count) {
        List<DailyWeather> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateWeatherResults());
        }

        return list;

    }
}
