package com.github.woundteries.sdp.weatherapp.weather;

import com.github.woundteries.sdp.weatherapp.weather.finder.CurrentWeather;
import com.github.woundteries.sdp.weatherapp.weather.finder.Main;
import com.github.woundteries.sdp.weatherapp.weather.finder.Weather;
import com.github.woundteries.sdp.weatherapp.weather.finder.exceptions.WeatherNotConvertableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


class WeatherDataConverterTest {

    private static final Double TEMPERATURE = 4.7;

    @Test
    void convert_validWeatherInput_correctRealTemperature() {

        // given
        WeatherDataConverter converter = new WeatherDataConverter();

        CurrentWeather input = new CurrentWeather();
        input.setWeather(Collections.singletonList(new Weather()));

        Main main = new Main();
        main.setTemp(TEMPERATURE);
        input.setMain(main);

        // when
        CurrentWeatherResponse response = converter.weatherToResponse(input);

        // then
        CurrentWeatherResponseAssert.assertThat(response) //
                .isNotNull() //
                .hasRealTemperature(TEMPERATURE);
    }

    @Test
    void convert_nullInput_throwWeatherNotConvertableExceptionWithInvalidInput() {

        // given
        WeatherDataConverter converter = new WeatherDataConverter();

        // when + then
        Throwable exception = Assertions.assertThrows(WeatherNotConvertableException.class,
                () -> converter.weatherToResponse(null));
        assertThat(exception) //
                .isNotNull() //
                .hasMessage("Missing or invalid parameter: input");
    }

}