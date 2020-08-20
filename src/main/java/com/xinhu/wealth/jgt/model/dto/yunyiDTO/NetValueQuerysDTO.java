package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 17:42
 * 基金行情信息查询（三方）(K406)child
 */
@Data
public class NetValueQuerysDTO {
    private float asset_size;//资产规模
    private String fund_code;//基金代码
    private String fund_manager;//基金管理人
    private String fund_manager_code;//基金管理人代码
    private String fund_name;//基金名称
    private String fund_status;//基金状态
    private boolean is_support_fixedinvest;//是否可以定投
    private boolean is_support_purchase;//是否可做申购
    private boolean is_support_subscribe;//是否可做认购
    private float max_fixinvest_amount;//最大定投金额
    private float max_purchase_amount;//最大申购金额
    private float min_fixinvest_amount;//最小定投金额
    private float min_purchase_amount;//最小申购金额
    private String nav_date;//净值日期
    private float nav_total;//累计净值
    private float net_value;//产品当前净值
    private String ofund_risklevel;//基金风险等级
    private String ofund_type;//基金类型
    private float per_myriad_income;//万份单位收益
    private float pre_income_ratio;//预期年化收益率
    private String price_increase_day;//日涨幅
    private String price_increase_month1;//月涨幅
    private String price_increase_month3;//季涨幅
    private String price_increase_month6;//半年涨幅
    private String price_increase_setup;//成立以来涨跌
    private String price_increase_thisyear;//今年以来涨跌
    private String price_increase_threeyear;//三年涨跌
    private String price_increase_week;//周涨跌
    private String price_increase_year;//近一年涨幅
    private String back_fund_code;//后收费基金代码
    private String front_fund_code;//前收费基金代码
    private String full_fund_name;//基金名称全称
    private float income_ratio;//七日年化收益率
    private String price_increase_twoyear;//两年涨幅
}
