package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 16:28
 * 交易结果查询(S407)child
 */
@Data
public class TradeResultQuerysDTO {
    private String affirm_date;//确认日期
    private String allot_no;//申请编号
    private String apply_date;//申请日期
    private float apply_share;//申请份数
    private String apply_time;//申请时间
    private String auto_buy;//分红方式
    private float balance;//申请金额
    private String bank_account;//银行帐号
    private String bank_name;//银行名称
    private String bank_no;//银行编号
    private String bank_protocol_id;//银行协议号
    private String busin_ass_code;//业务辅助代码
    private String calm_time;//冷静期时间
    private String capital_in_time;//资金存入时间
    private String capital_mode;//资金方式
    private String clear_date;//清算日期
    private String combined_error_info;//银联错误原因
    private String confirm_businflag;//确认业务类型
    private String confirm_state;//确认状态
    private float current_share;//当前份额
    private String cyber_bank_error_id;//网银错误代码
    private String deduct_status;//扣款状态
    private float enable_shares;//可用份额
    private float fare_sx;//手续费
    private float frozen_shares;//冻结份额
    private String fund_busin_code;//业务代码
    private String fund_code;//基金代码
    private String fund_name;//基金名称
    private float net_value;//净值
    private String net_value_date;//净值日期
    private String oppo_agency;//对方销售商
    private String oppo_netno;//对方销售网点编号
    private String order_date_time;//下单日期时间
    private float ori_balance;//原申请金额
    private String original_appno;//原申请单编号
    private String sale_code;//销售人代码
    private String scheduled_protocol_id;//定投协议号
    private String share_type;//份额类别
    private String split_flag;//分笔支付标识
    private String ta_acco;//TA账号/基金账号
    private String target_fund_code;//目标基金代码
    private String target_share_type;//目标份额类型
    private String trade_acco;//交易账号
    private float trade_confirm_balance;//交易确认金额
    private float trade_confirm_shares;//交易确认份额
    private String trade_status;//交易处理状态
}
