package Kusu.Blockchain.datamodels;

public class Transaction {
    String addressFrom;
    String addressTo;
    Long value;
    Long time;

    public Transaction(String addressFrom, String addressTo, Long value, Long time){
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.value = value;
        this.time = time;
    }
}
