package coltectp.github.io.tp_02_rest_app.blockExplorer;

public class Output {
    private int n;
    private long value;
    private String address;
    private long txIndex;
    private String script;
    private boolean spent;
    private boolean spentToAddress;

    public Output(int n, long value, String address, long txIndex, String script, boolean spent, boolean spentToAddress) {
        this.n = n;
        this.value = value;
        this.address = address;
        this.txIndex = txIndex;
        this.script = script;
        this.spent = spent;
        this.spentToAddress = spentToAddress;
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

    public boolean isSpentToAddress() {
        return spentToAddress;
    }
}
