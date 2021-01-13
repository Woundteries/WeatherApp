package com.github.woundteries.sdp.weatherapp.weather.finder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

class OpenWeatherFinderTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenWeatherFinder weatherFinder;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCurrentWeather_nullResponse() {
        // given
        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(CurrentWeather.class))).thenReturn(null);

        // when
        CurrentWeather weather = weatherFinder.getCurrentWeather("Warszawa", "pl", "pl");

        // then
        assertThat(weather).isNull();
    }

    @Test
    void getCurrentWeather_validResponse() {
        // given
        CurrentWeather expectedWeather = new CurrentWeather();
        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(CurrentWeather.class))).thenReturn(expectedWeather);

        // when
        CurrentWeather weather = weatherFinder.getCurrentWeather("Warszawa", "pl", "pl");

        // then
        assertThat(weather).isEqualTo(expectedWeather);
    }

    @Test
    void getCurrentWeather_verifyUsedUrl() {
        // given
        String expectedUrl = "http://api.openweathermap.org/data/2.5/weather?q=Warszawa,pl&appid=null&units=metric&lang=pl";

        // expect
        weatherFinder.getCurrentWeather("Warszawa", "pl", "pl");
        Mockito.verify(restTemplate).getForObject(Mockito.eq(expectedUrl), Mockito.eq(CurrentWeather.class));
    }

}