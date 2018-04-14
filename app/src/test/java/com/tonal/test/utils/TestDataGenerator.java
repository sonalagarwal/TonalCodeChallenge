package com.tonal.test.utils;

import com.tonal.test.data.model.City;
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
    public static WeatherData generateWeatherResults() {

        Temperature temp = new Temperature.Builder().setMaxTemp(15.28).setMinTemp(19.58).setHumidity(80).build();
        return new WeatherData.Builder()
                .setMain(temp)
                .setDt(1523728800l + new Random().nextInt(300000))
                .build();
    }

    /**
     * Generates a dummy result for city
     */
    public static City generateCity() {

        return new City.Builder()
                .setName("Richmond")
                .setCountry("US")
                .build();
    }

    /**
     * Generates a list of weather data with {count} items
     * @param count - number of dummy results to generate
     */
    public static  Results generateWeatherDataList(int count) {
        List<WeatherData> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateWeatherResults());
        }

        return new Results.Builder().setCity(generateCity()).setList(list).build();

    }

    /**
     * Generates a {@link Results} object/
     *
     * @param weatherData - the weatherResults to include in the response object
     * @return {@link Results} with the weatherResults set
     */
    public static Results generateDiscoverGeoResponse(List<WeatherData> weatherData, City cityData) {
        return new Results(weatherData, cityData);
    }
}
