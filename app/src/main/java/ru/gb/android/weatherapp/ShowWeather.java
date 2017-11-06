package ru.gb.android.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowWeather extends AppCompatActivity {

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);
        TextView textView = findViewById(R.id.weather_textview);
        Button button = findViewById(R.id.share_weather_button);
        button.setOnClickListener(onClickListener);
        Intent intent = getIntent();
        if(intent != null){
            result = Forecast.getForecast(ShowWeather.this, intent.getIntExtra(MainActivity.CITY_POSITION,0));
            textView.setText(result);
        }
    }
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.share_weather_button) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, result);
                startActivity(intent);
            }
        }
    };
}
