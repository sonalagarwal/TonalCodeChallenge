package com.tonal.test.view.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.tonal.test.data.model.DailyWeather;
import com.tonal.test.view.adapter.WeatherAdapter;

import java.util.List;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherDataBinding {
    @BindingAdapter("bind:data")
    public static void setResults(RecyclerView recyclerView, List<DailyWeather> weatherData) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (adapter != null && adapter instanceof WeatherAdapter) {

            ((WeatherAdapter) adapter).setWeatherData(weatherData);

        } else {
            throw new IllegalStateException("RecyclerView either has no adapter set or the "
                    + "adapter isn't of type WeatherAdapter");
        }
    }
}
