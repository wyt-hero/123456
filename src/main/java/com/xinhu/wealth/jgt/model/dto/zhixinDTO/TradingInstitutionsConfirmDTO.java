package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 15:05
 * 4.5基金账户确认结果查询--------S486(账户确认查询)
 */
@Data
public class TradingInstitutionsConfirmDTO {
    @SerializedName(value = "Oserialno", alternate = "request_no")
    @JSONField(name = "Oserialno")
    private String Oserialno;//申报编号

    @SerializedName(value = "Appserialno", alternate = "allot_no")
    @JSONField(name = "Appserialno")
    private String Appserialno;//申请单编号

    @SerializedName(value = "TransactionCfmDate", alternate = "affirm_date")
    @JSONField(name = "TransactionCfmDate")
    private String TransactionCfmDate;//交易确认日期
    private String InvestorCode;//客户号
    //todo 基金账号查询(S405)
    @SerializedName(value = "TransactionAccountID", alternate = "trade_acco")
    @JSONField(name = "TransactionAccountID")
    private String TransactionAccountID;//交易账号
    //todo TA信息查询(S443)

    @SerializedName(value = "RegistrarCode", alternate = "ta_code")
    @JSONField(name = "RegistrarCode")
    private String RegistrarCode;//注册登记人代码
    //todo 基金账号查询(S405)
    @SerializedName(value = "TAAccountID", alternate = "ta_acco")
    @JSONField(name = "TAAccountID")
    private String TAAccountID;//基金帐号

    @SerializedName(value = "Busintype", alternate = "business_type")
    @JSONField(name = "Busintype")
    private String Busintype;//业务类型

    @SerializedName(value = "TASerialno", alternate = "confirm_id")
    @JSONField(name = "TASerialno")
    private String TASerialno;//确认流水号
    @SerializedName(value = "ReturnCode", alternate = "code")
    @JSONField(name = "ReturnCode")
    private String ReturnCode;//确认结果
}
