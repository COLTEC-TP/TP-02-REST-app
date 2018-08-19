package coltectp.github.io.tp_02_rest_app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Coin {

    @SerializedName("USD")
    private Price priceUSD;

    @SerializedName("EUR")
    private Price priceEUR;

    @SerializedName("BRL")
    private Price priceBRL;

    public Coin(Price priceUSD, Price priceEUR, Price priceBRL) {
        this.priceUSD = priceUSD;
        this.priceEUR = priceEUR;
        this.priceBRL = priceBRL;
    }

    public Price getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Price priceUSD) {
        this.priceUSD = priceUSD;
    }

    public Price getPriceEUR() {
        return priceEUR;
    }

    public void setPriceEUR(Price priceEUR) {
        this.priceEUR = priceEUR;
    }

    public Price getPriceBRL() {
        return priceBRL;
    }

    public void setPriceBRL(Price priceBRL) {
        this.priceBRL = priceBRL;
    }
}
