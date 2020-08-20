package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 20:19
 * 赎回(T513)
 */
@Data
public class RedeemZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口
    @SerializedName(value = "fund_code", alternate = "fundcode")
    @JSONField(name = "fund_code")
    private String fund_code;//基金代码

    private String share_type = "A";//份额类别

    @SerializedName(value = "shares", alternate = "appvol")
    @JSONField(name = "shares")
    private String shares;//发生份额
    @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco;//交易账号

    private String apply_time;//申请时间
    private String bank_acco;//银行账号
    private String bank_no;//银行编号
    private String busin_board_type;//业务大类
    private String capital_mode = "K";//资金方式
    private String chinapay_serial_no;//银商流水号
    @SerializedName(value = "fund_exceed_flag", alternate = "largeRedemptionFlag")
    @JSONField(name = "fund_exceed_flag")
    private String fund_exceed_flag;//巨额赎回标志
    private String order_date;//下单日期
    private String password;//密码
    private String sub_acco;//子账户编号
    private String target_fund_code;//对方基金代码
    private String target_share_type;//对方份额类别
    private String trade_source;//交易来源
    private String pre_check_flag;//预检查标识
    private String broker;//经纪人
}
