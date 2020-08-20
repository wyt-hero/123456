package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 19:22
 * 交易成交查询(S416)
 */
@Data
public class TradingZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口
    @SerializedName(value = "allot_no", alternate = "Oserialno")
    @JSONField(name = "allot_no")
    private String allot_no;//申请编号
    private String busin_ass_code;//业务辅助代码
    private String chinapay_serial_no;//银商流水号
    private String client_id;//客户编号
    private String comb_request_no;//组合申请编号
    private String come_from;//交易来源
    private String expiry_date;//终止日期
    private String fund_busin_code;//业务代码

    @SerializedName(value = "fund_code", alternate = "Fundcode")
    @JSONField(name = "fund_code")
    private String fund_code;//基金代码
    private String fund_sub_type;//基金子类型
    private String fund_sub_type2;//基金子类型2
    private String ofund_type;//基金类型
    private String query_light;//轻量查询

    @SerializedName(value = "request_num", alternate = "RowSize")
    @JSONField(name = "request_num")
    private String request_num;//每页记录数

    @SerializedName(value = "request_pageno", alternate = "PageIndex")
    @JSONField(name = "request_pageno")
    private String request_pageno;//请求页码

    @SerializedName(value = "start_date", alternate = "Fdate")
    @JSONField(name = "start_date")
    private String start_date;//开始日期

    @SerializedName(value = "ta_serial_id", alternate = "TAAccountID")
    @JSONField(name = "ta_serial_id")
    private String ta_serial_id;//TA确认编号

    @SerializedName(value = "trade_acco", alternate = "TransactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco;//交易账号
    private String confirm_flag;//确认标志
    private String confirm_flag_neq;//确认标志不等于
    private String fix_businflag;//过滤业务辅助代码
    private String kjzt_company_no;//基金通机构编码
    private String protocol_name;//协议名称
    private String tax_advan_flag;//税优确认标志
}
