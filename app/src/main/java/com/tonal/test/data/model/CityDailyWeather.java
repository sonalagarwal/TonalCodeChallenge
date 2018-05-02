package com.tonal.test.data.model;

import android.arch.persistence.room.Entity;

import java.util.List;

/**
 * Created by sonal on 4/13/18.
 */

public class CityDailyWeather {

    private City city;
    private List<DailyWeather> weather;

    public CityDailyWeather(City city, List<DailyWeather> weather) {
        this.city = city;
        this.weather = weather;
    }

    public City getCity() {
        return city;
    }

    public List<DailyWeather> getWeather() {
        return weather;
    }
}
