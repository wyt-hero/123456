package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/10 10:51
 */
@Data
public class AccountConfirmQuerys {
    private String code;//成功码
    private String affirm_date;// 确认日期
    private String allot_no  ;// 申请编号
    private String apply_date  ;// 申请日期
    private String apply_time  ;// 申请时间
    private String business_type  ;// 业务类型
    private String cd_card  ;// 银联CD卡号
    private String cofirm_cause  ;// 确认失败原因
    private String confirm_flag  ;// 确认标志
    private String confirm_id  ;// 确认编号
}
