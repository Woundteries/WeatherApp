package com.github.woundteries.sdp.weatherapp.weather.finder;

public interface WeatherFinder {

    CurrentWeather getCurrentWeather(String city, String countryCode, String lang);

}
