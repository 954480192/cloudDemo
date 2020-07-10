package com.zp.cloud_common.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class RSAUtils {
    private static final String RSA = "RSA";
    private static final Integer SIZE = 2048;
    public static KeyPair getKeys(){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(SIZE); // 初始化密钥长度
            KeyPair keyPair = keyPairGenerator.generateKeyPair();// 生成密钥对
            return keyPair;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeByPub(String pubKey,String content){
        try {
//            KeyFactory.getInstance(RSA).generatePublic();
            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.ENCRYPT_MODE,keyPair.getPrivate());
            byte[] bytes = cipher.doFinal("hello".getBytes());
            System.out.println(Base64.getEncoder().encodeToString(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // 初始化密钥长度
            KeyPair keyPair = keyPairGenerator.generateKeyPair();// 生成密钥对
            System.out.println(keyPair.getPrivate());
            System.out.println(keyPair.getPublic());
            System.out.println("-----------------------------");
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,keyPair.getPrivate());
            byte[] bytes = cipher.doFinal("hello".getBytes());
            System.out.println(Base64.getEncoder().encodeToString(bytes));
            System.out.println("-----------------------------");
             cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,keyPair.getPublic());
            bytes = cipher.doFinal(bytes);
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
