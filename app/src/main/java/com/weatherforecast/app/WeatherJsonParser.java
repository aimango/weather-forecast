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

    public static WeatherJsonResponse getWeather(String data) throws JSONException {
        WeatherJsonResponse response = new WeatherJsonResponse();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        Location loc = new Location();

        JSONArray jArr = jObj.getJSONArray("city");

        JSONObject JSONCity = jArr.getJSONObject(0);
        loc.setCity(getString("name", JSONCity));
        loc.setCountry(getString("country", JSONCity));

        JSONArray jArrCoord = JSONCity.getJSONArray("coord");
        JSONObject JSONCoord = jArrCoord.getJSONObject(0); // ?

        loc.setLatitude(getFloat("lat", JSONCoord));
        loc.setLongitude(getFloat("lon", JSONCoord));
        response.setLocation(loc);

        int count = getInt("cnt", jObj);
        response.setDayCnt(count);

        JSONArray jArrList = jObj.getJSONArray("list");
        List<Forecast> days = new ArrayList<Forecast>();
        for (int i = 0; i < count; i++) {
            Forecast f = new Forecast();
            JSONObject day = jArrList.getJSONObject(i);
            f.setDate(getFloat("dt", day));
            f.setHumidity(getFloat("humidity", day));

            // Retrieve max/min temperatures
            JSONObject tempObj = getObject("temp", day);
            f.setMinTemp(getFloat("min", tempObj));
            f.setMaxTemp(getFloat("max", tempObj));

            // Retrieve forecast conditions
            Forecast.Conditions c = new Forecast.Conditions();
            JSONArray weather = day.getJSONArray("weather");
            JSONObject conditionsObj = weather.getJSONObject(0);
            c.setDescription(getString("description", conditionsObj));
            c.setMain(getString("main", conditionsObj));
            f.setConditions(c);
            days.add(f);
        }

        return response;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
