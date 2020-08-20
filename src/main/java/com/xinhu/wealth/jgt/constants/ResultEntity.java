package com.xinhu.wealth.jgt.constants;

import java.util.HashMap;
import java.util.Map;

public enum ResultEntity {

    //成功
    SUCCESS(200, "操作成功!"),
    //连接失败
    FAIL(90004, "操作失败！请重试！"),
    //验签失败
    SINGERRO(90003,"验签失败"),

    //云毅失败
    YUNYIERROR(2,"失败"),

    //云毅成功
    YUNYISUCCESS(0, "成功"),
    OUT_TIME(40404,"请求超时"),
    //云逸验签失败
    YUNYISINGERROR(1, "验签失败"),
   //值为空
    TRUENULL(90000,"没有相关数据");


    ResultEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * code
     */
    private final int code;
    /**
     * 文言
     */
    private final String msg;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 根据key获取value
     *
     * @param code : 键值key
     * @return String
     */
    public static String getMsgByCode(int code) {
        ResultEntity[] enums = ResultEntity.values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getCode() == code) {
                return enums[i].getMsg();
            }
        }
        return "";
    }

    /**
     * 转换为MAP集合
     *
     * @returnMap<String, String>
     */
    public static Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        ResultEntity[] enums = ResultEntity.values();
        for (int i = 0; i < enums.length; i++) {
            String key = String.valueOf(enums[i].getCode());
            map.put(key, enums[i].getMsg());
        }
        return map;
    }

}
