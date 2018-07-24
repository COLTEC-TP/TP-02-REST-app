package coltectp.github.io.tp_02_rest_app.blockExplorer;

import java.util.List;

public class Transaction {
    private boolean doubleSpend;
    private long blockHeight;
    private long time;
    private long lockTime;
    private String relayedBy;
    private String hash;
    private long index;
    private int version;
    private long size;
    private List<Input> inputs;
    private List<Output> outputs;

    public boolean isDoubleSpend() {
        return doubleSpend;
    }

    public long getBlockHeight() {
        return blockHeight;
    }

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
