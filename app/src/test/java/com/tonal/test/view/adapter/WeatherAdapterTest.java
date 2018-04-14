package com.tonal.test.view.adapter;

import com.tonal.test.data.model.Results;
import com.tonal.test.utils.TestDataGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by sonal on 4/13/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherAdapterTest {

    private static final Results RESULT = TestDataGenerator.generateWeatherDataList(10);

    private WeatherAdapter adapter;

    @Test
    public void test_setLocation() {
        adapter = spy(new WeatherAdapter());
        doNothing().when(adapter).notifyDataSetChanged();

        adapter.setWeatherData(RESULT.getList());

        verify(adapter).notifyDataSetChanged();
    }


    @Test
    public void test_getItemCount() {
        adapter = spy(new WeatherAdapter());
        doNothing().when(adapter).notifyDataSetChanged();
        // empty list to begin
        assertEquals(0, adapter.getItemCount());
        // add 10 items
        adapter.setWeatherData(RESULT.getList());
        assertEquals(10, adapter.getItemCount());
    }
}