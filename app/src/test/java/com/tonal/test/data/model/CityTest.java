package com.tonal.test.data.model;

import com.tonal.test.utils.TestDataGenerator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by sonal on 4/13/18.
 */

public class CityTest {

    @Test
    public void test_EmptyConstructor() {
        City city = new City(null,null);
        assertNotNull(city);
        assertNull(city.getName());
        assertNull(city.getCountry());
    }

    @Test
    public void test_Constructor() {
        City city = new City("Richmond","US");
        assertNotNull(city);
        assertNotNull(city.getName());
        assertNotNull(city.getCountry());
        assertEquals("Richmond", city.getName());
        assertEquals("US", city.getCountry());
    }
    @Test
    public void test_getName() {
        City city = TestDataGenerator.generateCity();
        assertNotNull(city.getName());
    }

    @Test
    public void test_getCountry() {
        City city = TestDataGenerator.generateCity();
        assertNotNull(city.getCountry());
    }
}
