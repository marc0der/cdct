package org.example;

public class Weather {

    private final String description;
    private final String main;

    public Weather(String description, String main) {
        this.description = description;
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public String getMain() {
        return main;
    }
}
