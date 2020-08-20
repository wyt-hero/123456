package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 14:40
 * 3.5交易账户对应的对手方银行账户查询---------S429历史基金行情查询
 * error  可能没有
 */
@Data
public class HistoryFundMarketDTO {

    private String TransactionAccountID;//交易账户
    private String PartyBankAccountId;//对手方银行账户号
    private String PartyBankAccountName;//对手方银行账户名称
    private String LargePayNum;//支付系统号
    private String BankName;//开户银行名称
    private String BankInfo;//银行总行
    private String BankProvince;//所在省份
    private String BankCity;//所在城市
}
