package com.tonal.test.data.repository;

import com.tonal.test.data.model.Results;
import com.tonal.test.data.model.WeatherData;
import com.tonal.test.data.remote.APIService;
import com.tonal.test.utils.APIUtils;

import io.reactivex.Single;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherRepository {
    private APIService service;

    public WeatherRepository(APIService service) {
        this.service = service;
    }

    /* Parse data
     * {
     * "list":[{
     * "dt":1523847600,"main":{"humidity":80,"temp_min":15.28,"temp_max":19.58},
     * "weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}]
     * ,"clouds":{"all":20},"wind":{"speed":2.77,"deg":277.504},"sys":{"pod":"n"},"dt_txt":"2018-04-14 03:00:00"}]
     * ,"city":{"id":420008442,"name":"San Francisco","coord":{"lat":37.7793,"lon":-122.4193},"country":"US"}}
     * */
    public Single<Results> getWeather(String zipCode) {

        return service.getWeatherData(zipCode, APIUtils.getAppId(), "imperial");

    }

    public Single<WeatherData> getTodayWeatherData(String zipCode) {

        return service.getTodaysWeather(zipCode, APIUtils.getAppId(), "imperial");

    }
}
