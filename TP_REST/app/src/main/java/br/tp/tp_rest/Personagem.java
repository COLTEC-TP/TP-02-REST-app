package br.tp.tp_rest;

import com.google.gson.annotations.SerializedName;

public class Personagem {
    @SerializedName("name")
    private String nome;


    public Personagem(String nome) {
        this.nome = nome;
    }

    public String getName() {
        return this.nome;
    }

}
