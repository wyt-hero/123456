package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wyt
 * @data 2020/3/5 13:04
 * 2.3分红--------S420-分红查询
 */
@Data
public class BonusDTO {
    @JSONField(name = "BasisforCalculatingDividend")
    private float BasisforCalculatingDividend;//红 利 / 红利再投资基数

    @SerializedName(value = "TransactionCfmDate", alternate = "affirm_date")
    @JSONField(name = "TransactionCfmDate")
    private String TransactionCfmDate;//交易确认日期
    @JSONField(name = "CashDate")
    private String CashDate;//到帐日 null
    @SerializedName(value = "CurrencyType", alternate = "bill_money_type")
    @JSONField(name = "CurrencyType")
    private String CurrencyType;//结算币种
    @SerializedName(value = "VolOfDividendforReinvestment", alternate = "bonus_share")
    @JSONField(name = "VolOfDividendforReinvestment")
    private String VolOfDividendforReinvestment;//基金账户红利再投资基金份数
    @SerializedName(value = "DividentDate", alternate = "dividend_date")
    @JSONField(name = "DividentDate")
    private String DividentDate;//分红日 / 发放日

    @SerializedName(value = "DividendAmount", alternate = "dividend_totalbala")
    @JSONField(name = "DividendAmount")
    private BigDecimal DividendAmount;//基金账户红利资金

    @SerializedName(value = "XRDate", alternate = "ex_dividend_date")
    @JSONField(name = "XRDate")
    private String XRDate;//除权日

    @SerializedName(value = "ConfirmedAmount", alternate = "unit_bala")
    @JSONField(name = "ConfirmedAmount")
    private BigDecimal ConfirmedAmount;//每笔交易确认金额

    @SerializedName(value = "FundCode", alternate = "fund_code")
    @JSONField(name = "FundCode")
    private String FundCode;//基金代码

    @SerializedName(value = "RegistrationDate", alternate = "equity_reg_date")
    @JSONField(name = "RegistrationDate")
    private String RegistrationDate;//权益登记日期
    @JSONField(name = "ReturnCode")
    private String ReturnCode = "0000";//交易处理返回代码,0000成功，9999失败
    @JSONField(name = "InvestorCode")
    private String InvestorCode;//客户号

    @SerializedName(value = "TransactionAccountID", alternate = "trade_acco")
    @JSONField(name = "TransactionAccountID")
    private String TransactionAccountID;//投资人基金交易账号
    @JSONField(name = "BusinessCode")
    private String BusinessCode = "143";//业务代码

    @SerializedName(value = "TAAccountID", alternate = "ta_acco")
    @JSONField(name = "TAAccountID")
    private String TAAccountID;//投资人基金账号

    @JSONField(name = "DividendPerUnit")
    private float DividendPerUnit;//单位基金分红金额（含税）

    @SerializedName(value = "DefDividendMethod", alternate = "auto_buy")
    @JSONField(name = "DefDividendMethod")
    private String DefDividendMethod;//默认分红方式
    @JSONField(name = "RegionCode")
    private String RegionCode;//交易所在地区编号
    @JSONField(name = "DownLoaddate")
    private String DownLoaddate;//交易数据下传日期

    @SerializedName(value = "Charge", alternate = "fare_sx")
    @JSONField(name = "Charge")
    private BigDecimal Charge;//手续费

    @SerializedName(value = "NAV", alternate = "net_value")
    @JSONField(name = "NAV")
    private BigDecimal NAV;//基金单位净值
    @JSONField(name = "BranchCode")
    private String BranchCode;//网点号码
    @JSONField(name = "OtherFee1")
    private float OtherFee1;//其他费用 1
    @JSONField(name = "OtherFee2")
    private float OtherFee2;//其他费用 2
    @JSONField(name = "DividendRatio")
    private String DividendRatio;//红利比例
    @SerializedName(value = "TASerialNO", alternate = "ta_serial_id")
    @JSONField(name = "TASerialNO")
    private String TASerialNO;//TA 确认交易流
    @JSONField(name = "StampDuty")
    private float StampDuty;//印花税
    @JSONField(name = "TransferFee")
    private float TransferFee;//过户费

    @SerializedName(value = "ShareClass", alternate = "capital_mode")
    @JSONField(name = "ShareClass")
    private String ShareClass;//收费方式

    @SerializedName(value = "DrawBonusUnit", alternate = "bonus_unit")
    @JSONField(name = "DrawBonusUnit")
    private String DrawBonusUnit;//分红单位

    @SerializedName(value = "FrozenSharesforReinvest", alternate = "frozen_shares")
    @JSONField(name = "FrozenSharesforReinvest")
    private BigDecimal FrozenSharesforReinvest;//冻结再投资份额
    @JSONField(name = "DividendType")
    private String DividendType="0";//分红类型

    @SerializedName(value = "OriginalAppSheetNo", alternate = "original_appno")
    @JSONField(name = "OriginalAppSheetNo")
    private String OriginalAppSheetNo;//原申请单编号
    @JSONField(name = "AchievementPay")
    private float AchievementPay;//业绩报酬
    @JSONField(name = "AchievementCompen")
    private float AchievementCompen;//业绩补偿
    @SerializedName(value = "RegistrarCode", alternate = "ta_code")
    @JSONField(name = "RegistrarCode")
    private String RegistrarCode;//注册登记人代码
}
