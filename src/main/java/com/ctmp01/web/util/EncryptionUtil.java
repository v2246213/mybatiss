package com.ctmp01.web.util;

import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by admin on 2017/3/13.
 * 加密工具类
 */
public class EncryptionUtil {
    public static final String IV = "2017000000000000";
    public static  final String utf8="UTF-8";

    /**
     * Base64加密算法:编码方式utf-8
     * @param str
     * @return
     */
    public static String base64Encoder(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64解密算法：编码方式utf-8
     * @param str
     * @return
     */
    public static String base64Decoder(String str){
        if(!StringUtils.isEmpty(str)){
            byte[] bytes=Base64.getDecoder().decode(str);
            try {
                return new String(bytes,utf8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String AESandBase64Encrypt(String sSrc) {
        try {
            return AESandBase64Encrypt(sSrc,"wangwang20170518");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 加密
    public static String AESandBase64Encrypt(String sSrc, String sKey)
            throws Exception {
        if (StringUtils.isEmpty(sSrc) || StringUtils.isEmpty(sKey)) {
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);// 此处使用BAES64做转码功能，同时能起到2次
    }

    // 解密
    public static String Base64AndAesDecrypt(String sSrc, String sKey)
            throws Exception {
        // 判断Key是否正确
        if (StringUtils.isEmpty(sSrc) || StringUtils.isEmpty(sKey)) {
            return null;
        }
        // 判断Key是否为8位
        if (sKey.length() != 16) {
            return null;
        }
        byte[] raw = sKey.getBytes("UTF-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = Base64.getDecoder().decode(sSrc);// 先用bAES64解密
        try {
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"UTF-8");
            return originalString;
        }
        catch (Exception e) {
            return null;
        }
    }


}
