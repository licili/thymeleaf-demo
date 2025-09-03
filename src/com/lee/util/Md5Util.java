package com.lee.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: ljg
 * @Date: 2025/9/3 2:38 AM Wednesday
 * @Description: md5加密
 */
public class Md5Util {

    public static String encode(String source) {
        if(source == null || "".equals(source)) {
            throw new RuntimeException("加密明文不能为空");
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(source.getBytes());
        byte[] digest = messageDigest.digest();

        BigInteger bigInteger = new BigInteger(1, digest);
        String digestString = bigInteger.toString(16).toUpperCase();

        return digestString;
    }
}
