
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uv {

    @SerializedName("max")
    @Expose
    private Float max;

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

}
