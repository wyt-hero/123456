package com.xinhu.wealth.jgt.service;

/**
 * 行情查询及其他查询service
 * @author wyt
 * @data 2020/3/20 15:58
 */
public interface MarketSearchService {

    /**
     * 行情查询及其他查询接口
     *
     * @param body 云毅body
     * @param sing 签名
     * @param processCode 功能代码
     * @param version 版本
     * @param pageIndex 页码编号
     * @param rowSize 每页数量
     * @param sendTime 发送时间
     * @return
     */
    String accountSearch(String body, String sing, String processCode, String version, String pageIndex, String rowSize, String sendTime);
}
