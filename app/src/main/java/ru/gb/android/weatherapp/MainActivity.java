package ru.gb.android.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

//    private static final String BUNDLE_CITY_POSITION = "bundle_city_position";
    private static final String BUNDLE_PRESSURE_CHECKED = "bundle_pressure_checked";
    private static final String BUNDLE_WIND_CHECKED = "bundle_wind_checked";
    public static final String CITY_POSITION = "city_position";
    public static final String PRESSURE_CHECKED = "pressure_checked";
    public static final String WIND_CHECKED = "wind_checked";

    //private Spinner spinner;
    private CheckBox checkBoxPressure;
    private CheckBox checkBoxWind;
    private SharedPreferences sPref;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
//        Button button = findViewById(R.id.show_weather_button);
//        button.setOnClickListener(onClickListener);
//        spinner = findViewById(R.id.choosecity_spinner);
        checkBoxPressure = findViewById(R.id.checkbox_show_pressure);
        checkBoxWind = findViewById(R.id.checkbox_show_wind);
        RecyclerView citiesRecyclerView = findViewById(R.id.cities_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(VERTICAL);
        citiesRecyclerView.setLayoutManager(layoutManager);
        citiesRecyclerView.setAdapter(new MyAdapter());
    }

    //Класс, который содержит в себе все элементы списка
    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cityNameTextView;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.city, parent, false));
            itemView.setOnClickListener(this);
            cityNameTextView = itemView.findViewById(R.id.city_name_text_view);
        }

        void bind(int position) {
            String city = Forecast.getCityName(MainActivity.this,position);
            cityNameTextView.setText(city);
        }

        @Override
        public void onClick(View view) {
            showWeather(this.getLayoutPosition());
        }
    }

    //Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            String[] strings = getResources().getStringArray(R.array.cities);
            return strings.length;
        }
    }

    private void showWeather(int cityPosition) {
        Intent intent = new Intent(MainActivity.this,ShowWeather.class);
        intent.putExtra(CITY_POSITION, cityPosition);
        intent.putExtra(PRESSURE_CHECKED, checkBoxPressure.isChecked());
        intent.putExtra(WIND_CHECKED, checkBoxWind.isChecked());
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        saveCity();
        super.onPause();
    }

    @Override
    protected void onResume() {
        loadCity();
        if(savedInstanceState!=null){
            //spinner.setSelection(savedInstanceState.getInt(BUNDLE_CITY_POSITION));
            checkBoxPressure.setChecked(savedInstanceState.getBoolean(BUNDLE_PRESSURE_CHECKED,false));
            checkBoxWind.setChecked(savedInstanceState.getBoolean(BUNDLE_WIND_CHECKED,false));
        }
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //outState.putInt(BUNDLE_CITY_POSITION,spinner.getSelectedItemPosition());
        outState.putBoolean(BUNDLE_PRESSURE_CHECKED,checkBoxPressure.isChecked());
        outState.putBoolean(BUNDLE_WIND_CHECKED,checkBoxWind.isChecked());
        super.onSaveInstanceState(outState);
    }

    private void saveCity() {
        SharedPreferences.Editor ed = sPref.edit();
        //ed.putInt(CITY_POSITION, spinner.getSelectedItemPosition());
        ed.putBoolean(PRESSURE_CHECKED, checkBoxPressure.isChecked());
        ed.putBoolean(WIND_CHECKED, checkBoxWind.isChecked());
        ed.apply();
    }


    private void loadCity() {
        int position = sPref.getInt(CITY_POSITION, 0);
        //spinner.setSelection(position);
        boolean pressure = sPref.getBoolean(PRESSURE_CHECKED,false);
        checkBoxPressure.setChecked(pressure);
        boolean wind = sPref.getBoolean(WIND_CHECKED,false);
        checkBoxWind.setChecked(wind);
    }
}