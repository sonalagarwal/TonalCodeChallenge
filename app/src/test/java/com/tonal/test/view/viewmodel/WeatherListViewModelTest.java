package com.tonal.test.view.viewmodel;

import android.content.Context;

import com.tonal.test.data.model.Results;
import com.tonal.test.data.repository.WeatherRepository;
import com.tonal.test.utils.TestDataGenerator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherListViewModelTest {
    private static final Results RESULT = TestDataGenerator.generateWeatherDataList(10);;
    private static final String zipcode = "94804, us";

    @Mock
    private WeatherRepository mRepository;

    @Mock
    private Context mContext;

    private WeatherListViewModel mViewModel;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // setup context
        setupContext();
        mViewModel = new WeatherListViewModel(mRepository,mContext);
    }

    private void setupContext() {
        Mockito.when(mContext.getApplicationContext()).thenReturn(mContext);
    }


    /**
     * Test that the call to get results returns without error
     */
    @Test
    public void getResultsWithoutError() {
        // given the following zipcode
        Mockito.when(mRepository.getWeather(zipcode)).thenReturn(Single.just(RESULT));

        // find latest weather data
        mViewModel.getLatestWeatherData(zipcode);

        // verify that the repository is called
        Mockito.verify(mRepository).getWeather(zipcode);

        // test that the loading indicator is hidden
        assertFalse(mViewModel.isDataLoading.get());

        // check that the empty view is hidden
        assertFalse(mViewModel.emptyViewShowing.get());

        // check that the error view is hidden
        assertFalse(mViewModel.errorViewShowing.get());

        assertTrue(mViewModel.weatherData.size() == RESULT.getList().size());
    }

    @Test
    public void getResultsThrowsError() {
        Mockito.when(mRepository.getWeather(zipcode)).thenReturn(
                Single.<Results>error(new TimeoutException()));

        // request weather data
        mViewModel.getLatestWeatherData(zipcode);

        Mockito.verify(mRepository).getWeather(zipcode);

        // check that empty view is hidden
        assertFalse(mViewModel.emptyViewShowing.get());

        // check that loading view is hidden
        assertFalse(mViewModel.isDataLoading.get());

        // check that error view is showing
        assertTrue(mViewModel.errorViewShowing.get());
    }
}
