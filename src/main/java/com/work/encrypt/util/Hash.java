package com.work.encrypt.util;

import java.io.InputStream;
import java.security.MessageDigest;
import java.math.BigInteger;

/**
 *
 * @author AJO
 */
public class Hash {

    private static String toHex(byte[] bytes) {
        return new BigInteger(1, bytes).toString(16);

    }

    public static String encrypt(InputStream input, HashTypes algoritihm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algoritihm.getName());
        byte[] block = new byte[4096];
        int length;
        while ((length = input.read(block)) > 0) {
            digest.update(block, 0, length);
        }
        return toHex(digest.digest());

    }

    public static String encrypt(String input, HashTypes algoritihm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algoritihm.getName());
        digest.update(input.getBytes(), 0, input.length());
        return toHex(digest.digest());
    }

}
