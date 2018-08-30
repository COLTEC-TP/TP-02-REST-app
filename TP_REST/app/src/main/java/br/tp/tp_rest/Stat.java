package br.tp.tp_rest;

import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("base_stat")
    private int base_stat;

    public Stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getBase_stat() {
        return this.base_stat;
    }

}
