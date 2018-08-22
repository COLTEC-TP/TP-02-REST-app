package br.tp.tp_rest;

import com.google.gson.annotations.SerializedName;

public class NameOfPokeType {

    @SerializedName("name")
    private String nameOfType;

    public NameOfPokeType(String type) {
        this.nameOfType = type;
    }

    public String getPokeType() {
        return this.nameOfType;
    }


}
