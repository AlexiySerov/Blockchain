package Kusu.Blockchain.leveldb.handler;

import Kusu.Blockchain.leveldb.db.WalletDB;

public class WalletDBHandler implements DBHandlerInterface{

    @Override
    public void post(byte[] hash, byte[] data) {
        WalletDB.getInstance().put(hash, data);
    }

    @Override
    public byte[] get(byte[] hash) {
        return WalletDB.getInstance().get(hash);
    }
}
