package com.github.woundteries.sdp.weatherapp.weather.finder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {

    public List<Weather> weather;
    public Main main;

}
