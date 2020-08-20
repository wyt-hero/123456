package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @date 2020-07-16-13-58-46
 * 此类用于存储收益类字段
 */
@Data
public class FoundIncomeDTO {
    private String income_by_hold="0.00";//持有收益
    private String holding_income="0.00";//持仓收益
    private String accum_income="0.00";//累计收入
    private String fund_curr_income="0.00";//基金收益
}
