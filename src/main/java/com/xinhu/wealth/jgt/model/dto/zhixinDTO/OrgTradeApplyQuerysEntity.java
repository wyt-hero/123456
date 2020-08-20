package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/30 10:21
 * 机构交易申请查询(S501)
 */
@Data
public class OrgTradeApplyQuerysEntity {
    private String accept_time;// 下单时间
    private String after_discount_rate;// 后收费折扣率
    private String allot_no;// 申请编号
    private String apply_date;// 申请日期
    private String apply_time;// 申请时间
    private String auto_buy;// 分红方式
    private String balance;// 发生金额
    private String bank_account;// 银行账号
    private String bank_no;// 银行代码
    private String busin_ass_code;// 业务辅助代码
    private String busin_board_type;// 业务大类
    private String capital_mode;// 资金方式
    private String comb_request_no;// 组合申请编号
    private String combined_error_info;// 银联错误原因
    private String come_from;// 交易来源
    private String confirm_flag;// 确认标志
    private String cust_confirm;// 审核标志
    private String cyber_bank_error_id;// 网银错误代码
    private String deduct_status;// 扣款状态
    private String detail_fund_way;// 明细资金方式
    private String discount_rate;// 折扣比率
    private String expiry_date;// 终止日期
    private String explain;// 备注信息
    private String first_exchdate;// 首次交易日期
    private String fix_date;// 定投日
    private String fund_busin_code;// 业务代码
    private String fund_code;// 基金代码
    private String fund_exceed_flag;// 巨额赎回标志
    private String fund_froze_flag;// 冻结方式
    private String fund_name;// 基金名称
    private String fund_risk_flag;// 风险标志
    private String max_succ_times;// 最大成功次数
    private String max_trade_succ_quota;// 最大交易成功额度
    private String money_type;// 币种类别
    private String ofund_type;// 基金类型
    private String oppo_agency;// 对方销售商
    private String oppo_fund_account;// 对方基金账号
    private String oppo_netno;// 对方销售网点编号
    private String oppo_trade_account;// 对方交易账号
    private String original_appno;// 原申请单编号
    private String original_date;// 原申请日期
    private String protocol_name;// 协议名称
    private String protocol_period_unit;// 协议周期单位
    private String range;// 级差
    private String receivable_account;// 回款账户
    private String reserve_discount_rate;// 补差费折扣率
    private String scheduled_protocol_id;// 定投协议号
    private String send_flags;// 发送标志
    private String share_type;// 份额分类
    private String shares;// 发生份额
    private String subacco_name;// 子账户名称
    private String subacco_no;// 子账户编号
    private String ta_acco;// TA账号
    private String target_fund_code;// 目标基金代码
    private String target_fund_type;// 目标基金类型
    private String trade_acco;// 交易账号
    private String trade_period;// 交易周期
    private String trust_way;// 委托方式
}
