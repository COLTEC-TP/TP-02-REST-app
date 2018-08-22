package br.ufmg.coltec.rest.Services;

/**
 * Created by a2016951600 on 21/08/18.
 */

public class TaxaHistorica {

    private boolean success;
    private boolean historical;
    private String date;
    private int timestamp;
    private String base;
    private Taxas rates;

    public boolean getSuccess() {
        return success;
    }

    public boolean getHistorical() {
        return historical;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() { return date; }

    public Taxas getMoedas() { return rates; }

    public void setSuccess(boolean success) { this.success = success; }

    public void setHistorical(boolean historical) { this.historical = historical; }

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
