package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.UUID;

/**
 * @author wyt
 * @data 2020/4/2 21:10
 * 4.3 账户结果回写
 */
@Data
public class AccResYunYiDTO {
    @JSONField(name = "Investorcode")
    private String   Investorcode;//客户号
    @JSONField(name = "TransactionAccountID")
    private String   TransactionAccountID;//交易账号
    @JSONField(name = "Oserialno")
    private String   Oserialno= UUID.randomUUID().toString().replaceAll("-","");//申报编号
    @JSONField(name = "Appserialno")
    private String   Appserialno;//申请单编号
    @JSONField(name = "Transtate")
    private String   Transtate;//交易申报状态
    @JSONField(name = "TranStateDetail")
    private String   TranStateDetail ="交易成功" ;//交易申报状态信息
}
