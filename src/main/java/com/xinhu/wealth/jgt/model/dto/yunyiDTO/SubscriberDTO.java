package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wyt
 * @data 2020/3/4 15:29
 */
//T501认购返回值,基金申购(T502)(返回值相同，用一个)
@Data
public class SubscriberDTO implements Serializable {
    private String code;
    private String message;
    private String allot_no;//申请编号
    private String apply_date;//申请日期
    private String apply_time;//申请时间
    private float balance;//发生金额
    private String clear_date;//清算日期

}
