package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/28 17:45
 */
@Data
public class FundAccountQuerysDTO {
    private String bank_account;//银行账号
    private String bank_no;//银行代码
    private String capital_mode;//资金方式
    private String cd_card;//银联CD卡号
    private String client_id;//客户编号
    private String client_name;//客户名称
    private String come_from;//交易来源
    private String detail_fund_way;//明细资金方式
    private String ect_cid;//外部客户客户号
    private String ect_uid;//外部客户用户号
    private String fund_interface_type;//接口类型
    private String id_kind_gb;//证件类型
    private String id_no;//证件号码
    private String is_main_trade_acco;//是否主交易账号
    private String lock_date;//锁定日期
    private String mobile_tel;//手机号码
    private String net_trust;//允许网上委托
    private String ofund_user_type;//用户类型
    private String openacco_date;//开户日期
    private String other_user_id;//对方平台ID
    private String phone_trust;//允许电话委托
    private String pwd_errors;//密码错误次数
    private String ta_acco;//TA账号/基金账号
    private String ta_no;//TA代码
    private String trade_acco;//交易账号
    private String trade_acco_status;//交易账号状态
    private String trans_account_status;//基金账号状态
    private String bank_mobile_no;//银行预留手机号
    private String cap_acco_status;//资金账号状态
    private String invest_acco;//投资账户
    private String password_active;//密码激活状态
}
