package com.work.encrypt.service;

import com.work.encrypt.util.HashTypes;
import com.work.encrypt.util.Hash;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author AJO
 */
@Slf4j
public abstract class EncryptBase {

    protected String hash(String input, HashTypes algoritihm) {
        try {
            return Hash.encrypt(input, algoritihm);
        } catch (Exception ex) {
            log.error("Error: ", ex);
            return "";
        }
    }

    protected String hash(InputStream input, HashTypes algoritihm) {
        try {
            return Hash.encrypt(input, algoritihm);
        } catch (Exception ex) {
            log.error("Error: ", ex);
            return "";
        }
    }

    public abstract String hash(String input);

    public abstract String hash(InputStream input);
}
