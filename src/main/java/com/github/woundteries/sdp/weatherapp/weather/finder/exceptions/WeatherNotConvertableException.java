package com.github.woundteries.sdp.weatherapp.weather.finder.exceptions;

import org.slf4j.helpers.MessageFormatter;

public class WeatherNotConvertableException extends IllegalArgumentException {

    public WeatherNotConvertableException(String parameter) {
        super(MessageFormatter.format("Missing or invalid parameter: {}", parameter).getMessage());
    }
}
