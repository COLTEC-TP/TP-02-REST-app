
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uv {

    @SerializedName("max")
    @Expose
    private Integer max;

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

}
