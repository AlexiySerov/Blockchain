package Kusu.Blockchain.utils;

import Kusu.Blockchain.datamodels.Wallet;
import Kusu.Blockchain.leveldb.handler.WalletDBHandler;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WalletHandler {

    private final WalletDBHandler walletDBHandler = new WalletDBHandler();

    public String createWallet() throws NoSuchAlgorithmException {
        String[] passphrase = PassPhraseGenerator.generate();
        String passphraseStr = Arrays.toString(PassPhraseGenerator.generate());

        /*
        while (true){
            passphrase = PassPhraseGenerator.generate();
            passphraseStr = Arrays.toString(PassPhraseGenerator.generate());

            try {
                if (getWallet(passphraseStr) == null) break;
            } catch (NullPointerException e){
                System.out.println("Exc "+ e.getClass() + e.getMessage() );
                e.printStackTrace();
                break;
            }
        }*/

        walletDBHandler.post(
                SHA512.getHash(Arrays.toString(passphrase)),
                ByteSerialize.serialize(
                        new Wallet()
                )
        );

        return SHA512.byteArrayToHexString(SHA512.getHash(Arrays.toString(passphrase)));
    }



    public Wallet getWallet(String walletHash){

        return (Wallet) ByteSerialize.deserialize(
                walletDBHandler.get(
                        SHA512.hexStringToByteArray(walletHash)
                )
        ) ;
    }


}
