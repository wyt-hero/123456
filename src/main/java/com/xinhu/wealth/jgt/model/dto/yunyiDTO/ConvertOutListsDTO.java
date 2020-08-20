package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 09:21
 * 可转换出基金列表查询(T413)child
 */
@Data
public class ConvertOutListsDTO {
    private String auto_buy;//分红方式
    private String bank_account;//银行账号
    private String bank_no;//银行代码
    private float business_frozen_amount;//交易冻结数量
    private String capital_mode;//资金方式
    private float current_share;//当前份额
    private float enable_shares;//可用份额
    private float frozen_share;//基金冻结数量
    private String fund_code;//基金代码
    private float last_nav;//上一日净值
    private String net_value_date;//净值日期
    private String share_type;//份额分类
    private String ta_acco;//TA账号/基金账号
    private String trade_acco;//交易账号
    private float unpaid_income;//未付收益金额
    private float worth_value;//市值
}
