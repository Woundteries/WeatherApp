package com.github.woundteries.sdp.weatherapp.weather.finder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class OpenWeatherFinder implements WeatherFinder {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CurrentWeather getCurrentWeather(String city, String countryCode, String lang) {
        String url = buildUrl(city, countryCode, lang);
        CurrentWeather response = restTemplate.getForObject(url, CurrentWeather.class);
        return response;
    }

    private String buildUrl(String city, String countryCode, String lang) {

        return new StringBuilder().append("http://api.openweathermap.org/data/2.5/weather?") //
                .append("q=").append(city).append(",").append(countryCode).append("&") //
                .append("appid=").append(apiKey).append("&") //
                .append("units=metric&") //
                .append("lang=").append(lang) //
                .toString();
    }
}
