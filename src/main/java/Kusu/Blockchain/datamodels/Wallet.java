package Kusu.Blockchain.datamodels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Wallet implements Serializable {

    private byte[] hashCode;
    private Long balance;
    private Map<byte[], Long> currencies;
    private List<byte[]> property;
    private Queue<byte[]> transactionHistory;

    public byte[] getHashCode() {
        return hashCode;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Map<byte[], Long> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<byte[], Long> currencies) {
        this.currencies = currencies;
    }

    public List<byte[]> getProperty() {
        return property;
    }

    public void setProperty(List<byte[]> property) {
        this.property = property;
    }

    public Queue<byte[]> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(Queue<byte[]> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
