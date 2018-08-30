
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("velocity_min")
    @Expose
    private Float velocity_min;
    @SerializedName("velocity_max")
    @Expose
    private Float velocity_max;
    @SerializedName("velocity_avg")
    @Expose
    private Float velocity_avg;
    @SerializedName("gust_max")
    @Expose
    private Double gust_max;
    @SerializedName("direction_degrees")
    @Expose
    private Float direction_degrees;
    @SerializedName("direction")
    @Expose
    private String direction;

    public Float getVelocity_min() {
        return velocity_min;
    }

    public void setVelocity_min(Float velocity_min) {
        this.velocity_min = velocity_min;
    }

    public Float getVelocity_max() {
        return velocity_max;
    }

    public void setVelocity_max(Float velocity_max) {
        this.velocity_max = velocity_max;
    }

    public Float getVelocity_avg() {
        return velocity_avg;
    }

    public void setVelocity_avg(Float velocity_avg) {
        this.velocity_avg = velocity_avg;
    }

    public Double getGust_max() {
        return gust_max;
    }

    public void setGust_max(Double gust_max) {
        this.gust_max = gust_max;
    }

    public Float getDirection_degrees() {
        return direction_degrees;
    }

    public void setDirection_degrees(Float direction_degrees) {
        this.direction_degrees = direction_degrees;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
