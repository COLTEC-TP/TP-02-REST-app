package br.tp.tp_rest;

import com.google.gson.annotations.SerializedName;

public class PokeType {

    @SerializedName("type")
    private NameOfPokeType type;

    public PokeType(NameOfPokeType type) {
        this.type = type;
    }

    public String getNamePokeType() {
        return this.type.getPokeType();
    }

}
