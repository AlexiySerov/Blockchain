package Kusu.Blockchain.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512 {
    private static MessageDigest digest;

    private static MessageDigest getMessageDigestInstance() throws NoSuchAlgorithmException {
        if (digest == null) digest = MessageDigest.getInstance("SHA-512");

        return digest;
    }

    public static byte[] getHash(String string) throws NoSuchAlgorithmException {

        return getMessageDigestInstance().digest(string.getBytes(StandardCharsets.UTF_8));
    }

    public static String byteArrayToHexString(byte[] hash) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
