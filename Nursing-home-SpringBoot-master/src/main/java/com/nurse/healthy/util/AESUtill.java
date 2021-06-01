package com.nurse.healthy.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加解密工具
 */
public class AESUtill {
    private static String systemKey = "72hogezUaL58ciAm";

    /**
     * 加密方法
     * @param data
     * @return
     */
    public static String encryptData(String data) {
        String strKey = systemKey;
        // 密钥数据
        byte[] rawKey = getRawKey(strKey.getBytes());
        // 密码的明文
        String clearPwd = data;
        // 密码加密后的密文
        byte[] encryptedByteArr = encrypt(rawKey, clearPwd);
        String s = Base64.encodeBase64String(encryptedByteArr);
        return s;
    }

    /**
     * 解密方法
     * @param dataEncrypt
     * @return
     */
    public static String deci(String dataEncrypt) {
        String strKey = systemKey;
        // 密钥数据
        byte[] rawKey = getRawKey(strKey.getBytes());
        byte[] bytes = Base64.decodeBase64(dataEncrypt);
        String decryptedPwd = decrypt(bytes, rawKey);
        //decryptedPwd = decrypt(Base64.decodeBase64(decryptedPwd), rawKey);
        return decryptedPwd;
    }

    /**
     * 解密两次
     * @param dataEncrypt
     * @return
     */
    public static String deciTwo(String dataEncrypt) {
        String strKey = systemKey;
        // 密钥数据
        byte[] rawKey = getRawKey(strKey.getBytes());
        byte[] bytes = Base64.decodeBase64(dataEncrypt);
        String decryptedPwd = decrypt(bytes, rawKey);
        decryptedPwd = decrypt(Base64.decodeBase64(decryptedPwd), rawKey);
        return decryptedPwd;
    }

    public static void main(String[] args) {
        // 密钥的种子，可以是任何形式，本质是字节数组
        String strKey = "b11364c1-a00b-4f75-8858-2902cc16371b" + "newstar12189898";
        // 密钥数据
        byte[] rawKey = getRawKey(strKey.getBytes());
        // 密码的明文
        String clearPwd = "cSe5AUZfsYkLuFyQaYmmX2QgEmmLeU4pFF7kRxipK3QYy8gS2SZZ";
        // 密码加密后的密文
        byte[] encryptedByteArr = encrypt(rawKey, clearPwd);
        String encryptedPwd = new String(encryptedByteArr);
        System.out.println(encryptedPwd);
        String s = Base64.encodeBase64String(encryptedByteArr);
        byte[] bytes = Base64.decodeBase64(s);
        // 解密后的字符串
        String decryptedPwd = decrypt(bytes, rawKey);
        System.out.println(decryptedPwd);

    }

    /**
     * @param rawKey   密钥
     * @param clearPwd 明文字符串
     * @return 密文字节数组
     */
    public static byte[] encrypt(byte[] rawKey, String clearPwd) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encypted = cipher.doFinal(clearPwd.getBytes());
            return encypted;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param encrypted 密文字节数组
     * @param rawKey    密钥
     * @return 解密后的字符串
     */
    public static String decrypt(byte[] encrypted, byte[] rawKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * @param seed种子数据
     * @return 密钥数据
     */
    public static byte[] getRawKey(byte[] seed) {
        byte[] rawKey = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            // AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            rawKey = secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
        }
        return rawKey;
    }

}
