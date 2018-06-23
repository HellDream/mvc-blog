package com.blog.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

public class Encoder {
    private final static String DES = "DES";
    private final static String ENCODE_STR = "utf-8";
    private final byte[] DESIV = new byte[] { 0x12, 0x34, 0x56, 120, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };
    private  AlgorithmParameterSpec aps = new IvParameterSpec(DESIV);
    private Key key;
    public Encoder(String enKey){
        try{
            DESKeySpec keySpec = new DESKeySpec(enKey.getBytes(ENCODE_STR));// 设置密钥参数
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
            key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String encrypt(String data)  {
        try{

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key,aps);
            byte[] bytes = cipher.doFinal(data.getBytes(ENCODE_STR));
            return new BASE64Encoder().encode(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public String decrypt(String data){
        try {
            Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            deCipher.init(Cipher.DECRYPT_MODE, key, aps);
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
            return new String(pasByte, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] encryptOrDecrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        return cipher.doFinal(data);
    }

}
