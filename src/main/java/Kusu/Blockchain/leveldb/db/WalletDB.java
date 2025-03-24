package Kusu.Blockchain.leveldb.db;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

import java.io.File;
import java.io.IOException;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

public class WalletDB {
    private static DB db;

    public static DB getInstance() {
        if (db == null){
            try {
                File dbPath = new File("C:\\Users\\Alexey\\Desktop\\Blockchain\\src\\main\\resources\\db");
                Options options = new Options();

                options.createIfMissing(true);

                db = factory.open(dbPath, options);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return db;
    }


    public static void close() {
        if (db != null){
            try {
                db.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
