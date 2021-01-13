package com.github.woundteries.sdp.weatherapp.weather;

import lombok.Data;

@Data
public class CurrentWeatherRequest {

    String city;
    String countryCode;
    String preferredLang;

}
