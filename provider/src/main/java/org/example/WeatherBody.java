package org.example;


public class WeatherBody {

    private final Clouds clouds;
    private final Integer id;
    private final Main main;
    private final String name;
    private final Integer timezone;
    private final Weather weather;
    private final Wind wind;

    public WeatherBody(Clouds clouds, Integer id, Main main, String name, Integer timezone, Weather weather, Wind wind) {
        this.clouds = clouds;
        this.id = id;
        this.main = main;
        this.name = name;
        this.timezone = timezone;
        this.weather = weather;
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Integer getId() {
        return id;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public Weather getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }
}
