package com.github.woundteries.sdp.weatherapp.weather;

import com.github.woundteries.sdp.weatherapp.weather.finder.CurrentWeather;
import com.github.woundteries.sdp.weatherapp.weather.finder.WeatherFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherService {

    @Autowired
    private WeatherFinder weatherFinder;

    @Autowired
    private WeatherDataConverter converter;

    @RequestMapping(value = "currentweather/v1/koszalin", method = RequestMethod.GET)
    public CurrentWeatherResponse getKoszalinWeather() {
        CurrentWeather foundWeather = weatherFinder.getCurrentWeather("Koszalin", "pl", "pl");
        return converter.weatherToResponse(foundWeather);
    }

    @RequestMapping(value = "currentweather/v1/city", method = RequestMethod.POST)
    public CurrentWeatherResponse getWeather(@RequestBody CurrentWeatherRequest request) {
        CurrentWeather foundWeather = weatherFinder.getCurrentWeather(request.getCity(), request.getCountryCode(), request.getPreferredLang());
        return converter.weatherToResponse(foundWeather);
    }
}
