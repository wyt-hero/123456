package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 15:56
 */
//赎回(T513)
@Data
public class RedemptionDTO {
    private String code;
    private String message;
    private String allot_no;//申请编号
    private String apply_date;//申请日期
    private String apply_time;//申请时间
    private float balance;//发生金额
    private String clear_date;//清算日期
    private String curr_date;//当前日期
    private float enable_shares;//可用份额
    private float frozen_shares;//冻结份额
    private float fund_share;//基金总份额
    private float nodefault_total_share;//不违约总份额
    private float nopay_income;//未付收益
    private boolean penalty_flag;//违约标志
    private float today_frozen_share;//当日冻结份额
    private float today_income;//每日收益
    private float today_total_share;//当日总份额
}
