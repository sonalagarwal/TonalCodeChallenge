package com.tonal.test.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sonal on 4/13/18.
 */

public class City {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static class Builder {
        private String name;
        private String country;


        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public City build() {
            return new City(name, country);
        }
    }

}
