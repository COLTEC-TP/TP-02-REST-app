package br.tp.tp_rest;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon {
    @SerializedName("name")
    private String nome;

    @SerializedName("stats")
    private ArrayList<Stat> stats = null;

    @SerializedName("sprites")
    private Sprite sprite = null;

    private Bitmap imagem;

    public Pokemon(String nome, ArrayList<Stat> stats, Sprite sprite) {
        this.nome = nome;
        this.stats = stats;
        this.sprite = sprite;
    }

    public String getName() {
        return this.nome;
    }

    public ArrayList<Stat> getStats(){
        return this.stats;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setBitmap(Bitmap imagem){
        this.imagem = imagem;
    }
}
