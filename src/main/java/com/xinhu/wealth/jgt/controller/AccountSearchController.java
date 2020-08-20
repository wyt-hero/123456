package com.xinhu.wealth.jgt.controller;

import com.xinhu.wealth.jgt.service.AccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账户类查询controller
 *
 * @author panjie
 * @date 2020/4/29 14:40
 */
@RestController
@RequestMapping("/api")
public class AccountSearchController {

    /**
     * 账户查询service
     */
    @Autowired
    private AccountSearchService accountSearchService;

    /**
     * 账户查询类接口
     *
     * @param body               入参body
     * @param version            版本号
     * @param processCode        功能代码
     * @param sing               签名
     * @param sendTime           发送时间
     * @param InstitutionMarking 机构标识
     * @param pageIndex          页码编号
     * @param rowSize            每页数量
     * @return
     */
    @PostMapping(value = "/v1/account/search", produces = "application/json;charset=utf-8")
    public String accountSearch(@RequestBody String body,
                                @RequestHeader("Version") String version,
                                @RequestHeader("ProcessCode") String processCode,
                                @RequestHeader("SingText") String sing,
                                @RequestHeader("SendTime") String sendTime,
                                @RequestHeader("InstitutionMarking") String InstitutionMarking,
                                @RequestHeader(name = "PageIndex",required = false) Integer pageIndex,
                                @RequestHeader(name = "RowSize",required = false) Integer rowSize

    ) {
        String result = accountSearchService.accountSearch(body, version, processCode, sing, sendTime, InstitutionMarking, pageIndex, rowSize);
        return result;
    }
}
