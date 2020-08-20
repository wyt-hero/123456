package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 16:10
 */
//T518基金转换
@Data
public class FundChangeDTO {
    private String code;
    private String message;
    private String allot_no;//申请编号
    private String apply_date;//申请日期
    private float exist_sum_limit;//存在累计限额
    private float sum_limit;//累计限额
}
