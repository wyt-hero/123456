package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 21:02
 * S443
 */
@Data
public class TAMesZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口
    @SerializedName(value = "fund_code", alternate = "Fundcode")
    @JSONField(name = "fund_code")
    private String fund_code  ;//基金代码
    private String ta_code  ;//TA代码
}
