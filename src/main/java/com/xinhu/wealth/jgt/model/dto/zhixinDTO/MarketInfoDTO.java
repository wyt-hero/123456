package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 14:12
 * 3.1行情信息查询------最新基金行情查询(S428)
 */
@Data
public class MarketInfoDTO {
    @SerializedName(value = "Fdate", alternate = "accept_hq_date")
    @JSONField(name = "Fdate")
    private String Fdate;//日期
    @SerializedName(value = "NavDate", alternate = "nav_date")
    @JSONField(name = "NavDate")
    private String NavDate;//净值日期
    @SerializedName(value = "Fundcode", alternate = "fund_code")
    @JSONField(name = "Fundcode")
    private String Fundcode;//基金代码
    @SerializedName(value = "FundName", alternate = "fund_name")
    @JSONField(name = "FundName")
    private String FundName;//基金简称
    @SerializedName(value = "FundNameAll", alternate = "fund_full_name")
    @JSONField(name = "FundNameAll")
    private String FundNameAll;//基金全称fund_full_name
    @SerializedName(value = "FundStatus", alternate = "fund_status")
    @JSONField(name = "FundStatus")
    private String FundStatus;//基金状态
    @SerializedName(value = "NAV", alternate = "net_value")
    @JSONField(name = "NAV")
    private String NAV;//基金单位净值
    @JSONField(name = "NetValueType")
    private String NetValueType;//净值类型

    @SerializedName(value = "ConvertStatus", alternate = "convert_status")
    @JSONField(name = "ConvertStatus")
    private String ConvertStatus;//基金转换状态

    @SerializedName(value = "PeriodicStatus", alternate = "valuagr_state")
    @JSONField(name = "PeriodicStatus")
    private String PeriodicStatus;//定期定额状态
    @JSONField(name = "TransferAgencyStatus")
    private String TransferAgencyStatus;//转托管状态

    @SerializedName(value = "CurrencyType", alternate = "money_type")
    @JSONField(name = "CurrencyType")
    private String CurrencyType;//结算币种
    @SerializedName(value = "DefDividendMethod", alternate = "default_auto_buy")
    @JSONField(name = "DefDividendMethod")
    private String DefDividendMethod;//默认分红方式

    @SerializedName(value = "ChangeDividendMethod", alternate = "forbid_modi_autobuy_flag")
    @JSONField(name = "ChangeDividendMethod")
    private String ChangeDividendMethod;//是否允许修改分红方式

    @SerializedName(value = "RegistrarCode", alternate = "ta_code")
    @JSONField(name = "RegistrarCode")
    private String RegistrarCode;//注册登记人代码
    @SerializedName(value = "FundIncome", alternate = "per_myriad_income")
    @JSONField(name = "FundIncome")
    private String FundIncome;//货币基金万份收益
    @JSONField(name = "FundIncomeFlag")
    private String FundIncomeFlag;//货币基金万份收益正负
    @SerializedName(value = "Yield", alternate = "income_ratio")
    @JSONField(name = "Yield")
    private String Yield="0";//货币基金七日年化收益率
    @JSONField(name = "YieldFlag")
    private String YieldFlag;//货币基金七日年化收益率正负


    @JSONField(name = "RateOfWeek")
    private String RateOfWeek="0";//近一周收益率
    @JSONField(name = "RateOfMoon")
    private String RateOfMoon="0";//近一月收益率
    @JSONField(name = "RateOfYear")
    private String RateOfYear="0";//近一年收益率
    @JSONField(name = "FundSize")
    private String FundSize;//基金规模
    @JSONField(name = "AllowBreachRedempt")
    private String AllowBreachRedempt;//允许违约赎回标志
    @SerializedName(value = "FundType", alternate = "ofund_type")
    @JSONField(name = "FundType")
    private String FundType;//基金类型
    @JSONField(name = "FundTypeName")
    private String FundTypeName;//基金类型名称
    //todo TA信息查询(S443)：根据ta_code查询对应名称

    @SerializedName(value = "RegistrarName", alternate = "ta_name")
    @JSONField(name = "RegistrarName")
    private String RegistrarName;//注册登记人名称

    @SerializedName(value = "IssueName", alternate = "manager_name")
    @JSONField(name = "IssueName")
    private String IssueName;//发行人名称
    @JSONField(name = "RedemptionCashDate")
    private String RedemptionCashDate;//赎回款到账天数

    @SerializedName(value = "FastRedemptionFlag", alternate = "quick_exceed_flag")
    @JSONField(name = "FastRedemptionFlag")
    private String FastRedemptionFlag;//T+0.5 赎回支持标识

    @JSONField(name = "InstAppSubsVol")
    private float InstAppSubsVol;//法人追加认购份数
    @JSONField(name = "MinAmountByInst")
    private float MinAmountByInst;//法人首次认购最低金额
    @JSONField(name ="MinVolByInst" )
    private float MinVolByInst;//法人首次认购最低份数
    @JSONField(name ="MaxRedemptionVol" )
    private float MaxRedemptionVol;//基金最高赎回份数
    @JSONField(name ="MaxSubsVolByInst" )
    private float MaxSubsVolByInst;//法人最高认购份数

    @JSONField(name ="UnitSubsVolByInst" )
    private String UnitSubsVolByInst;//法人认购份数单位
    @JSONField(name ="UnitSubsAmountByInst" )
    private String UnitSubsAmountByInst;//法人认购金额单位
    @SerializedName(value = "MinBidsAmountByInst", alternate = "min_purchase_amount")
    @JSONField(name = "MinBidsAmountByInst")
    private float MinBidsAmountByInst;//法人首次申购最低金额

    @JSONField(name = "MinRedemptionVol")
    private float MinRedemptionVol;//基金最少赎回份数
    @JSONField(name = "MinInterconvertVol")
    private float MinInterconvertVol;//最低基金转换份数
    @SerializedName(value = "InstMaxPurchase", alternate = "max_purchase_amount")
    @JSONField(name = "InstMaxPurchase")
    private String InstMaxPurchase="0.00";//法人最大申购金额

    //todo
    @JSONField(name = "MinAppBidsAmountByInst")
    private String MinAppBidsAmountByInst="0.00";//法人追加申购最低金额
    @JSONField(name = "InstMaxRedeem")
    private String InstMaxRedeem="0.00";//法人最大赎回份额
    @JSONField(name ="MaxSubsAmountByInst" )
    private String MaxSubsAmountByInst="0.00";//法人最高认购金额
    @JSONField(name = "InstAppSubsAmnt")
    private String InstAppSubsAmnt="0.00";//法人追加认购金
    //todo
    @JSONField(name = "DeclareState")
    private String declare_state;//申购状态
    @JSONField(name = "ShareType")
    private String share_type;//份额分类

    //赋值
    @JSONField(name = "InstDayMaxSumBuy")
    private String InstDayMaxSumBuy="0.00";//法人当日累计购买最大金额

    @JSONField(name = "HuilinDayMaxSumBuy")
    private String HuilinDayMaxSumBuy="0.00";//汇林当日累计购买限额 2

    @JSONField(name = "InstDayMaxSumRedeem")
    private float InstDayMaxSumRedeem;//法人当日累计赎回最大份额
    @JSONField(name = "InstMinPurchase")
    private String InstMinPurchase="0.00";//法人单笔最低购买金额

}
