import Kusu.Blockchain.utils.SHA512;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class SHA512Test {

    String sourceString = "abcdefghigklMNOPRST123098";
    byte[] hash = SHA512.getHash(sourceString);

    String hexString1 = SHA512.byteArrayToHexString(hash);
    byte[] hashFromHexString = SHA512.hexStringToByteArray(hexString1);
    String hexString2 = SHA512.byteArrayToHexString(hashFromHexString);

    public SHA512Test() throws NoSuchAlgorithmException {
    }


    @Test
    public void HexStringCompare() {

        assertEquals(hexString1, hexString2);
    }

    @Test
    public void SourceStringAndHexStringCompare() {

        assertNotEquals(hexString1, sourceString);
        assertNotEquals(hexString2, sourceString);
    }

    @Test
    public void HashCompare() {

        for (int i = 0; i < hash.length-1; i++){
            assertEquals(hash[i], hashFromHexString[i]);
        }
    }

}
