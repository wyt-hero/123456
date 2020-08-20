package com.xinhu.wealth.jgt.constants;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

/**
 *
 * @author yangxin26743
 */
@Slf4j
public class PassWord {

    final static Base64.Decoder decoder = Base64.getDecoder();
    final static Base64.Encoder encoder = Base64.getEncoder();

    /**
     * 构造函数
     */
    public PassWord() {
    }

    /**
     * 加密
     * @param datasource obj
     * @return str
     * @throws Exception e
     */
    public static String encrypt(String datasource) throws Exception {
        try {
            final byte[] bytes = datasource.getBytes("UTF-8");
            String encodedText = encoder.encodeToString(bytes);
            return encodedText;
        } catch (Throwable e) {
            log.error("Base64加密错误", e);
        }
        return null;
    }

    /**
     * 解密
     * @param src src
     * @return str
     * @throws Exception e
     */
    public static String decrypt(String src) throws Exception {
        return new String(decoder.decode(src), "UTF-8");
    }

    public static void main(String[] args) {
        try {
            String end = PassWord.encrypt("123456");
            System.out.println(end);
            System.out.println(PassWord.decrypt(end));
        } catch (Exception e){

        }
    }
}