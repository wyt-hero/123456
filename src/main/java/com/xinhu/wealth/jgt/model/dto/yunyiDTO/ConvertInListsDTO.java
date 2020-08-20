package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:11
 * 可转换入基金列表查询(T414)child
 */
@Data
public class ConvertInListsDTO {
    private String accept_hq_date;//接收行情日期
    private String businflag;//业务代码
    private String forbid_modi_autobuy_flag;//禁止修改分红方式标志
    private String fund_code;//基金代码
    private float fund_curr_income;//基金收益
    private float fund_curr_ratio;//基金收益率
    private String fund_full_name;//基金全称
    private String fund_name;//基金名称
    private float fund_share;//基金总份额
    private String fund_status;//基金状态
    private String fund_sub_type;//基金子类型
    private String hq_date;//行情日期
    private boolean is_collect_product;//是否集合类产品
    private float nav;//T-1日基金单位净值
    private float nav_total;//累计净值
    private String ofund_risklevel;//基金风险等级
    private String ofund_type;//基金类型
    private float per_myriad_income;//万份单位收益
    private float pre_income_ratio;//预期年化收益率
    private String prod_term;//产品期限
    private String share_type;//份额分类
    private String ta_no;//TA编号
}
