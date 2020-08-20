package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:28
 * 机构开户(C503)
 */
@Data
public class InstitutionsAccountDTO {
    private String code;//返回错误码
    private String allot_no; //申请编号
    private String apply_date; //申请日期
    private String client_id; //客户编号
    private String ecif_id;// ECIF编号
    private String message  ;// 返回错误信息
    private String product_id  ;// 产品ID
    private String ta_acco; //TA账号/基金账号
    private String trade_acco; //交易账号
}
