package com.weatherforecast.app;

class Forecast {

    long date;
    int humidity;
    float minTemp;
    float maxTemp;
    Conditions conditions;

    public void setDate(long date) {
        this.date = date;
    }

    public void setHumidity(int humidity) {
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

    public static class Conditions {
        String main;
        String description;

        public void setMain(String main) {
            this.main = main;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
