/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.encrypt.service;

import com.work.encrypt.util.HashTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author AJO
 */
@Service
public class EncryptFactory {

    @Autowired
    @Qualifier("MD5Service")
    private EncryptBase encryptMd5;

    @Autowired
    @Qualifier("SHA1Service")
    private EncryptBase encryptSha1;

    @Autowired
    @Qualifier("SHA256Service")
    private EncryptBase encryptSha256;

    @Autowired
    @Qualifier("SHA512Service")
    private EncryptBase encryptSha512;

    public EncryptBase createFactory(String type) {
        HashTypes hashType = HashTypes.valueOf(type.toUpperCase());
        switch (hashType) {
            case MD5 -> {
                return encryptMd5;
            }
            case SHA1 -> {
                return encryptSha1;
            }
            case SHA256 -> {
                return encryptSha256;
            }
            case SHA512 -> {
                return encryptSha512;
            }
            default ->
                throw new IllegalArgumentException("Opcion no permitida");

        }
    }

}
