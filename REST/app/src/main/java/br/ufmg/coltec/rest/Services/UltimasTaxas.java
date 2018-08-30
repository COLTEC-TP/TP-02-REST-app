package br.ufmg.coltec.rest.Services;

/**
 * Created by a2016951600 on 21/08/18.
 */

public class UltimasTaxas {

    private boolean success;
    private int timestamp;
    private String base;
    private String date;
    private Taxas rates;

    public boolean getSuccess() {
        return success;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Taxas getMoedas() {
        return rates;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMoedas(Taxas moedas) {
        this.rates = moedas;
    }
}


