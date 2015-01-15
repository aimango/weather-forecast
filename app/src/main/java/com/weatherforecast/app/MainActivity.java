package com.weatherforecast.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends Activity {

    private static String TAG = "MainActivity";
    private TextView city, country, maxTemp, minTemp, humid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (TextView) findViewById(R.id.city);
        country = (TextView) findViewById(R.id.country);
        maxTemp = (TextView) findViewById(R.id.temp_max);
        minTemp = (TextView) findViewById(R.id.temp_min);
        humid = (TextView) findViewById(R.id.humidity);
        Log.d(TAG, "onCreate");

        new GetWeatherTask().execute("Toronto");
    }

    private class GetWeatherTask extends AsyncTask<String, Void, WeatherJsonResponse> {

        @Override
        protected WeatherJsonResponse doInBackground(String... params) {
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

            WeatherJsonResponse response;
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();
            Log.d(TAG, "json data " + data);

            response = gson.fromJson(data, WeatherJsonResponse.class);

            return response;
        }

        @Override
        protected void onPostExecute(WeatherJsonResponse weather) {
            city.setText(weather.location.city);
            country.setText(weather.location.country);
            maxTemp.setText(weather.days.get(0).maxTemp + "C");
            minTemp.setText(weather.days.get(0).minTemp + "C");
            humid.setText(weather.days.get(0).humidity + "kPA");
        }
    }
}
