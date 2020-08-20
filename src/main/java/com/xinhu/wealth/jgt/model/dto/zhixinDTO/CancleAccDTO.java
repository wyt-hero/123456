package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/10 10:19
 */
@Data
public class CancleAccDTO {
    private String code;// 返回错误码
    private String allot_no;// 申请编号
    private String message;// 返回错误信息
}
