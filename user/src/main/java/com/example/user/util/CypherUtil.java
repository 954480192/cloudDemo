package com.example.user.util;

import org.hibernate.mapping.PrimaryKey;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class CypherUtil {
    private static final String TYPE = "RSA";

    public static void main(String[] args) throws Exception {
        KeyPair key = KeyPairGenerator.getInstance(TYPE).generateKeyPair();
        PublicKey publicKey = key.getPublic();
        PrivateKey privateKey = key.getPrivate();

        String msg = "zp";
        String code = "UTF-8";

        Cipher cipher = Cipher.getInstance(TYPE);
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        String encode =Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes()));
        System.out.println(encode);

        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        System.out.println(new String(cipher.doFinal(Base64.getDecoder().decode(encode.getBytes()))));
    }

}
