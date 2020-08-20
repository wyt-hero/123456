package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/14 11:05
 * 此类为3.1行情信息查询返回值的其中三个字段
 */
@Data
public class PriceIncreaseDTO {

    /**
     * 七日年化收益率
     */
    private String income_ratio="0";
    /**
     * 周涨跌
     */
    private String price_increase_week="0";
    /**
     *  月涨幅
     */
    private String price_increase_month1="0";
    /**
     *  近一年涨幅
     */
    private String price_increase_year="0";
}
