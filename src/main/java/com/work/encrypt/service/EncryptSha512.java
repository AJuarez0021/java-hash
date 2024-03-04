package com.work.encrypt.service;

import com.work.encrypt.util.HashTypes;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author AJO
 */
@Service
@Qualifier("SHA512Service")
public class EncryptSha512 extends EncryptBase {

    @Override
    public String hash(String input) {
        return hash(input, HashTypes.SHA512);
    }

    @Override
    public String hash(InputStream input) {
        return hash(input, HashTypes.SHA512);
    }

}
