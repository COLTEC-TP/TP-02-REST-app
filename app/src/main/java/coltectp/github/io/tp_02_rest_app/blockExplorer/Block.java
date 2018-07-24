package coltectp.github.io.tp_02_rest_app.blockExplorer;

import java.util.List;

public class Block extends SimpleBlock {
    private int version;
    private String previousBlockHash;
    private String merkleRoot;
    private long bits;
    private long fees;
    private long nonce;
    private long size;
    private long index;
    private long receivedTime;
    private String relayedBy;
    private List<Transaction> transactions;

    public Block(long height, String hash, long time, boolean mainChain, int version,
                 String previousBlockHash, String merkleRoot, long bits, long fees, long nonce, long size,
                 long index, long receivedTime, String relayedBy, List<Transaction> transactions) {

        super(height, hash, time, mainChain);
        this.version = version;
        this.previousBlockHash = previousBlockHash;
        this.merkleRoot = merkleRoot;
        this.bits = bits;
        this.fees = fees;
        this.nonce = nonce;
        this.size = size;
        this.index = index;
        this.receivedTime = receivedTime;
        this.relayedBy = relayedBy;
        this.transactions = transactions;
    }

    public int getVersion() {
        return version;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public long getBits() {
        return bits;
    }

    public long getFees() {
        return fees;
    }

    public long getNonce() {
        return nonce;
    }

    public long getSize() {
        return size;
    }

    public long getIndex() {
        return index;
    }

    public long getReceivedTime() {
        return receivedTime;
    }

    public String getRelayedBy() {
        return relayedBy;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public int hashCode () {
        int result = version;
        result = 31 * result + previousBlockHash.hashCode();
        result = 31 * result + merkleRoot.hashCode();
        result = 31 * result + (int) (fees ^ (fees >>> 32));
        result = 31 * result + (int) (nonce ^ (nonce >>> 32));
        result = 31 * result + (int) (size ^ (size >>> 32));
        result = 31 * result + (int) (index ^ (index >>> 32));
        return result;
    }
}
