package br.ufmg.coltec.rest.Services;

import java.util.ArrayList;

/**
 * Created by a2016951600 on 21/08/18.
 */

public class Taxas {
    private float AUD;
    private float BRL;
    private float CAD;
    private float CHF;
    private float CNY;
    private float GBP;
    private float JPY;
    private float USD;


    public float getAUD() {
        return AUD;
    }

    public float getBRL() {
        return BRL;
    }

    public float getCAD() {
        return CAD;
    }

    public float getCHF() {
        return CHF;
    }

    public float getCNY() {
        return CNY;
    }

    public float getGBP() {
        return GBP;
    }

    public float getJPY() {
        return JPY;
    }

    public float getUSD() {
        return USD;
    }

    public void setAUD(float AUD) {
        this.AUD = AUD;
    }

    public void setBRL(float BRL) {
        this.BRL = BRL;
    }

    public void setCAD(float CAD) {
        this.CAD = CAD;
    }

    public void setCHF(float CHF) {
        this.CHF = CHF;
    }

    public void setCNY(float CNY) {
        this.CNY = CNY;
    }

    public void setGBP(float GBP) {
        this.GBP = GBP;
    }

    public void setJPY(float JPY) {
        this.JPY = JPY;
    }

    public void setUSD(float USD) {
        this.USD = USD;
    }

    public ArrayList<Taxa> getTodosOsValores(){

        ArrayList<Taxa> t = new ArrayList<>();

        t.add(new Taxa("AUD",getAUD()));
        t.add(new Taxa("BRL",getBRL()));
        t.add(new Taxa("CAD",getCAD()));
        t.add(new Taxa("CHF",getCHF()));
        t.add(new Taxa("CNY",getCNY()));
        t.add(new Taxa("GBP",getGBP()));
        t.add(new Taxa("JPY",getJPY()));
        t.add(new Taxa("USD",getUSD()));

        return t;


    }

     public ArrayList<Taxa> filtrarProdutos(String busca, ArrayList<Taxa> data) {
        ArrayList<Taxa> filtrados = new ArrayList<>();

        for(int i=0;i<data.size();i++){
            if(data.get(i).getNome().toLowerCase().contains(busca.toLowerCase())){
                filtrados.add(data.get(i));
            }
        }
        return filtrados;

    }

}
