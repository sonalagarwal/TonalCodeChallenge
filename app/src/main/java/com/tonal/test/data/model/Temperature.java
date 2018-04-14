package com.tonal.test.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sonal on 4/13/18.
 */

public class Temperature {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("sea_level")
    @Expose
    private Double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Double grndLevel;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    public Temperature(Double tempMin, Double tempMax, Integer humidity) {
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Double grndLevel) {
        this.grndLevel = grndLevel;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public static class Builder {
        private Double tempMin;
        private Double tempMax;
        private Integer humidity;


        public Builder setMinTemp(Double tempMin) {
            this.tempMin = tempMin;
            return this;
        }

        public Builder setMaxTemp(Double tempMax) {
            this.tempMax = tempMax;
            return this;
        }

        public Builder setHumidity(Integer humidity) {
            this.humidity = humidity;
            return this;
        }

        public Temperature build() {
            return new Temperature(tempMin, tempMax, humidity);
        }
    }
}
