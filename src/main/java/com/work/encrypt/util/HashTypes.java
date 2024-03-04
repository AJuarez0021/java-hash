package com.work.encrypt.util;

/**
 *
 * @author AJO
 */
public enum HashTypes {

    MD5("MD5"),
    SHA1("SHA1"),
    SHA256("SHA256"),
    SHA512("SHA512");

    private final String name;

    HashTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
