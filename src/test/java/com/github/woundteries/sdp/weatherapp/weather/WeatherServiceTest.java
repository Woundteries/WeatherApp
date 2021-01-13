package com.github.woundteries.sdp.weatherapp.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.woundteries.sdp.weatherapp.weather.finder.CurrentWeather;
import com.github.woundteries.sdp.weatherapp.weather.finder.Main;
import com.github.woundteries.sdp.weatherapp.weather.finder.Weather;
import com.github.woundteries.sdp.weatherapp.weather.finder.WeatherFinderMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static com.github.woundteries.sdp.weatherapp.weather.IsCloseToTemperature.closeToTemperature;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherServiceTest {

    private static final String DESCRIPTION = "snow";
    private static final Double TEMPERATURE = 4.7;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void resetWeather() {
        WeatherFinderMock.reset();
    }

    @Test
    void getKoszalinWeather_KoszalinWeatherFound_validResponse() throws Exception {

        // given
        WeatherFinderMock.mockWeather("Koszalin", createWeather(DESCRIPTION, TEMPERATURE));

        // when + then
        mvc.perform(MockMvcRequestBuilders.get("/currentweather/v1/koszalin") //
                .accept(MediaType.APPLICATION_JSON)) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.description", equalToIgnoringCase(DESCRIPTION))) //
                .andExpect(jsonPath("$.temperature.real", closeToTemperature(TEMPERATURE)));
    }

    private CurrentWeather createWeather(String description, double temp) {
        CurrentWeather currentWeather = new CurrentWeather();

        Weather weather = new Weather();
        weather.setDescription(description);
        currentWeather.setWeather(Collections.singletonList(weather));

        Main main = new Main();
        main.setTemp(temp);
        currentWeather.setMain(main);

        return currentWeather;
    }

    @Test
    void getCityWeather_cityWeatherFound_validResponse() throws Exception {

        // given
        WeatherFinderMock.mockWeather("Warszawa", createWeather(DESCRIPTION, TEMPERATURE));

        // when + then
        mvc.perform(MockMvcRequestBuilders.post("/currentweather/v1/city") //
                .content(createRequestBody("Warszawa")) //
                .contentType(MediaType.APPLICATION_JSON) //
                .accept(MediaType.APPLICATION_JSON)) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.description", equalToIgnoringCase(DESCRIPTION))) //
                .andExpect(jsonPath("$.temperature.real", closeToTemperature(TEMPERATURE)));
    }

    private String createRequestBody(String city) throws JsonProcessingException {
        CurrentWeatherRequest request = new CurrentWeatherRequest();

        request.setCity(city);
        request.setCountryCode("pl");
        request.setPreferredLang("pl");

        return new ObjectMapper().writeValueAsString(request);
    }
}