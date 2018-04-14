package com.tonal.test.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tonal.test.R;
import com.tonal.test.data.model.DailyWeather;
import com.tonal.test.databinding.ItemBinding;
import com.tonal.test.view.viewholder.WeatherViewHolder;
import com.tonal.test.view.viewmodel.WeatherItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DailyWeather> weatherData;

    public WeatherAdapter() {
        weatherData = new ArrayList<>();
    }

    public void setWeatherData(@NonNull List<DailyWeather> weatherData) {
        this.weatherData = weatherData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.weather_row_item, parent, false);

        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyWeather data = weatherData.get(position);

        WeatherViewHolder viewHolder = (WeatherViewHolder) holder;
        WeatherItemViewModel viewModel = new WeatherItemViewModel();
        viewModel.setResult(data);
        viewHolder.setItemViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }
}
