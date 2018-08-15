package br.tp.tp_rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon {
    @SerializedName("name")
    private String nome;

    @SerializedName("stats")
    private ArrayList<Stat> stats = null;

    public Pokemon(String nome, ArrayList<Stat> stats) {
        this.nome = nome;
        this.stats = stats;
    }

    public String getName() {
        return this.nome;
    }

    public ArrayList<Stat> getStats(){
        return this.stats;
    }

}
