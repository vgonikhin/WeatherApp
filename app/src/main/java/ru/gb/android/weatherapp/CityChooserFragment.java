package ru.gb.android.weatherapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.LinearLayout.VERTICAL;

public class CityChooserFragment extends android.support.v4.app.Fragment {

    private static final String BUNDLE_PRESSURE_CHECKED = "bundle_pressure_checked";
    private static final String BUNDLE_WIND_CHECKED = "bundle_wind_checked";
    public static final String CITY_POSITION = "city_position";
    public static final String PRESSURE_CHECKED = "pressure_checked";
    public static final String WIND_CHECKED = "wind_checked";

    CheckBox checkBoxPressure;
    CheckBox checkBoxWind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_chooser, container, false);

        checkBoxPressure = view.findViewById(R.id.checkbox_show_pressure);
        checkBoxWind = view.findViewById(R.id.checkbox_show_wind);
        RecyclerView citiesRecyclerView = view.findViewById(R.id.cities_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(VERTICAL);
        citiesRecyclerView.setLayoutManager(layoutManager);
        citiesRecyclerView.setAdapter(new CityChooserFragment.MyAdapter());
        return view;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cityNameTextView;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.city, parent, false));
            itemView.setOnClickListener(this);
            cityNameTextView = itemView.findViewById(R.id.city_name_text_view);
        }

        void bind(int position) {
            String city = Forecast.getCityName(CityChooserFragment.this.getActivity(),position);
            cityNameTextView.setText(city);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(CityChooserFragment.this.getActivity(), "clicked", Toast.LENGTH_SHORT).show();
            showWeather(this.getLayoutPosition());
        }
    }

    //Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(CityChooserFragment.this.getActivity());
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
        Intent intent = new Intent(CityChooserFragment.this.getActivity(),ShowWeather.class);
        intent.putExtra(CITY_POSITION, cityPosition);
        intent.putExtra(PRESSURE_CHECKED, checkBoxPressure.isChecked());
        intent.putExtra(WIND_CHECKED, checkBoxWind.isChecked());
        startActivity(intent);
    }

}