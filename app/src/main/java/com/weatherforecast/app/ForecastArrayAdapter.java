package com.weatherforecast.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ForecastArrayAdapter extends ArrayAdapter<Forecast> {

    private static String DATE_FORMAT = "MMM dd, yyyy";
    private final Context context;
    private List<Forecast> forecasts;

    public ForecastArrayAdapter(Context context, List<Forecast> forecasts) {
        super(context, R.layout.list_item, forecasts);
        this.context = context;
        this.forecasts = forecasts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        Forecast f = forecasts.get(position);
        ((TextView) rowView.findViewById(R.id.date)).setText(getDate(f.date));
        ((TextView) rowView.findViewById(R.id.temperature)).setText(context.getString(
                R.string.temperature_high_low, Math.round(f.minTemp), Math.round(f.maxTemp)));
        ((TextView) rowView.findViewById(R.id.humidity)).setText(
                context.getString(R.string.humidity, f.humidity));
        ((TextView) rowView.findViewById(R.id.conditions_desc)).setText(
                context.getString(R.string.conditions, f.conditions.description));

        return rowView;
    }

    private String getDate(long unixSeconds) {
        Date date = new Date(unixSeconds * 1000L); // * 1000 to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }
}

