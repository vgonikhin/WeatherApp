package ru.gb.android.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE_CITY_POSITION = "bundle_city_position";
    private Spinner spinner;
    private SharedPreferences sPref;

    public static final String CITY_POSITION = "city_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("--------------", "callback oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        Button button = findViewById(R.id.show_weather_button);
        button.setOnClickListener(onClickListener);
        spinner = findViewById(R.id.choosecity_spinner);
        if(savedInstanceState!=null){
            Log.d("--------------", "savedInstance "+savedInstanceState.getInt(BUNDLE_CITY_POSITION));
            spinner.setSelection(0);
        }
        //loadCity();
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.show_weather_button) {
                spinner.setSelection(3);
//                Intent intent = new Intent(MainActivity.this,ShowWeather.class);
//                intent.putExtra(CITY_POSITION, spinner.getSelectedItemPosition());
//                startActivity(intent);
                //saveCity();
            }
        }
    };

    @Override
    protected void onResume() {
        Log.d("--------------", "callback onresume");
        //spinner.setSelection();
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("--------------", "callback osis");
        outState.putInt(BUNDLE_CITY_POSITION,spinner.getSelectedItemPosition());
        super.onSaveInstanceState(outState);
    }

    private void saveCity() {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(CITY_POSITION, spinner.getSelectedItemPosition());
        ed.apply();
    }


    private void loadCity() {
        int position = sPref.getInt(CITY_POSITION, 0);
        spinner.setSelection(position);
    }
}
