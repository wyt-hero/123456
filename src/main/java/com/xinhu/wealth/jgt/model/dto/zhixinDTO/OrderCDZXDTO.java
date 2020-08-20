package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 19:50
 *撤单(T516)
 */
@Data
public class OrderCDZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口

    @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco  ;//交易账号
    private String apply_time  ;//申请时间
    private String busin_board_type  ;//业务大类
    private String chinapay_serial_no  ;//银商流水号
    private String comb_request_no  ;//组合申请编号
    private String order_date  ;//下单日期

    @SerializedName(value = "original_appno", alternate = "orgAppserialno")
    @JSONField(name = "original_appno")
    private String original_appno  ;//原申请单编号
    private String password  ;//密码
    private String subacco_no  ;//子账号编号
    private String trade_return_flags  ;//交易退款标识
    private String trade_source  ;//交易来源
}
