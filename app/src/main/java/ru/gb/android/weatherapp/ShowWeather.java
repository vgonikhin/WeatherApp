package ru.gb.android.weatherapp;

import android.content.Intent;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowWeather extends AppCompatActivity {

    StringBuilder result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);
        result = new StringBuilder(0);
        TextView textViewCity = findViewById(R.id.city_textview);
        TextView textViewWeather = findViewById(R.id.weather_textview);
        TextView textViewPressure = findViewById(R.id.pressure_textview);
        textViewPressure.setTextSize(Dimension.SP,0);
        TextView textViewWind = findViewById(R.id.wind_textview);
        textViewWind.setTextSize(Dimension.SP,0);
        Button button = findViewById(R.id.share_weather_button);
        button.setOnClickListener(onClickListener);
        Intent intent = getIntent();
        if(intent != null){
            result.setLength(0);
            String[] strings = this.getResources().getStringArray(R.array.cities);
            String cityName = strings[intent.getIntExtra(MainActivity.CITY_POSITION,0)];
            result.append(cityName);
            textViewCity.setText(result);
            result.setLength(0);
            result.append(Forecast.getWeatherForecast(ShowWeather.this, intent.getIntExtra(MainActivity.CITY_POSITION,0)));
            textViewWeather.setText(result);
            result.setLength(0);
            if(intent.getBooleanExtra(MainActivity.PRESSURE_CHECKED,false)) {
                result.append(Forecast.getPressureForecast(ShowWeather.this, intent.getIntExtra(MainActivity.CITY_POSITION,0)));
                textViewPressure.setText(result);
                textViewPressure.setTextSize(Dimension.SP,18);
            }
            result.setLength(0);
            if(intent.getBooleanExtra(MainActivity.WIND_CHECKED,false)) {
                result.append(Forecast.getWindForecast(ShowWeather.this, intent.getIntExtra(MainActivity.CITY_POSITION,0)));
                textViewWind.setText(result);
                textViewWind.setTextSize(Dimension.SP,18);
            }
        }
    }
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.share_weather_button) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, result.toString());
                startActivity(intent);
            }
        }
    };
}
