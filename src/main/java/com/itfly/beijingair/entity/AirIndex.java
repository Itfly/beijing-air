package com.itfly.beijingair.entity;

import java.time.LocalDateTime;

/**
 * Created by fezho on 11/12/2016.
 */
public class AirIndex {

    private int timestamp;

    private double concentration;

    private int aqi;

    private String definition;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "AirIndex{" +
                "timestamp=" + timestamp +
                ", concentration=" + concentration +
                ", aqi=" + aqi +
                ", definition='" + definition + '\'' +
                '}';
    }
}
