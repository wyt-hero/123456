package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 19:44
 * 2.1 下单
 */
@Data
public class OrderYunYiDTO {
    private String InstitutionCode;//销售渠道号
    //todo begain 新增密码字段
    private String Password;
    //todo end
    private String InvestorCode;//客户号
    private String InstitutionMarking;//机构标识
    private String TransactionAccountID;//交易账号
    private String TAAccountID;//基金帐号
    private String Busintype;//业务类型
    private String Fundcode;//基金代码
    private String Unitcode;//单元代码
    private String Groupcode;//组合代码
    private String TargetGroupcode;//目标组合代码
    private String Oserialno;//申报编号
    private String Orgserialno;//原申报编号
    private String OrgAppserialno;//原申请单编号
    private String Appamount;//申请金额
    private String Appvol;//申请份额
    private String LargeRedemptionFlag;//巨额赎回标识
    private String FastRedemptionFlag;//T+0.5 赎回标识
    private String CodeOfTargetFund;//目标基金代码
    private String TargetDistributorCode;//对方销售人代码
    private String TargetBranchCode;//网点号
    private String DefDividendMethod;//默认分红方式
    private String Oworkday;//下达日期
    private String Oworktime;//下达时间
}
