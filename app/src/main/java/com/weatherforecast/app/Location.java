package com.weatherforecast.app;

/**
 * Created by aimango on 15-01-14.
 */
class Location {

    String country;

    String city;

    float lat;

    float lon;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(float lat) {
        this.lat = lat;
    }

    public void setLongitude(float lon) {
        this.lon = lon;
    }

}