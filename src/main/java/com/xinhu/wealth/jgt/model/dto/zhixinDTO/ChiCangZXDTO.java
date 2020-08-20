package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 18:56
 * 份额查询(S403)
 */
@Data
public class ChiCangZXDTO {
    private String channel ;//交易渠道
    private String signmsg   ;//签名信息
    private String trust_way   ;//交易委托方式
    private String usertype   ;//商户类型
    private String rpcSign   ;//是否对接微服务
    private String tenantId   ;//租户ID
    private String sysId   ;//应用系统编号
    private String sysEntryId   ;//应用系统入口
    private String auto_buy     ;//分红方式
    private String bank_no     ;//银行代码
    //@SerializedName(value = "client_id", alternate = "InvestorCode")
    //@JSONField(name = "client_id")
    private String client_id     ;//客户编号
    @SerializedName(value = "fund_code", alternate = "Fundcode")
    @JSONField(name = "fund_code")
    private String fund_code     ;//基金代码
    private String fund_sub_type     ;//基金子类型
    private String fund_sub_type2     ;//基金子类型2
    private String fund_sub_types     ;//基金子类型
    private String is_frozen_reservedshares     ;//是否冻结预约份额
    private String is_money_fund     ;//是否现金宝
    private String isFilter     ;//是否过滤
    private String ofund_type     ;//基金类型
    private String query_type     ;//查询类别

    @SerializedName(value = "request_num", alternate = "RowSize")
    @JSONField(name = "request_num")
    private String request_num     ;//每页记录数

    @SerializedName(value = "request_pageno", alternate = "PageIndex")
    @JSONField(name = "request_pageno")
    private String request_pageno     ;//请求页码
    private String share_type     ;//份额分类
    private String special_acco     ;//特殊交易账号

    @SerializedName(value = "ta_acco", alternate = "TAAccountID")
    @JSONField(name = "ta_acco")
    private String ta_acco       ;//TA账号/基金账号
    @SerializedName(value = "trade_acco", alternate = "TransactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco     ;//交易账号
    private String trade_source     ;//交易来源
    private String fund_style_flag     ;//公私募查询标志
    private String user_type     ;//用户类型
    private String xzb_flag     ;//薪资宝标志
    private String fund_type_neq       ;//基金类型不等于
    private String super_wallet_flag       ;//超级钱包标识
    private String user_type_neq       ;//用户类型不等于
}
