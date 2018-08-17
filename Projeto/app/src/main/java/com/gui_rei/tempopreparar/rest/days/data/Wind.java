
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("velocity_min")
    @Expose
    private Integer velocity_min;
    @SerializedName("velocity_max")
    @Expose
    private Integer velocity_max;
    @SerializedName("velocity_avg")
    @Expose
    private Integer velocity_avg;
    @SerializedName("gust_max")
    @Expose
    private Double gust_max;
    @SerializedName("direction_degrees")
    @Expose
    private Integer direction_degrees;
    @SerializedName("direction")
    @Expose
    private String direction;

    public Integer getVelocity_min() {
        return velocity_min;
    }

    public void setVelocity_min(Integer velocity_min) {
        this.velocity_min = velocity_min;
    }

    public Integer getVelocity_max() {
        return velocity_max;
    }

    public void setVelocity_max(Integer velocity_max) {
        this.velocity_max = velocity_max;
    }

    public Integer getVelocity_avg() {
        return velocity_avg;
    }

    public void setVelocity_avg(Integer velocity_avg) {
        this.velocity_avg = velocity_avg;
    }

    public Double getGust_max() {
        return gust_max;
    }

    public void setGust_max(Double gust_max) {
        this.gust_max = gust_max;
    }

    public Integer getDirection_degrees() {
        return direction_degrees;
    }

    public void setDirection_degrees(Integer direction_degrees) {
        this.direction_degrees = direction_degrees;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
