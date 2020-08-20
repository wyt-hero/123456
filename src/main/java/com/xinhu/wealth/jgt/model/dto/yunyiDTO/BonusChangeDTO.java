package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 16:17
 */
//T514修改分红方式
@Data
public class BonusChangeDTO {
    private String code;
    private String message;
    private String allot_no;//申请编号
    private String apply_date;//申请日期
}
