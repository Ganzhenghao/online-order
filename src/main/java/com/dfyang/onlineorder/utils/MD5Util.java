package com.dfyang.onlineorder.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: 55411
 * @Date: 2019/7/23 19:46
 * @Description: MD5加密工具类
 */
public class MD5Util {

    private MD5Util() {}

    /**
     * 简易加密
     * @param msg 加密信息
     * @return
     */
    public static String encrypt(String msg) {
        return encrypt(msg, "");
    }

    /**
     * 加盐加密
     * @param msg 加密信息
     * @param salt 盐
     * @return
     */
    public static String encrypt(String msg, String salt) {
        msg += salt;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuffer result = null;
        // 循环3次加密
        for (int i = 0; i < 3; i++) {
            byte[] buf = md.digest(msg.getBytes());
            result = new StringBuffer();
            int num;
            for (byte b : buf){
                num = b;
                // 简易加盐，如果num为负，加上256，确保有两位，如果小于16(F=15)，表示不足2位，则添0
                if (num < 0)
                    num += 256;
                if (num < 16)
                    result.append(0);
                result.append(Integer.toHexString(num));// 转化为16进制
            }
            msg = result.toString();
        }
        return result.toString();
    }
}
