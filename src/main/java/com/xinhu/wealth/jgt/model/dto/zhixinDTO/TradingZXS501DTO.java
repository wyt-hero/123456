package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 21:18
 * 登录验证(C501)
 */
@Data
public class TradingZXS501DTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口

    private String  apply_time  ;//申请时间
    private String client_id  ;//客户编号
    private String id_kind_gb  ;//证件类别
    private String id_no  ;//证件号码
    private String order_date    ;//下单日期
    private String password    ;//密码
    private String ta_acco    ;//TA账号

    @SerializedName(value = "trade_acco", alternate = "TransactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco    ;//交易账号
    private String trade_source    ;//交易来源
}
