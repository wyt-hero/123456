package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 20:03
 * 基金转换(T518)
 */
@Data
public class FunChangZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口

    private String pre_check_flag  ;//预检查标识

    @SerializedName(value = "auto_buy", alternate = "defDividendMethod")
    @JSONField(name = "auto_buy")
    private String auto_buy  ;//分红方式

    private String channel_serial_id  ;//交易来源申请编号

    @SerializedName(value = "fund_code", alternate = "fundcode")
    @JSONField(name = "fund_code")
    private String fund_code  ;//基金代码

    private String machine_datetime  ;//机器时间
    private String oppo_trade_account  ;//对方交易账号
    private String password  ;//密码
    private String share_type ="A" ;//份额分类

    @SerializedName(value = "shares", alternate = "appvol")
    @JSONField(name = "shares")
    private String shares  ;//发生份额
    private String sub_acco  ;//子账户编号

    @SerializedName(value = "target_fund_code", alternate = "codeOfTargetFund")
    @JSONField(name = "target_fund_code")
    private String target_fund_code  ;//目标基金代码

    private String target_share_type ="A" ;//目标份额类型

    @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco  ;//交易账号

    private String trade_source  ;//交易来源
    private String broker    ;//经纪人
}
