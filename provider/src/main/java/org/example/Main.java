package org.example;

public class Main {
    private final Integer humidity;
    private final Integer pressure;
    private final Float temp;
    private final Float temp_max;
    private final Float temp_min;

    public Main(Integer humidity, Integer pressure, Float temp, Float temp_max, Float temp_min) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Float getTemp() {
        return temp;
    }

    public Float getTemp_max() {
        return temp_max;
    }

    public Float getTemp_min() {
        return temp_min;
    }
}
