package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 20:35
 * 基金认购(T501)
 */
@Data
public class RenGouZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口

    @SerializedName(value = "balance", alternate = "appamount")
    @JSONField(name = "balance")
    private String balance  ;//发生金额
    private String capital_mode ="K" ;//资金方式
    @SerializedName(value = "fund_code", alternate = "fundcode")
    @JSONField(name = "fund_code")
    private String fund_code  ;//基金代码
    private String share_type ="A"  ;//份额分类
    @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco  ;//交易账号

    private String activity_code  ;//活动代码
    private String apply_time  ;//申请时间
    private String auto_buy  ;//分红方式
    private String bank_account  ;//银行账号
    private String bank_no  ;//银行代码
    private String chinapay_serial_no  ;//银商流水号
    private String discount_rate  ;//折扣比率
    private String money_type  ;//币种类别
    private String order_date  ;//下单日期
    private String password ;//密码
    private String recommender  ;//推荐人
    private String recommender_phone  ;//推荐人手机号码
    private String subacco_no  ;//子账号编号
    private String trade_source  ;//交易来源
    private String extends_day    ;//顺延天数
    private String pre_check_flag  ;//预检查标识
    private String broker  ;//经纪人
}
