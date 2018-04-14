package com.tonal.test.view.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.tonal.test.data.model.DailyWeather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherItemViewModel extends BaseObservable {

    private final ObservableField<DailyWeather> weatherData = new ObservableField<>();
    public final ObservableField<String> max_temp = new ObservableField<>();
    public final ObservableField<String> min_temp = new ObservableField<>();
    public final ObservableField<String> humidity = new ObservableField<>();
    public final ObservableField<String> day = new ObservableField<>();

    public WeatherItemViewModel() {

        weatherData.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                DailyWeather r = weatherData.get();

                if (r != null) {
                    max_temp.set((int) r.getTempMax() + "\u00b0");
                    min_temp.set((int) r.getTempMin() + "\u00b0");
                    humidity.set(Integer.toString(r.getHumidityAvg()));
                    day.set(getDate(r));
                }
            }
        });
    }

    private String getDate(@NonNull DailyWeather weatherData) {
        Date date = new Date(weatherData.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd", Locale.US);
        return formatter.format(date);
    }

    public void setResult(DailyWeather r) {
        weatherData.set(r);
    }
}