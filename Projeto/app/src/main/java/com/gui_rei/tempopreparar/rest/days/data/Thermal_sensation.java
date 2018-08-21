
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thermal_sensation {

    @SerializedName("min")
    @Expose
    private Float min;
    @SerializedName("max")
    @Expose
    private Float max;

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

}
