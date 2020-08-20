package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 20:52
 * 4.5 基金账户确认结果查询
 */
@Data
public class TraResYunYiDTO {
    private String  InstitutionMarking;//机构标识
    private String   InvestorCode;//客户号
    private String   TransactionAccountID;//交易账号
    private String   TAAccountID;//基金帐号
    private String   Oserialno;//申报编号
    private String   Appserialno;//申请单编号
    private String   Fdate;//日期
    private String   PageIndex;//页码编号
    private String   RowSize ;//每页数量
}
