package coltectp.github.io.tp_02_rest_app.blockExplorer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Output {
    @SerializedName("n")
    @Expose
    private int n;

    @SerializedName("value")
    @Expose
    private long value;

    @SerializedName("addr")
    @Expose
    private String address;

    @SerializedName("tx_index")
    @Expose
    private long txIndex;

    @SerializedName("script")
    @Expose
    private String script;

    @SerializedName("spent")
    @Expose
    private boolean spent;

    @SerializedName("type")
    @Expose
    private Integer type;

    public Output(int n, long value, String address, long txIndex, String script, boolean spent) {
        this.n = n;
        this.value = value;
        this.address = address;
        this.txIndex = txIndex;
        this.script = script;
        this.spent = spent;
    }

    @Override
    public int hashCode() {
        int result = (int) (value ^ (value >>> 32));
        result = 31 * result + (int) (txIndex ^ (txIndex >>> 32));
        result = 31 * result + (int) (n ^ (n >>> 32));
        result = 31 * result + (script != null ? script.hashCode() : 0);
        return result;
    }

    public int getN() {
        return n;
    }

    public long getValue() {
        return value;
    }

    public String getAddress() {
        return address;
    }

    public long getTxIndex() {
        return txIndex;
    }

    public String getScript() {
        return script;
    }

    public boolean isSpent() {
        return spent;
    }
}
