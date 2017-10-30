package ru.gb.android.weatherapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner spinner;
    private SharedPreferences sPref;

    final String CITY_POSITION = "city_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.weather_textview);
        Button button = (Button) findViewById(R.id.show_weather_button);
        button.setOnClickListener(onClickListener);
        spinner = (Spinner) findViewById(R.id.choosecity_spinner);
        loadCity();
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.show_weather_button) {
                String result = Forecast.getForecast(MainActivity.this, spinner.getSelectedItemPosition());
                textView.setText(result);
                saveCity();
            }
        }
    };

    private void saveCity() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(CITY_POSITION, spinner.getSelectedItemPosition());
        ed.commit();
    }


    private void loadCity() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        int position = sPref.getInt(CITY_POSITION, 0);
        spinner.setSelection(position);
        String result = Forecast.getForecast(MainActivity.this, position);
        textView.setText(result);
    }
}
