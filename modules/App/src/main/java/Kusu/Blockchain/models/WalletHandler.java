package Kusu.Blockchain.models;

import Kusu.Blockchain.datamodels.Wallet;
import Kusu.Blockchain.datamodels.WalletCreateResponse;
import Kusu.Blockchain.leveldb.handler.WalletDBHandler;
import Kusu.Blockchain.utils.ARCache;
import Kusu.Blockchain.utils.ByteSerialize;
import Kusu.Blockchain.utils.PassPhraseGenerator;
import Kusu.Blockchain.utils.SHA512;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WalletHandler {
    Logger log = Logger.getLogger("WalletHandler");

    private final WalletDBHandler walletDBHandler = new WalletDBHandler();
    private final static ARCache<byte[], Wallet> wallets = new ARCache<>();

    public WalletCreateResponse createWallet() throws NoSuchAlgorithmException {
        String[] passphrase;
        String passphraseStr;

        while (true){
            passphrase = PassPhraseGenerator.generate();
            passphraseStr = PassPhraseGenerator.passphraseToString(passphrase);

            try {
                if (getWallet(passphraseStr) == null) break;
            } catch (Exception e){
                log.log(Level.WARNING,"Exc "+ e.getClass() + e.getMessage());
                break;
            }
        }

        Wallet wallet = new Wallet();
        byte[] hash = SHA512.getHash(Arrays.toString(passphrase));
        walletDBHandler.post(
                hash,
                ByteSerialize.serialize(
                        wallet
                )
        );

        wallets.put(hash, wallet);

        return new WalletCreateResponse(
                SHA512.byteArrayToHexString(hash),
                passphraseStr
        );
    }



    public Wallet getWallet(String walletHash){
        byte[] hash = SHA512.hexStringToByteArray(walletHash);
        Wallet wallet = wallets.get(hash);
        if (wallet == null) {
            wallet = (Wallet) ByteSerialize.deserialize(
                    walletDBHandler.get(
                            SHA512.hexStringToByteArray(walletHash)
                    )
            );
            wallets.put(hash, wallet);
        }

        return wallet;
    }


}
