package org.example;

public class Wind {
    private final Float deg;
    private final Float speed;

    public Wind(Float deg, Float speed) {
        this.deg = deg;
        this.speed = speed;
    }

    public Float getDeg() {
        return deg;
    }

    public Float getSpeed() {
        return speed;
    }
}
