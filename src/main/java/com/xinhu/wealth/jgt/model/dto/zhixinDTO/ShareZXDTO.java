package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 19:11
 *分红查询(S420)
 */
@Data
public class ShareZXDTO {
    private String channel ;//交易渠道
    private String signmsg   ;//签名信息
    private String trust_way   ;//交易委托方式
    private String usertype   ;//商户类型
    private String rpcSign   ;//是否对接微服务
    private String tenantId   ;//租户ID
    private String sysId   ;//应用系统编号
    private String sysEntryId   ;//应用系统入口
    private String auto_buy     ;//分红方式

    @SerializedName(value = "begin_date",alternate = "Fdate")
    @JSONField(name = "begin_date")
    private String begin_date     ;//起始日期
    private String busin_board_type     ;//业务大类
    private String capital_mode     ;//资金方式
    private String client_id     ;//客户编号
    private String end_date     ;//结束日期
    private String filter_type     ;//过滤基金类型
    private String fund_code     ;//基金代码
    private String ofund_type     ;//基金类型
    private String query_light     ;//轻量查询

    @SerializedName(value = "request_num",alternate = "RowSize")
    @JSONField(name = "request_num")
    private String request_num     ;//每页记录数

    @SerializedName(value = "request_pageno",alternate = "PageIndex")
    @JSONField(name = "request_pageno")
    private String request_pageno     ;//请求页码
    private String sort_way     ;//排序方式

    @SerializedName(value = "trade_acco",alternate = "TransactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco       ;//交易账号
    private String trade_source       ;//交易来源
    private String private_flag       ;//私募标识
    private String special_trade       ;//专户标识
    private String ta_serial_id       ;//TA确认编号
    private String xzb_flag       ;//薪资宝标识
    private String user_type       ;//用户类型
    private String user_type_neq      ;//用户类型(<>)
}
