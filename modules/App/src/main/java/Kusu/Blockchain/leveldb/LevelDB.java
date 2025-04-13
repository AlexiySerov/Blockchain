package Kusu.Blockchain.leveldb;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

import java.io.File;
import java.io.IOException;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

public class LevelDB {
    public LevelDB() throws IOException {

        Options options = new Options();
        options.createIfMissing(true);
        DB db = factory.open(new File("/tmp/testdb"), options);

    }



}
