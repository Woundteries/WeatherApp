package com.github.woundteries.sdp.weatherapp.weather.finder;

import java.util.HashMap;
import java.util.Map;

public class WeatherFinderMock implements WeatherFinder {

    private static Map<String, CurrentWeather> knownWeather = new HashMap<>();

    public static void mockWeather(String city, CurrentWeather weather) {
        knownWeather.put(city, weather);
    }

    public static void reset() {
        knownWeather = new HashMap<>();
    }

    @Override
    public CurrentWeather getCurrentWeather(String city, String countryCode, String lang) {
        return knownWeather.get(city);
    }
}
