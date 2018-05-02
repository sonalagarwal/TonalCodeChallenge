package com.tonal.test.data.model;

/**
 * Created by sonal on 4/13/18.
 */

public class DailyWeather {
    private final long date;
    private double tempMin;
    private double tempMax;
    private int humidityAvg;
    public int viewType;


    public DailyWeather(long date, double min, double max, int humidity) {
        this.date = date;
        this.tempMin = min;
        this.tempMax = max;
        this.humidityAvg = humidity;
    }

    public long getDate() {
        return date;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public int getHumidityAvg() {
        return humidityAvg;
    }

    public void setHumidityAvg(int humidityAvg) {
        this.humidityAvg = humidityAvg;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
