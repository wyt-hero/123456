package com.xinhu.wealth.jgt.service;

/**
 * 账户类申报service
 *
 * @author panjie
 * @date 2020/4/28 18:40
 */
public interface AccountRepordService {

    /**
     *
     * @param body 云毅body
     * @param version 版本
     * @param processCode 功能代码
     * @param sing 签名
     * @param sendTime 发送时间
     * @param institutionCode 销售渠道
     * @param institutionMarking 机构标识
     * @return
     *
     *  账户类申报接口
     */
    String accountRepord(String body, String version, String processCode, String sing, String sendTime, String institutionCode, String institutionMarking);
}
