package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 17:53
 */
@Data
public class OrgnLogoutZXDTO {
//    private String channel ;//交易渠道
//    private String signmsg   ;//签名信息
//    private String trust_way   ;//交易委托方式
//    private String usertype   ;//商户类型
//    private String rpcSign   ;//是否对接微服务
//    private String tenantId   ;//租户ID
//    private String sysId   ;//应用系统编号
    @SerializedName(value = "ta_acco", alternate = "tAAccountID")
    @JSONField(name = "ta_acco")
    private String taAcco   ;//TA账号

//    private String password   ;//密码
}
