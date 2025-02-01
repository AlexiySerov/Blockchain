package Kusu.Blockchain.datamodels;

public class Profile {
    String address;
    Long balance;

    public Profile(String address, Long balance){
        this.address = address;
        this.balance = balance;
    }

    public Profile(String address){
        this.address = address;
        this.balance = 0l;
    }
}
