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

//    private static String offlineData = "{\"cod\":\"200\",\"message\":0.2645,\"mCity\":{\"id\":6167865,\"name\":\"Toronto\",\"coord\":{\"lon\":-79.416298,\"lat\":43.700111},\"country\":\"CA\",\"population\":0,\"sys\":{\"population\":0}},\"cnt\":5,\"list\":[{\"dt\":1421254800,\"temp\":{\"day\":-20.61,\"min\":-21.55,\"max\":-20.61,\"night\":-21.55,\"eve\":-20.61,\"morn\":-20.61},\"pressure\":1003.95,\"humidity\":54,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01n\"}],\"speed\":1.01,\"deg\":19,\"clouds\":0},{\"dt\":1421341200,\"temp\":{\"day\":-8.74,\"min\":-20.73,\"max\":-5.31,\"night\":-5.31,\"eve\":-8.78,\"morn\":-20.73},\"pressure\":994.64,\"humidity\":76,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":3.46,\"deg\":215,\"clouds\":48,\"snow\":1.25},{\"dt\":1421427600,\"temp\":{\"day\":-11.02,\"min\":-20.33,\"max\":-8.36,\"night\":-20.33,\"eve\":-15.88,\"morn\":-8.36},\"pressure\":1000.47,\"humidity\":43,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":3.87,\"deg\":313,\"clouds\":0},{\"dt\":1421514000,\"temp\":{\"day\":-10.5,\"min\":-16.54,\"max\":-0.79,\"night\":-0.79,\"eve\":-4.12,\"morn\":-16.54},\"pressure\":997.35,\"humidity\":70,\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}],\"speed\":4.91,\"deg\":114,\"clouds\":92,\"snow\":2},{\"dt\":1421600400,\"temp\":{\"day\":2.65,\"min\":-2.36,\"max\":2.65,\"night\":-2.36,\"eve\":0.97,\"morn\":2.39},\"pressure\":999.39,\"humidity\":0,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":8.14,\"deg\":242,\"clouds\":99,\"rain\":1.48,\"snow\":0.16}]}";

    private TextView mCity;

    private ListView mListView;
    private ForecastArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCity = (TextView) findViewById(R.id.city);
        mListView = (ListView) findViewById(R.id.listview);

        new GetWeatherTask().execute("Toronto");
    }

    private class GetWeatherTask extends AsyncTask<String, Void, FiveDayForecast> {

        @Override
        protected FiveDayForecast doInBackground(String... params) {
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));
            FiveDayForecast response = null;
            Log.d(TAG, "json data is: " + data);
            try {
                response = WeatherJsonParser.getWeather(data);
            } catch (JSONException e) {
                Log.e(TAG, "Error message: " + e.getMessage());
            }
            return response;
        }

        @Override
        protected void onPostExecute(FiveDayForecast weather) {
            if (weather != null) {
                mCity.setText("Forecast for " + weather.location.city + ", " + weather.location.country);

                List<Forecast> objects = weather.days;
                mAdapter = new ForecastArrayAdapter(MainActivity.this, objects);
                mListView.setAdapter(mAdapter);
            }
        }
    }
}
