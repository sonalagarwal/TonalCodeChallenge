package com.tonal.test.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherData {

    @SerializedName("main")
    @Expose
    private Temperature main;
    @SerializedName("weather")
    @Expose
    private List<MainWeather> weather = null;
    @SerializedName("dt")
    @Expose
    private Long dt;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public WeatherData(Temperature main, Long dt) {
        this.main = main;
        this.dt = dt;
    }

    public Long getDt() {
        return dt;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public Temperature getMain() {
        return main;
    }

    public java.util.List<MainWeather> getWeather() {
        return weather;
    }

    public static class Builder {
        private Temperature main;
        private List<MainWeather> weather;
        private String dtTxt;
        private Long date;


        public Builder setMain(Temperature main) {
            this.main = main;
            return this;
        }

        public Builder setDtText(String dtTxt) {
            this.dtTxt = dtTxt;
            return this;
        }

        public Builder setDt(Long dt) {
            this.date = dt;
            return this;
        }

        public WeatherData build() {
            return new WeatherData(main, date);
        }
    }
}