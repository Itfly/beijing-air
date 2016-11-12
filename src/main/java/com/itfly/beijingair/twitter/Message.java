package com.itfly.beijingair.twitter;

import twitter4j.Status;

import java.time.LocalDateTime;

/**
 * Created by fezho on 11/12/2016.
 */
public class Message {

    private LocalDateTime dateTime;

    private double concentration;

    private int aqi;

    private String definition;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

}
