package ru.gb.android.weatherapp;

import android.content.Context;

public class Forecast {
    static String getForecast(Context context, int position) {
        String[] strings = context.getResources().getStringArray(R.array.city_weather);
        String weather = strings[position];
        return weather;
    }
}
