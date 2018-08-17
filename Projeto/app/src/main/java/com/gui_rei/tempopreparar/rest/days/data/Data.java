
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_br")
    @Expose
    private String date_br;
    @SerializedName("humidity")
    @Expose
    private Humidity humidity;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("uv")
    @Expose
    private Uv uv;
    @SerializedName("thermal_sensation")
    @Expose
    private Thermal_sensation thermal_sensation;
    @SerializedName("text_icon")
    @Expose
    private Text_icon text_icon;
    @SerializedName("temperature")
    @Expose
    private Temperature temperature;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_br() {
        return date_br;
    }

    public void setDate_br(String date_br) {
        this.date_br = date_br;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Uv getUv() {
        return uv;
    }

    public void setUv(Uv uv) {
        this.uv = uv;
    }

    public Thermal_sensation getThermal_sensation() {
        return thermal_sensation;
    }

    public void setThermal_sensation(Thermal_sensation thermal_sensation) {
        this.thermal_sensation = thermal_sensation;
    }

    public Text_icon getText_icon() {
        return text_icon;
    }

    public void setText_icon(Text_icon text_icon) {
        this.text_icon = text_icon;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

}
