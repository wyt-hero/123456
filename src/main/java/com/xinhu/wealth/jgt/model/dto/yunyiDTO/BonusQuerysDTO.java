package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 16:53
 * 分红查询(S420)child
 */
@Data
public class BonusQuerysDTO {
    private String affirm_date;//确认日期
    private String auto_buy;//分红方式
    private String bank_account;//银行账号
    private String bank_no;//银行编号
    private String bill_money_type;//结算币种
    private float bonus_share;//红股份额
    private String bonus_unit;//分红单位
    private String capital_mode;//资金方式
    private String dividend_date;//分红日-发放日
    private float dividend_totalbala;//红利总金额
    private String equity_reg_date;//权益登记日
    private String ex_dividend_date;//除息日
    private float fare_sx;//手续费
    private float frozen_balance;//冻结金额
    private float frozen_shares;//冻结份额
    private String fund_code;//基金代码
    private String fund_name;//基金名称
    private String fund_sub_type;//基金子类型
    private float income_tax;//所得税
    private float net_value;//净值
    private float real_balance;//实发红利金额
    private float reg_share;//登记份额
    private String share_type;//份额类别
    private String ta_acco;//基金账号
    private String ta_serial_id;//TA确认编号
    private String trade_acco;//交易账号
    private float unit_bala;//每股分红金额
    private float unit_share;//每股分红股数
    private String xzb_flag; //薪资宝标识
}
