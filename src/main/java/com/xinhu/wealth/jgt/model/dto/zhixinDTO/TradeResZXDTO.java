package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 18:36
 * 2.5 交易申请结果回写
 */
@Data
public class TradeResZXDTO {
    private String channel ;//交易渠道
    private String signmsg   ;//签名信息
    private String trust_way   ;//交易委托方式
    private String usertype   ;//商户类型
    private String rpcSign   ;//是否对接微服务
    private String tenantId   ;//租户ID
    private String sysId   ;//应用系统编号
    private String sysEntryId   ;//应用系统入口
    @SerializedName(value = "allot_no", alternate = "Oserialno")
    @JSONField(name = "allot_no")
    private String allot_no   ;//申请编号
    private String apply_date   ;//申请日期
    private String begin_date   ;//起始日期
    private String businflag   ;//业务类型
    private String capital_mode   ;//资金方式
    private String chinapay_serial_no   ;//银商流水号
    private String client_id   ;//客户编号
    private String confirm_state   ;//确认状态
    private String end_date   ;//到期日期
    private String filter_businflag   ;//过滤业务类型
    private String filter_businflags   ;//过滤业务代码
    private String filter_trade_source   ;//交易来源（不等于）
    private String fix_businflag   ;//过滤业务辅助代码
    private String fund_code   ;//基金代码
    private String fund_sub_type   ;//基金子类型
    private String fund_sub_type2   ;//基金子类型2
    private String is_revocation     ;//是否撤单
    private String ofund_type     ;//基金类型
    private String order_end_date     ;//下单到期日期
    private String order_start_date     ;//下单起始日期
    private String query_light     ;//轻量查询
    private String request_num     ;//每页记录数
    private String request_pageno     ;//请求页码
    private String sort_way     ;//排序方式
    @SerializedName(value = "ta_acco", alternate = "TAAccountID")
    @JSONField(name = "ta_acco")
    private String ta_acco     ;//TA账号/基金账号
    @SerializedName(value = "trade_acco", alternate = "TransactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco     ;//交易账号
    private String trade_source     ;//交易来源
    private String busin_ass_code     ;//业务辅助代码
    private String private_flag     ;//私募标识
}
