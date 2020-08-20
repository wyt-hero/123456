package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 16:20
 * T519撤单
 */
@Data
public class CancellationsDTO {
    private String code;
    private String message;
    private String allot_no;// 申请编号
    private String apply_date;//申请日期
    private String apply_time;//申请时间
}
