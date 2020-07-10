package com.zp.cloud_common.utils;

import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5Util {
    private static final String MD5 = "md5";
    public static  String encode(String content){
        try {
            if(StringUtils.isEmpty(content)){
                throw  new Error("加密内容不能为空！");
            }
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(content.getBytes());
            BigInteger bigInteger = new BigInteger(digest.digest());
            return bigInteger.toString(16).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean equal(String target,String source){
        if(StringUtils.isEmpty(target)){
            throw  new Error("密内容不能为空！");
        }
        String econdeStr = encode(target);
        return econdeStr.equals(source);
    }

    public static void main(String[] args) {
        String source = MD5Util.encode("hello");
        System.out.println(source);
        System.out.println(MD5Util.equal("hello",source));
    }

}
