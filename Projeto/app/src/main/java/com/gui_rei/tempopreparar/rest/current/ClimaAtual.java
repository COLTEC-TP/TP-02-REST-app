package com.gui_rei.tempopreparar.rest.current;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClimaAtual {

    public class Data {
        @SerializedName("temperature")
        @Expose
        private Float temperature;
        @SerializedName("wind_direction")
        @Expose
        private String wind_direction;
        @SerializedName("wind_velocity")
        @Expose
        private Integer wind_velocity;
        @SerializedName("humidity")
        @Expose
        private Integer humidity;
        @SerializedName("condition")
        @Expose
        private String condition;
        @SerializedName("pressure")
        @Expose
        private Integer pressure;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("sensation")
        @Expose
        private Integer sensation;
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
        public Integer getWind_velocity() {
            return wind_velocity;
        }
        public void setWind_velocity(Integer wind_velocity) {
            this.wind_velocity = wind_velocity;
        }
        public Integer getHumidity() {
            return humidity;
        }
        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }
        public String getCondition() {
            return condition;
        }
        public void setCondition(String condition) {
            this.condition = condition;
        }
        public Integer getPressure() {
            return pressure;
        }
        public void setPressure(Integer pressure) {
            this.pressure = pressure;
        }
        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }
        public Integer getSensation() {
            return sensation;
        }
        public void setSensation(Integer sensation) {
            this.sensation = sensation;
        }
        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
}
