package br.tp.tp_rest;

import com.google.gson.annotations.SerializedName;

public class Sprite {

    @SerializedName("front_default")
    private String url;

    public Sprite(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
}
