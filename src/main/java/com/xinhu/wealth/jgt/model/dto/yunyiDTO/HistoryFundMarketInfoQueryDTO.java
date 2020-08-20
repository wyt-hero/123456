package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:22
 * 历史基金行情查询(S429)child
 */
@Data
public class HistoryFundMarketInfoQueryDTO {
    private String fund_code;// 基金代码
    private float fund_curr_ratio;// 七日年化收益率
    private float fund_income;// 基金收益
    private String fund_name;// 基金名称
    private String fund_status;// 基金状态
    private float fund_total_share;// 基金总份额
    private float nav_date;// 净值日期
    private float nav_total;// 累计净值
    private float net_value;// 基金净值
    private float per_myriad_income;// 万份基金单位收益
    private String share_type;// 份额类别
    private String tax_reduction_fund;// 税优基金
    private String tax_reduction_share_type;// 个税递延份额类别
}
