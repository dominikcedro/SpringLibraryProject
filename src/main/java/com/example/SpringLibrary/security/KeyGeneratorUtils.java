package com.example.SpringLibrary.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
public class KeyGeneratorUtils {

    public static Key generateKey() throws NoSuchAlgorithmException {
        javax.crypto.KeyGenerator keyGen = javax.crypto.KeyGenerator.getInstance("HmacSHA256");
        return keyGen.generateKey();
    }
}