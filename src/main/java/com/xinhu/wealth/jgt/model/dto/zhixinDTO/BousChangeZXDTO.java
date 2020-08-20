package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 20:13
 * 修改分红方式(T514)
 */
@Data
public class BousChangeZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口

    @SerializedName(value = "auto_buy", alternate = "defDividendMethod")
    @JSONField(name = "auto_buy")
    private String auto_buy  ;//分红方式
    @SerializedName(value = "fund_code", alternate = "fundcode")
    @JSONField(name = "fund_code")
    private String fund_code  ;//基金代码

    private String share_type ="A" ;//份额分类

    @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco  ;//交易账号
    private String apply_time  ;//申请时间
    private String order_date  ;//下单日期
    private String password  ;//密码
    private String sub_acco  ;//子账户编号
    private String ta_acco  ;//TA账号
    private String trade_conduct_person  ;//交易经办人
    private String trade_source  ;//交易来源
}
