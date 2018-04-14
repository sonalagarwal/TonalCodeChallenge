package com.tonal.test.view.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.util.Log;

import com.tonal.test.R;
import com.tonal.test.data.model.CityDailyWeather;
import com.tonal.test.data.model.DailyWeather;
import com.tonal.test.data.model.Temperature;
import com.tonal.test.data.model.WeatherData;
import com.tonal.test.data.repository.WeatherRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherListViewModel extends BaseObservable {

    private static final String TAG = "WeatherListViewModel";

    private WeatherRepository repository;
    public ObservableList weatherData = new ObservableArrayList();
    public final ObservableBoolean isDataLoaded = new ObservableBoolean(false);
    public final ObservableBoolean isDataLoading = new ObservableBoolean(false);
    public final ObservableBoolean emptyViewShowing = new ObservableBoolean(false);
    public final ObservableBoolean errorViewShowing = new ObservableBoolean(false);
    public final ObservableField<String> errorString = new ObservableField<>();
    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> country = new ObservableField<>();
    private Context context;


    public WeatherListViewModel(WeatherRepository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    public void getLatestWeatherData(String location) {

        //clear previous results
        weatherData.clear();

        // reset the states to initial states
        isDataLoading.set(weatherData.isEmpty());
        errorViewShowing.set(false);
        emptyViewShowing.set(false);

        repository.getWeather(location)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(results -> {
                    List<DailyWeather> weatherData = getDailyWeatherStats(results.getList().toArray(new WeatherData[0]));
                    Collections.sort(weatherData,
                            (o1, o2) -> Long.compare(o1.getDate(), o2.getDate()));

                    return new CityDailyWeather(results.getCity(), weatherData);
                })
                .subscribe(new DisposableSingleObserver<CityDailyWeather>() {

                    @Override
                    public void onSuccess(CityDailyWeather cityDailyWeather) {
                        boolean isEmpty = cityDailyWeather == null || cityDailyWeather.getWeather().isEmpty();

                        if (!isEmpty) {
                            weatherData.addAll(cityDailyWeather.getWeather());
                            city.set(cityDailyWeather.getCity().getName());
                            country.set(cityDailyWeather.getCity().getCountry());
                            isDataLoaded.set(true);
                        }

                        emptyViewShowing.set(isEmpty);
                        isDataLoading.set(false);
                        errorViewShowing.set(false);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        errorViewShowing.set(true);
                        isDataLoading.set(false);
                        emptyViewShowing.set(false);
                        isDataLoaded.set(false);


                        errorString.set(getErrorMessage(throwable));
                        throwable.printStackTrace();
                    }
                });
    }

    private String getErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            // We had non-2XX http error
            return context.getString(R.string.error_msg_server);
        }
        if (throwable instanceof IOException) {
            // A network or conversion error happened
            return context.getString(R.string.error_msg_network);
        } else {
            // Generic error handling
            return context.getString(R.string.error_msg_network_generic);
        }
    }


    private List<DailyWeather> getDailyWeatherStats(WeatherData... weatherData) {
        final List<DailyWeather> dailyWeatherData = new ArrayList<>();

        Observable<WeatherData> observable = Observable.fromArray(weatherData);
        // Group the weather data for each day
        Observable<GroupedObservable<Date, WeatherData>> groupedObservable = observable.groupBy(weatherData1 -> {
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(weatherData1.getDt() * 1000); // weather date is in secs - converting into millis
            Log.d(TAG, "Got date = " + cal.getTime().toString());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            return cal.getTime();
        });
        groupedObservable.subscribe(new DisposableObserver<GroupedObservable<Date, WeatherData>>() {
            Map<Date, List<WeatherData>> data = new HashMap<>();

            @Override
            public void onNext(final GroupedObservable<Date, WeatherData> dateWeatherObservable) {
                data.put(dateWeatherObservable.getKey(), new ArrayList<>());

                dateWeatherObservable.subscribe(new DisposableObserver<WeatherData>() {
                    @Override
                    public void onNext(WeatherData weatherData) {
                        Log.d(TAG, "Adding data for key = " + dateWeatherObservable.getKey() + " data = " + weatherData);
                        data.get(dateWeatherObservable.getKey()).add(weatherData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Failed to process weather data", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed processing weather data for = " + dateWeatherObservable.getKey());
                        dailyWeatherData.add(getDailyWeather(dateWeatherObservable.getKey(), data.get(dateWeatherObservable.getKey())));
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Failed to process weather data", e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All weather data processed");
            }
        });

        return dailyWeatherData;
    }

    private DailyWeather getDailyWeather(Date date, List<WeatherData> weatherData) {

        DailyWeather dailyWeather = null;
        Temperature temperature;

        for (WeatherData weather : weatherData) {
            temperature = weather.getMain();

            if (dailyWeather == null) {
                dailyWeather = new DailyWeather(date.getTime(), temperature.getTempMin(), temperature.getTempMax(), temperature.getHumidity());
            } else {
                dailyWeather.setTempMin(Math.min(dailyWeather.getTempMin(), temperature.getTempMin()));
                dailyWeather.setTempMax(Math.max(dailyWeather.getTempMax(), temperature.getTempMax()));
                dailyWeather.setHumidityAvg((dailyWeather.getHumidityAvg() + temperature.getHumidity()) / 2);
            }
        }
        return dailyWeather;
    }
}
