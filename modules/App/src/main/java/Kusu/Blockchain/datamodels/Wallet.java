package Kusu.Blockchain.datamodels;

import java.io.Serializable;
import java.util.*;

public class Wallet implements Serializable {

    private Long balance;
    private Map<byte[], Long> currencies;
    private Set<byte[]> properties;
    private Queue<byte[]> transactionHistory;

    private final Object walletLock = new Object();

    public Wallet(){
        this.balance = 0l;
        this.currencies = new HashMap<>();
        this.properties = new HashSet<>();
        this.transactionHistory = new LinkedList<>();
    }

    public Wallet(Long balance, Map<byte[], Long> currencies, Set<byte[]> properties, Queue<byte[]> transactionHistory){
        this.balance = balance;
        this.currencies = currencies;
        this.properties = properties;
        this.transactionHistory = transactionHistory;
    }

    public Long getBalance() {
        synchronized (walletLock) {
            return balance;
        }
    }

    public void deposit(int amount) {
        synchronized (walletLock) {
            this.balance += amount;
        }
    }

    public void withdraw(int amount) {
        synchronized (walletLock) {
            if (this.balance >= amount) {
                this.balance -= amount;
            }
        }
    }

    public Map<byte[], Long> getCurrencies() {
        synchronized (walletLock) {
            return currencies;
        }
    }

    public Long getCurrency(byte[] currency) {
        synchronized (walletLock) {
            return currencies.get(currency);
        }
    }

    public void depositCurrency(byte[] currency, int amount) {
        synchronized (walletLock) {
            currencies.put(currency, currencies.get(currency) + amount);
        }
    }

    public void withdrawCurrency(byte[] currency, int amount) {
        synchronized (walletLock) {
            currencies.put(currency, currencies.get(currency) - amount);
        }
    }

    public Set<byte[]> getProperties() {
        synchronized (walletLock) {
            return properties;
        }
    }

    public void addProperty(byte[] property) {
        synchronized (walletLock) {
            properties.add(property);
        }
    }

    public void removeProperty(byte[] property) {
        synchronized (walletLock) {
            properties.remove(property);
        }
    }

    public Queue<byte[]> getTransactionHistory() {
        synchronized (walletLock) {
            return transactionHistory;
        }
    }

    public void addTransaction(byte[] transaction) {
        synchronized (walletLock) {
            this.transactionHistory.add(transaction);
        }
    }
}
