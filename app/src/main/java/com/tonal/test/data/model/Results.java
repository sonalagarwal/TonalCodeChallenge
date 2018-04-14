package com.tonal.test.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sonal on 4/13/18.
 */

public class Results {

    @SerializedName("list")
    @Expose
    private List<WeatherData> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public Results(List<WeatherData> list, City city) {
        this.city = city;
        this.list = list;
    }

    public java.util.List<WeatherData> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public static class Builder {
        private List<WeatherData> list = null;
        private City city;

        public Builder setList(List<WeatherData> list) {
            this.list = list;
            return this;
        }

        public Builder setCity(City city) {
            this.city = city;
            return this;
        }

        public Results build() {
            return new Results(list, city);
        }
    }
}
