package com.weatherforecast.app;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {

    private static String TAG = "WeatherHttpClient";
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "cnt=%d&units=metric&mode=json&q=%s";

    public String getWeatherData(String location, int days) {
        InputStream is = null;
        HttpURLConnection connection = null;
        try {
            String formatted = String.format(BASE_URL, days, location);
            connection = (HttpURLConnection) new URL(formatted).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            Log.v(TAG, "Connection established.");

            // read the response
            StringBuffer buffer = new StringBuffer();
            is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            is.close();
            connection.disconnect();
            return buffer.toString();
        } catch (IOException e) {
            Log.e(TAG, "IOException message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch(Throwable t) {}
            try {
                connection.disconnect();
            } catch(Throwable t) {}
        }
        return null;
    }


}
