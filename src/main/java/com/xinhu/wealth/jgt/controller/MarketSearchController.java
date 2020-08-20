package com.xinhu.wealth.jgt.controller;

import com.xinhu.wealth.jgt.service.MarketSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 行情查询及其他查询controller
 *
 * @author wyt
 * @data 2020/3/20 15:58
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class MarketSearchController {


    /**
     * 行情查询service
     */
    @Autowired
    private MarketSearchService marketSearchService;

    /**
     * 行情查询及其他查询接口
     *
     * @param body        云毅body
     * @param sing        签名
     * @param processCode 功能代码
     * @param version     版本
     * @param pageIndex   页码编号
     * @param RowSize     每页数量
     * @param sendTime    发送时间
     * @return
     */
    @PostMapping(value = "/v1/market/search", produces = "application/json;charset=utf-8")
    public String accountSearch(@RequestBody String body,
                                @RequestHeader("SingText") String sing,
                                @RequestHeader("ProcessCode") String processCode,
                                @RequestHeader("Version") String version,
                                @RequestHeader("PageIndex") String pageIndex,
                                @RequestHeader("RowSize") String RowSize,//每页数量
                                @RequestHeader("SendTime") String sendTime) {
        String result = marketSearchService.accountSearch(body, sing, processCode, version, pageIndex, RowSize, sendTime);
        return result;
    }
}
