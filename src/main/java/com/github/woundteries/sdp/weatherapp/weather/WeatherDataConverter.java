package com.github.woundteries.sdp.weatherapp.weather;

import com.github.woundteries.sdp.weatherapp.weather.finder.CurrentWeather;
import com.github.woundteries.sdp.weatherapp.weather.finder.exceptions.WeatherNotConvertableException;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataConverter {

    public CurrentWeatherResponse weatherToResponse(CurrentWeather input) {
        validateInput(input);
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDescription(input.getWeather().get(0).getDescription());

        Temperature temperature = new Temperature();
        temperature.setReal(input.getMain().getTemp());
        temperature.setFeelsLike(input.getMain().getFeels_like());
        temperature.setMaximal(input.getMain().getTemp_max());
        temperature.setMinimal(input.getMain().getTemp_min());
        response.setTemperature(temperature);

        response.setPressure(input.getMain().getPressure());
        response.setHumidity(input.getMain().getHumidity());

        return response;
    }

    private void validateInput(CurrentWeather input) {
        if (input == null) {
            throw new WeatherNotConvertableException("input");
        } else if (input.getWeather() == null || input.getWeather().size() < 1) {
            throw new WeatherNotConvertableException("weather");
        } else if (input.getMain() == null) {
            throw new WeatherNotConvertableException("main");
        }
    }
}
