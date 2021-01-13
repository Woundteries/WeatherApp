package com.github.woundteries.sdp.weatherapp.weather.finder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class WeatherFinderConfiguration {

    @Value("${test.environment:false}")
    private Boolean testEnvironment;

    @Autowired
    private OpenWeatherFinder openWeatherFinder;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public WeatherFinder weatherFinder() {
        if (testEnvironment) {
            log.info("Using mocked WeatherFinder");
            return new WeatherFinderMock();
        } else {
            log.info("Using OpenWeather");
            return openWeatherFinder;
        }
    }

}
