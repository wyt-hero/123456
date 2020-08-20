package com.xinhu.wealth.jgt.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Table;

/**
 * @author wyt
 * @data 2020/3/9 10:32
 * 2.2确认--------S416 交易成交查询
 */
@Data
@Table(name = "jgt_trading_confirm")
public class TradingConfirmEntity {
    @SerializedName(value = "Oserialno", alternate = "original_appno")
    @JSONField(name = "Oserialno")
    private String Oserialno;//申报编号
    @SerializedName(value = "Appserialno", alternate = "allot_no")
    @JSONField(name = "Appserialno")
    private String Appserialno;//申请单编号
    @SerializedName(value = "TransactionCfmDate", alternate = "affirm_date")
    @JSONField(name = "TransactionCfmDate")
    private String TransactionCfmDate;//交易确认日期

    private String CashDate;//到帐日
    @SerializedName(value = "CurrencyType", alternate = "money_type")
    @JSONField(name = "CurrencyType")
    private String CurrencyType;//结算币种
    @JSONField(name = "ConfirmedVol")
    private double ConfirmedVol;//交易确认份数
    @SerializedName(value = "ConfirmedAmount", alternate = "trade_confirm_balance")
    @JSONField(name = "ConfirmedAmount")
    private double ConfirmedAmount;//交易确认金额
    @SerializedName(value = "FundCode", alternate = "fund_code")
    @JSONField(name = "FundCode")
    private String FundCode;//基金代码

    private String LargeRedemptionFlag="0";//巨额赎回处理标志,0000成功，9999失败

    private String ReturnCode="0000";//交易处理返回
    @SerializedName(value = "InvestorCode", alternate = "cust_no")
    @JSONField(name = "InvestorCode")
    private String InvestorCode;//客户号
    @SerializedName(value = "TransactionAccountID", alternate = "trade_acco")
    @JSONField(name = "TransactionAccountID")
    private String TransactionAccountID;//投资人基金交易帐号
    //todo 建议申请S002和S003接口授权
    private double ApplicationVol;//申请基金份数
    //todo 建议申请S002和S003接口授权
    private double ApplicationAmount;//申请金额
    @SerializedName(value = "BusinessCode", alternate = "fund_busin_code")
    @JSONField(name = "BusinessCode")
    private String BusinessCode;//业务代码

    @SerializedName(value = "TAAccountID", alternate = "ta_acco")
    @JSONField(name = "TAAccountID")
    private String TAAccountID;//投资人基金帐号
    @SerializedName(value = "TASerialNO", alternate = "ta_serial_id")
    @JSONField(name = "TASerialNO")
    private String TASerialNO;//TA 确认交易流水号

    private String BusinessFinishFlag="0";//业务过程完全结束标识
    @SerializedName(value = "Charge", alternate = "fare_sx")
    @JSONField(name = "Charge")
    private double Charge;//手续费
    @JSONField(name = "NAV")
    private double NAV;//基金单位净值
    @SerializedName(value = "OtherFee1", alternate = "ta_fare")
    @JSONField(name = "OtherFee1")
    private double OtherFee1;//其他费用 1
    @SerializedName(value = "StampDuty", alternate = "stamptax")
    @JSONField(name = "StampDuty")
    private double StampDuty;//印花税
    private double TotalBackendLoad;//交易后端收费总额

    private String TargetDistributorCode;//对方销售人代码
    @SerializedName(value = "TargetBranchCode", alternate = "oppo_netno")
    @JSONField(name = "TargetBranchCode")
    private String TargetBranchCode;//对方网点号
    @JSONField(name = "TargetTransactionAccountID")
    private String TargetTransactionAccountID;//对方销售人处投资人基金交易帐号

    private String TargetRegionCode;//对方所在地区编号
    @SerializedName(value = "DefDividendMethod", alternate = "auto_buy")
    @JSONField(name = "DefDividendMethod")
    private String DefDividendMethod;//默认分红方式

    private double DividendRatio;//红利比例

    private double Interest=0;//基金账户利息金额

    private double VolumeByInterest=0;//利息产生的基金份数

    private float InterestTax;//利息税
    private float Tax;//税金

    //todo S428，基金转换业务的目标基金的净值
    @SerializedName(value = "TargetNAV", alternate = "net_value")
    @JSONField(name = "TargetNAV")
    private String TargetNAV;//目标基金的单位净值

    @SerializedName(value = "CfmVolOfTargetFund", alternate = "trade_confirm_type")
    @JSONField(name = "CfmVolOfTargetFund")
    private double CfmVolOfTargetFund;//目标基金的确认份数
    private double OtherFee2;//其他费用 2
    private double TransferFee;//过户费

    private String FromTAFlag="0";//是否注册登记人发起业务标志
    @SerializedName(value = "ShareClass", alternate = "share_type")
    @JSONField(name = "ShareClass")
    private String ShareClass;//收费方式
    @SerializedName(value = "CodeOfTargetFund", alternate = "target_fund_code")
    @JSONField(name = "CodeOfTargetFund")
    private String CodeOfTargetFund;//转换时的目标基金代码
    @SerializedName(value = "TargetTAAccountID", alternate = "oppo_fund_account")
    @JSONField(name = "TargetTAAccountID")
    private String TargetTAAccountID;//对方基金账号

    private String TargetRegistrarCode;//对方 TA 代码
    @SerializedName(value = "TargetShareType", alternate = "target_share_type")
    @JSONField(name = "TargetShareType")
    private String TargetShareType;//对方基金份额类别
    private String TakeIncomeFlag;//带走收益标志
    private float ChangeFee;//转换费
    private float RecuperateFee;//补差费
    private float AchievementPay;//业绩报酬
    private float AchievementCompen;//业绩补偿
    private float BreachFee;//违约金
    @SerializedName(value = "ErrorDetail", alternate = "message")
    @JSONField(name = "ErrorDetail")
    private String ErrorDetail;//出错详细信息
    private float RaiseInterest=0;//认购期间利息
}
