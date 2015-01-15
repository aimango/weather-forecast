package com.weatherforecast.app;


/**
 * Created by aimango on 15-01-14.
 */
public class Forecast {

    float date;

    float humidity;

    Conditions conditions;

    float minTemp;

    public void setDate(float date) {
        this.date = date;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    float maxTemp;

    public static class Conditions {

        public void setMain(String main) {
            this.main = main;
        }

        String main;

        String description;

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
