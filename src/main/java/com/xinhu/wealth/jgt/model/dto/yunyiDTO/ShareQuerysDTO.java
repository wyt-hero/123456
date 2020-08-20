package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 17:09
 * 份额查询(S403)child
 */
@Data
public class ShareQuerysDTO {
    private float accum_income;//累计收益
    private String auto_buy;//分红方式
    private float available_due_share;//可用到期份额
    private float available_onway_share;//可用在途资产
    private String bank_account;//银行账号
    private String bank_no;//银行代码
    private float business_frozen_amount;//交易冻结数量
    private String capital_mode;//资金方式
    private String client_type;//用户类型
    private String current_share;//当前份额
    private String cust_type;//客户类别
    private float dxcb_fast_redeem_share;//代销超宝可快速赎回份额
    private float enable_shares;//可用份额
    private float excetrans_in_total_quota;//当日赎回转购出总额
    private float excetrans_out_total_quota;//当日赎回转购入总额
    private String forbid_modi_autobuy_flag;//禁止修改分红方式标志
    private float frozen_nopay_income;//冻结未付收益
    private float frozen_share;//基金冻结数量
    private float frozen_value;//冻结市值
    private String fund_code;//基金代码
    private String fund_name;//基金名称
    private String fund_status;//基金状态
    private String has_share;//是否有过份额
    private float last_nav;//上一日净值
    private float lock_share;//最低持有期限内被锁定的份额
    private String money_type;//币种类别
    private float natural_valid_rapid_share;//单个投资人单个自然日此产品累积快速取现金额
    private float need_confirm_bala;//待确认金额
    private float need_transfer_bala;//待划款金额
    private float net_value;//单位净值
    private String net_value_date;//净值日期
    private String ofund_type;//基金类型
    private float quick_exceed_enable_share;//快速赎回可用份额
    private float realtime_onway_bala;//全程T0实时资金
    private String return_path;//兑付路径
    private String share_type;//份额分类
    private String special_acco;//特殊交易账号
    private String super_wallet_flag;//超级钱包标识
    private String ta_acco;//TA账号/基金账号
    private String ta_no;//TA编号
    private float today_apply_total_quota;//当日申购总额
    private float today_confirm_share;//当日确认份额
    private float today_exceed_total_quota;//当日赎回总额
    private float today_income;//每日收益
    private float today_transin_total_quota;//当日转入总额
    private float today_transout_total_quota;//当日转出总额
    private float total_bala;//总金额
    private String trade_acco;//交易账号
    private String trade_source;//交易来源
    private float unpaid_income;//未付收益金额
    private float used_unconfirm_share;//已用在途资产
    private float valid_rapid_share;//单日此产品累计快速取现金额
    private float worth_value;//市值
    private String xzb_flag;//薪资宝标识
}
