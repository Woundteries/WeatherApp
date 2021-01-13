package com.github.woundteries.sdp.weatherapp.weather;

import org.assertj.core.api.AbstractAssert;

public class CurrentWeatherResponseAssert extends AbstractAssert<CurrentWeatherResponseAssert, CurrentWeatherResponse> {

    private static final Double TEMPERATURE_COMPARING_ERROR = 0.01;

    public CurrentWeatherResponseAssert(CurrentWeatherResponse currentWeatherResponse) {
        super(currentWeatherResponse, CurrentWeatherResponseAssert.class);
    }

    public static CurrentWeatherResponseAssert assertThat(CurrentWeatherResponse actual) {
        return new CurrentWeatherResponseAssert(actual);
    }

    public CurrentWeatherResponseAssert hasRealTemperature(Double expectedTemp) {
        isNotNull();

        Double realTemp = actual.getTemperature().getReal();
        Double difference = Math.abs(realTemp - expectedTemp);

        if (difference > TEMPERATURE_COMPARING_ERROR) {
            failWithMessage("Temperature {} is not close to the expected one: {}",
                    realTemp, expectedTemp);
        }
        return this;
    }
}
