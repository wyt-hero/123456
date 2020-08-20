package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wyt
 * @data 2020/3/5 13:29
 * 2.4持仓-------S403份额查询
 */
@Data
public class PositionShareDTO {
    @SerializedName(value = "AvailableVol", alternate = "enable_shares")
    @JSONField(name = "AvailableVol")
    private BigDecimal AvailableVol;//持有人可用基金份数
    @SerializedName(value = "TotalVolOfDistributorInTA", alternate = "current_share")
    @JSONField(name = "TotalVolOfDistributorInTA")
    private BigDecimal TotalVolOfDistributorInTA;//基金总份数（含冻结）

    @SerializedName(value = "TransactionCfmDate", alternate = "affirm_date")
    @JSONField(name = "TransactionCfmDate")
    private String TransactionCfmDate;//交易确认日期
    @SerializedName(value = "FundCode", alternate = "fund_code")
    @JSONField(name = "FundCode")
    private String FundCode;//基金代码
    @JSONField(name = "InvestorCode")
    private String InvestorCode;//客户号
    @SerializedName(value = "TransactionAccountID", alternate = "trade_acco")
    @JSONField(name = "TransactionAccountID")
    private String TransactionAccountID;//投资人基金交易账号
    @SerializedName(value = "TAAccountID", alternate = "ta_acco")
    @JSONField(name = "TAAccountID")
    private String TAAccountID;//投资人基金账号
    @SerializedName(value = "TotalFrozenVol", alternate = "frozen_share")
    @JSONField(name = "TotalFrozenVol")
    private BigDecimal TotalFrozenVol;//基金冻结总份数

    @SerializedName(value = "TASerialNO", alternate = "ta_serial_id")
    @JSONField(name = "TASerialNO")
    private String TASerialNO;//TA 确认交易流水号
    @JSONField(name = "TotalBackendLoad")
    private float TotalBackendLoad;//交易后端收费总额

    @SerializedName(value = "ShareClass", alternate = "share_type")
    @JSONField(name = "ShareClass")
    private String ShareClass;//收费方式
    @JSONField(name = "AccountStatus")
    private String AccountStatus;//账户状态
    @JSONField(name = "ShareRegisterDate")
    private String ShareRegisterDate;//份额注册日期
    @SerializedName(value = "UndistributeMonetaryIncome", alternate = "unpaid_income")
    @JSONField(name = "UndistributeMonetaryIncome")
    private BigDecimal UndistributeMonetaryIncome;//货币基金未付收益金额
    @JSONField(name = "UndistributeMonetaryIncomeFlag")
    private float UndistributeMonetaryIncomeFlag;//货币基金未付收益金额正负
    @JSONField(name = "GuaranteedAmount")
    private float GuaranteedAmount;//剩余保本金额
    @SerializedName(value = "DefDividendMethod", alternate = "auto_buy")
    @JSONField(name = "DefDividendMethod")
    private String DefDividendMethod;//默认分红方式

    @SerializedName(value = "RegistrarCode", alternate = "ta_no")
    @JSONField(name = "RegistrarCode")
    private String RegistrarCode;//注册登记人代码

    @SerializedName(value = "AccIncome", alternate = "accum_income")
    @JSONField(name = "AccIncome")
    private String AccIncome="0.00";//累计收入
    @JSONField(name = "ByIncome")
    private String ByIncome="0.00";//持有收益
    @SerializedName(value = "HoldingIncome", alternate = "holding_income")
    @JSONField(name = "HoldingIncome")
    private String HoldingIncome="0.00";// 持仓收益
    @SerializedName(value = "DayIncome", alternate = "fund_curr_income")
    @JSONField(name = "DayIncome")//接口中叫基金收益
    private String DayIncome="0.00";//昨日收益
}
