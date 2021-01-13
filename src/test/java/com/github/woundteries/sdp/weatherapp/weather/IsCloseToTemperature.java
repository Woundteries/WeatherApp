package com.github.woundteries.sdp.weatherapp.weather;

import lombok.AllArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

@AllArgsConstructor
public class IsCloseToTemperature extends TypeSafeMatcher<Double> {

    private static final Double TEMPERATURE_COMPARING_ERROR = 0.01;

    private Double expectedTemperature;

    @Override
    protected boolean matchesSafely(Double temperature) {
        return Matchers.closeTo(expectedTemperature, TEMPERATURE_COMPARING_ERROR).matches(temperature);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("close to temperature " + expectedTemperature);
    }

    public static Matcher<Double> closeToTemperature(Double expectedTemperature) {
        return new IsCloseToTemperature(expectedTemperature);
    }
}
