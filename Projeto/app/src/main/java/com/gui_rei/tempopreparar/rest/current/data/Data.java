package com.gui_rei.tempopreparar.rest.current.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("temperature")
    @Expose
    private Float temperature;
    @SerializedName("wind_direction")
    @Expose
    private String wind_direction;
    @SerializedName("wind_velocity")
    @Expose
    private Float wind_velocity;
    @SerializedName("humidity")
    @Expose
    private Float humidity;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("pressure")
    @Expose
    private Float pressure;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sensation")
    @Expose
    private Float sensation;
    @SerializedName("date")
    @Expose
    private String date;

    public Float getTemperature() {
        return temperature;
    }
    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }
    public String getWind_direction() {
        return wind_direction;
    }
    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }
    public Float getWind_velocity() {
        return wind_velocity;
    }
    public void setWind_velocity(Float wind_velocity) {
        this.wind_velocity = wind_velocity;
    }
    public Float getHumidity() {
        return humidity;
    }
    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public Float getPressure() {
        return pressure;
    }
    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Float getSensation() {
        return sensation;
    }
    public void setSensation(Float sensation) {
        this.sensation = sensation;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
