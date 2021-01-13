package com.github.woundteries.sdp.weatherapp.weather;

import lombok.Data;

@Data
public class CurrentWeatherResponse {

    String description;
    Temperature temperature;
    Integer pressure;
    Integer humidity;

}
