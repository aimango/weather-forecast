package com.weatherforecast.app.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by aimango on 15-01-14.
 */
@JsonType
public class Location {

    @JsonField(fieldName = "coord")
    Coordinates coord;

    @JsonField(fieldName = "country")
    String country;

    @JsonField(fieldName = "name")
    String city;

    @JsonType
    class Coordinates {
        @JsonField(fieldName = "lat")
        String lat;

        @JsonField(fieldName = "lon")
        String lon;
    }
}