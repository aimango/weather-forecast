package com.weatherforecast.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;

import java.util.List;

public class MainActivity extends Activity {

    private static String TAG = "MainActivity";

    private static String BEST_CITY = "Toronto";
    private static int NUM_DAYS = 5;

    private TextView mCity;
    private ListView mListView;
    private ForecastArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCity = (TextView) findViewById(R.id.city);
        mListView = (ListView) findViewById(R.id.listview);

        new GetWeatherTask(BEST_CITY, NUM_DAYS).execute();
    }

    private class GetWeatherTask extends AsyncTask<String, Void, FiveDayForecast> {

        private int days;
        private String cityName;

        GetWeatherTask(String city, int numDays) {
            cityName = city;
            days = numDays;
        }

        @Override
        protected FiveDayForecast doInBackground(String... params) {
            String responseData = (new WeatherHttpClient()).getWeatherData(cityName, days);
            FiveDayForecast forecastResponse = null;
            if (responseData != null) {
                Log.d(TAG, "Json data is: " + responseData);
                try {
                    forecastResponse = WeatherJsonParser.getWeather(responseData);
                } catch (JSONException e) {
                    Log.e(TAG, "Error message: " + e.getMessage());
                }
            }
            return forecastResponse;
        }

        @Override
        protected void onPostExecute(FiveDayForecast weather) {
            if (weather != null) {
                mCity.setText(getString(
                        R.string.forecast_title, weather.location.city, weather.location.country));

                List<Forecast> objects = weather.days;
                mAdapter = new ForecastArrayAdapter(MainActivity.this, objects);
                mListView.setAdapter(mAdapter);
            } else {
                mCity.setText(R.string.no_connection);
            }
        }
    }
}
