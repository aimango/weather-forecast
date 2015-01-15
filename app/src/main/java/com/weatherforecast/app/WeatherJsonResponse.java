package com.weatherforecast.app;

import java.util.List;

/**
 * Created by aimango on 15-01-14.
 */

class WeatherJsonResponse {

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDayCnt(int dayCnt) {
        this.dayCnt = dayCnt;
    }

    public void setDays(List<Forecast> days) {
        this.days = days;
    }

    Location location;

    int dayCnt;

    List<Forecast> days;
}
