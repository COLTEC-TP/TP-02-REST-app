package br.tp.tp_rest;

import com.google.gson.annotations.SerializedName;

public class Pokemon {
    @SerializedName("name")
    private String nome;

    public Pokemon(String nome) {
        this.nome = nome;
    }

    public String getName() {
        return this.nome;
    }


}
