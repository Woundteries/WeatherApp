package com.github.woundteries.sdp.weatherapp.weather.finder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    public Double temp;
    public Double feels_like;
    public Double temp_min;
    public Double temp_max;
    public Integer pressure;
    public Integer humidity;

}
