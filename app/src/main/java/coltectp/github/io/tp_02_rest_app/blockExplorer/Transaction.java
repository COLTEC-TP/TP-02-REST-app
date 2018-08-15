package coltectp.github.io.tp_02_rest_app.blockExplorer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transaction {

    @SerializedName("time")
    @Expose
    private long time;

    @SerializedName("lock_time")
    @Expose
    private long lockTime;

    @SerializedName("relayed_by")
    @Expose
    private String relayedBy;

    @SerializedName("hash")
    @Expose
    private String hash;

    @SerializedName("tx_index")
    @Expose
    private long index;

    @SerializedName("ver")
    @Expose
    private int version;

    @SerializedName("size")
    @Expose
    private long size;

    @SerializedName("inputs")
    @Expose
    private List<Input> inputs;

    @SerializedName("out")
    @Expose
    private List<Output> outputs;

    public long getTime() {
        return time;
    }

    public long getLockTime() {
        return lockTime;
    }

    public String getRelayedBy() {
        return relayedBy;
    }

    public String getHash() {
        return hash;
    }

    public long getIndex() {
        return index;
    }

    public int getVersion() {
        return version;
    }

    public long getSize() {
        return size;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }


    @Override
    public int hashCode () {
        int result = hash != null ? hash.hashCode() : 0;
        result = 31 * result + (int) (index ^ (index >>> 32));
        result = 31 * result + version;
        result = 31 * result + (int) (size ^ (size >>> 32));
        result = 31 * result + (inputs != null ? inputs.hashCode() : 0);
        result = 31 * result + (outputs != null ? outputs.hashCode() : 0);
        return result;
    }
}
