package coltectp.github.io.tp_02_rest_app.blockExplorer;

import java.util.List;

public class Address {
    private String hash160;
    private String address;
    private long totalReceived;
    private long totalSent;
    private long finalBalance;
    private int txCount;
    private List<Transaction> transactions;

    public Address(String hash160, String address, long totalReceived, long totalSent,
                   long finalBalance, int txCount, List<Transaction> transactions) {
        this.hash160 = hash160;
        this.address = address;
        this.totalReceived = totalReceived;
        this.totalSent = totalSent;
        this.finalBalance = finalBalance;
        this.txCount = txCount;
        this.transactions = transactions;
    }

    public String getHash160() {
        return hash160;
    }

    public String getAddress() {
        return address;
    }

    public long getTotalReceived() {
        return totalReceived;
    }

    public long getTotalSent() {
        return totalSent;
    }

    public long getFinalBalance() {
        return finalBalance;
    }

    public int getTxCount() {
        return txCount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public int hashCode () {
        int result = hash160.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + (int) (totalReceived ^ (totalReceived >>> 32));
        result = 31 * result + (int) (totalSent ^ (totalSent >>> 32));
        result = 31 * result + (int) (finalBalance ^ (finalBalance >>> 32));
        return result;
    }
}
