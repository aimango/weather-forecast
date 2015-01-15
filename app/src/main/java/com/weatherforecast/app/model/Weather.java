package com.weatherforecast.app.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by aimango on 15-01-14.
 */
@JsonType
public class Weather {
//
//    @JsonField(fieldName = "location")
//    Location location;

    @JsonField(fieldName = "condition")
    Conditions condition;

    @JsonField(fieldName = "date")
    float date;

    @JsonField(fieldName = "temp_min")
    float minTemp;

    @JsonField(fieldName = "temp_max")
    float maxTemp;

    @JsonField(fieldName = "humidity")
    float humidity;

    @JsonType
    public class Conditions {

        @JsonField(fieldName = "main")
        String main;

        @JsonField(fieldName = "description")
        String description;
    }


}
