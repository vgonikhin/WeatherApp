package ru.gb.android.weatherapp;

import android.content.Context;

public class Forecast {

    static String getCityName(Context context, int position) {
        String[] strings = context.getResources().getStringArray(R.array.cities);
        return strings[position];
    }

    static String getWeatherForecast(Context context, int position) {
        String[] strings = context.getResources().getStringArray(R.array.city_weather);
        return strings[position];
    }

    static String getPressureForecast(Context context, int position){
            String[] strings = context.getResources().getStringArray(R.array.city_pressure);
        return strings[position];
    }

    static String getWindForecast(Context context, int position){
        String[] strings = context.getResources().getStringArray(R.array.city_wind);
        return strings[position];
    }
}
