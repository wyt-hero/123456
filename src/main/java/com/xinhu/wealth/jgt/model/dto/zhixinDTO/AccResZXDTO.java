package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 21:12
 * 柜台审核结果查询(C479)
 */
@Data
public class AccResZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口
    @SerializedName(value = "trade_acco", alternate = "TransactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco  ;//交易账号
    private String audit_flag  ;//复核标志
}
