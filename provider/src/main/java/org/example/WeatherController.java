package org.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/weather")
public class WeatherController {
    @Get(value = "/1851632", produces = "application/json; charset=UTF-8")
    public WeatherBody index() {
        return new WeatherBody(
                new Clouds(2), 1851632,
                new Main(92, 1009, 289.92F, 290.93F, 288.71F),
                "Shuzenji", 32400,
                new Weather("clear sky", "Clear"),
                new Wind(107.538F, 0.47F));
    }
}
