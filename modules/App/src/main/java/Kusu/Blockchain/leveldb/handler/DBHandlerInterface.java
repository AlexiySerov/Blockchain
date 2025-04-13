package Kusu.Blockchain.leveldb.handler;

public interface DBHandlerInterface {

    void post(byte[] hash, byte[] data);

    byte[] get(byte[] hash);
}
