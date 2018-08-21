
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("min")
    @Expose
    private Float min;
    @SerializedName("max")
    @Expose
    private Float max;
    @SerializedName("morning")
    @Expose
    private Morning morning;
    @SerializedName("afternoon")
    @Expose
    private Afternoon afternoon;
    @SerializedName("night")
    @Expose
    private Night night;

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public Morning getMorning() {
        return morning;
    }

    public void setMorning(Morning morning) {
        this.morning = morning;
    }

    public Afternoon getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(Afternoon afternoon) {
        this.afternoon = afternoon;
    }

    public Night getNight() {
        return night;
    }

    public void setNight(Night night) {
        this.night = night;
    }

}
