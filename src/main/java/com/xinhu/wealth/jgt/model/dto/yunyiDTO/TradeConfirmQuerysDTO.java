package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 13:49
 * S416 交易成交查询
 */
@Data
public class TradeConfirmQuerysDTO {
    private float acc_tax_pre_takeoff; //累计税前扣除金额
    private String accept_date; //下单日期
    private String accept_time; //下单时间
    private String affirm_date; //确认日期
    private String allot_no; //申请编号
    private String apply_date; //申请日期
    private String auto_buy; //分红方式
    private String bank_account; //银行账号
    private String bank_no; //银行代码
    private String busin_ass_code; //业务辅助代码
    private String capital_mode; //资金方式
    private String cert_code; //凭证编号
    private String cert_date; //凭证日期
    private String come_from; //交易来源
    private String confirm_flag; //确认标志
    private String cust_no; //客户编号
    private String custom_name; //客户名称的全称
    private String fail_cause; //失败原因
    private float fare_sx; //手续费
    private String fund_busin_code; //业务代码
    private String fund_code; //基金代码
    private String fund_froze_flag; //冻结方式
    private String fund_name; //基金名称
    private String identity_no; //投资人证件号码
    private String identity_type; //投资人证件类型
    private String money_type; //币种类别
    private String nationa_lity; //投资者国籍
    private float net_value; //净值
    private String netno; //交易网点代码
    private String ofund_type; //基金类型
    private String oppo_agency; //对方销售商
    private String oppo_fund_account; //对方基金账号
    private String oppo_netno; //对方销售网点编号
    private String original_appno; //原申请单编号
    private String original_date; //原申请日期
    private String return_code; //返回代码
    private String scheduled_protocol_id; //定投协议号
    private String share_type; //份额分类
    private float stamptax; //印花税
    private String ta_acco; //TA账号
    private float ta_fare; //ta收取的费用
    private String ta_serial_id; //TA确认编号
    private String target_fund_code; //目标基金代码
    private String target_fund_name; //目标基金名称
    private String target_share_type; //目标份额类型
    private float tax_advan_amount; //税优金额
    private String tax_advan_code; //税优码
    private String tax_serialno; //个税平台流水号
    private String trade_acco; //交易账号
    private float trade_confirm_balance; //交易确认金额
    private float trade_confirm_type; //交易确认份额
    private String trust_way; //交易委托方式
}
