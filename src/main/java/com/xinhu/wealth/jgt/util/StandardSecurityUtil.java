package com.xinhu.wealth.jgt.util;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 加签验签工具类
 */
public class StandardSecurityUtil {

    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 加签
     *
     * @param in        加签原文
     * @param pivateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(String in, String pivateKey) throws Exception {
        Signature signa = Signature.getInstance("SHA512WithRSA");
        PrivateKey priKey = toPrivateKey(pivateKey);
        signa.initSign(priKey);
        signa.update(in.getBytes("UTF-8"));
        byte[] signdata = signa.sign();
        return Base64.encodeBase64String(signdata);
    }

    /**
     * 验签
     *
     * @param in        验签原文
     * @param signData  签名
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    public static boolean verify(String in, String signData, String publicKey) throws Exception {
        boolean flag = false;
        try {
            Signature signa = Signature.getInstance("SHA512WithRSA");
            PublicKey pubKey = toPublicKey(publicKey);
            signa.initVerify(pubKey);
            signa.update(in.getBytes("UTF-8"));
            byte[] sign_byte = Base64.decodeBase64(signData);
            flag = signa.verify(sign_byte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("验签失败，", e);
        }
        return flag;
    }

    /**
     * 转换私钥
     *
     * @param pivateKey
     * @return
     * @throws Exception
     */
    private static PrivateKey toPrivateKey(String pivateKey) throws Exception {
        byte[] data = Base64.decodeBase64(pivateKey);
        PKCS8EncodedKeySpec pkcs8Enc = new PKCS8EncodedKeySpec(data);
        KeyFactory keyFactory = null;
        PrivateKey priKey = null;
        keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        priKey = keyFactory.generatePrivate(pkcs8Enc);
        return priKey;
    }

    /**
     * 转换公钥
     *
     * @param publicKeyString base64公钥字符�?
     * @return 公钥对象
     */
    public static PublicKey toPublicKey(String publicKeyString) throws Exception {
        byte[] data = Base64.decodeBase64(publicKeyString);
        X509EncodedKeySpec x509Enc = new X509EncodedKeySpec(data);
        KeyFactory keyFactory = null;
        PublicKey publicKey = null;
        keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        publicKey = keyFactory.generatePublic(x509Enc);
        return publicKey;
    }


}
