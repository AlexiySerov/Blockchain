package Kusu.Blockchain.datamodels;

public class WalletCreateResponse {
    private final String hash, passphrase;

    public WalletCreateResponse(String hash, String passphrase) {
        this.hash = hash;
        this.passphrase = passphrase;
    }

    public String getHash() {
        return hash;
    }

    public String getPassphrase() {
        return passphrase;
    }
}
