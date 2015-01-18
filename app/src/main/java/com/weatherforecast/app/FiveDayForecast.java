package com.weatherforecast.app;

import java.util.List;

class FiveDayForecast {

    int dayCnt;
    Location location;
    List<Forecast> days;

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDayCnt(int dayCnt) {
        this.dayCnt = dayCnt;
    }

    public void setDays(List<Forecast> days) {
        this.days = days;
    }

}
