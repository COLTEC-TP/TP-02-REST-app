package coltectp.github.io.tp_02_rest_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("last")
    @Expose
    private Float last;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    public Price(Float last, String symbol) {
        this.last = last;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getLast() {
        return last;
    }

    public void setLast(Float last) {
        this.last = last;
    }
}
