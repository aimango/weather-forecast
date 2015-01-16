package com.weatherforecast.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aimango on 15-01-15.
 */
public class WeatherJsonParser {

    public static FiveDayForecast getWeather(String data) throws JSONException {
        FiveDayForecast response = new FiveDayForecast();

        JSONObject jObj = new JSONObject(data);
        JSONObject jCity = getObject("city", jObj);

        Location loc = new Location();
        loc.setCity(getString("name", jCity));
        loc.setCountry(getString("country", jCity));

        JSONObject jCoord = getObject("coord", jCity);
        loc.setLatitude(getFloat("lat", jCoord));
        loc.setLongitude(getFloat("lon", jCoord));
        response.setLocation(loc);

        int count = getInt("cnt", jObj);
        response.setDayCnt(count);

        JSONArray jForecastList = jObj.getJSONArray("list");
        List<Forecast> days = new ArrayList<Forecast>();
        for (int i = 0; i < count; i++) {
            Forecast forecast = new Forecast();
            JSONObject day = jForecastList.getJSONObject(i);
            forecast.setDate(getFloat("dt", day));
            forecast.setHumidity(getFloat("humidity", day));

            // Retrieve max/min temperatures
            JSONObject tempObj = getObject("temp", day);
            forecast.setMinTemp(getFloat("min", tempObj));
            forecast.setMaxTemp(getFloat("max", tempObj));

            // Retrieve forecast conditions
            Forecast.Conditions conditions = new Forecast.Conditions();
            JSONArray weather = day.getJSONArray("weather");
            JSONObject conditionsObj = weather.getJSONObject(0);
            conditions.setDescription(getString("description", conditionsObj));
            conditions.setMain(getString("main", conditionsObj));
            forecast.setConditions(conditions);
            days.add(forecast);
        }
        response.setDays(days);

        return response;
    }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
