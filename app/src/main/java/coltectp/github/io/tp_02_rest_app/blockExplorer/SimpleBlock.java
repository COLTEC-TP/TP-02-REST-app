package coltectp.github.io.tp_02_rest_app.blockExplorer;

public class SimpleBlock {
    private long height;
    private String hash;
    private long time;
    private boolean mainChain;

    public SimpleBlock(long height, String hash, long time, boolean mainChain) {
        this.height = height;
        this.hash = hash;
        this.time = time;
        this.mainChain = mainChain;
    }

    public long getHeight() {
        return height;
    }

    public String getHash() {
        return hash;
    }

    public long getTime() {
        return time;
    }

    public boolean isMainChain() {
        return mainChain;
    }

    @Override
    public int hashCode () {
        int result = (int) (height ^ (height >>> 32));
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (mainChain ? 1 : 0);
        return result;
    }

}
