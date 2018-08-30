
package com.gui_rei.tempopreparar.rest.days.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("probability")
    @Expose
    private Float probability;
    @SerializedName("precipitation")
    @Expose
    private Float precipitation;

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public Float getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Float precipitation) {
        this.precipitation = precipitation;
    }

}
