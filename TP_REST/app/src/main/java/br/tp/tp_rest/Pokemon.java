package br.tp.tp_rest;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon {

    //@SerializedName("id")
    private int id;

    @SerializedName("name")
    private String nome;

    @SerializedName("stats")
    private ArrayList<Stat> stats;

    @SerializedName("sprites")
    private Sprite sprite;

    @SerializedName("types")
    private ArrayList<PokeType> types;


    private Bitmap imagem = null;

    public Pokemon(String nome, ArrayList<Stat> stats, Sprite sprite, ArrayList<PokeType> types) {
        this.nome = nome;
        this.stats = stats;
        this.sprite = sprite;
        this.types = types;
    }

    public String getName() {
        return this.nome;
    }

    public ArrayList<Stat> getStats(){
        return this.stats;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public ArrayList<PokeType> getPokeTypes(){
        return this.types;
    }

    public Bitmap getImagem() {
        return this.imagem;
    }

    public void setImagem(Bitmap imagem){
        this.imagem = imagem;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
