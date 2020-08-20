package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 18:34
 * 2.5 交易申请结果回写
 */
@Data
public class TradeResDTO {
    private String  InstitutionCode;//销售渠道
    private String   TransactionAccountID;//交易账号
    private String   TAAccountID;//基金帐号
    private String   Oserialno;//申报编号
    private String   Appserialno;//申请单编号
    private String   Transtate;//交易申报状态
    private String   TranStateDetail;//申报状态信息
}
