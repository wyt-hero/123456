package com.xinhu.wealth.jgt.controller;

import com.xinhu.wealth.jgt.service.AccountRepordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账户类申报controller
 *
 * @author panjie
 * @date 2020/4/28 18:40
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class AccountRepordController {

    /**
     * 账户类申报service
     */
    @Autowired
    private AccountRepordService AccountRepordService;

    /**
     * 账户类申报接口 账户新增，修改，基金账户销户，基金账户销户回写
     *
     * @param body               云毅body
     * @param version            版本
     * @param processCode        功能代码
     * @param sing               签名
     * @param sendTime           发送时间
     * @param institutionCode    销售渠道
     * @param institutionMarking 机构标识
     * @return
     */
    @PostMapping(value = "/v1/account/repord", produces = "application/json;charset=utf-8")
    public String accountRepord(@RequestBody String body,
                                @RequestHeader("Version") String version,
                                @RequestHeader("ProcessCode") String processCode,
                                @RequestHeader("SingText") String sing,
                                @RequestHeader("SendTime") String sendTime,
                                @RequestHeader("InstitutionCode") String institutionCode,
                                @RequestHeader("InstitutionMarking") String institutionMarking
    ) {
        String result = AccountRepordService.accountRepord(body, version, processCode, sing, sendTime, institutionCode, institutionMarking);
        log.info("返回结果:{}", result);
        return result;
    }
}
