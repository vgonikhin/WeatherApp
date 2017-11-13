package ru.gb.android.weatherapp;

import android.content.Context;

public class Forecast {
    static String getWeatherForecast(Context context, int position) {
        String[] strings = context.getResources().getStringArray(R.array.city_weather);
        String weather = strings[position];
        return weather;
    }

    static String getPressureForecast(Context context, int position){
            String[] strings = context.getResources().getStringArray(R.array.city_pressure);
            String pressure = strings[position];
            return pressure;
    }

    static String getWindForecast(Context context, int position){
        String[] strings = context.getResources().getStringArray(R.array.city_wind);
        String wind = strings[position];
        return wind;
    }
}
