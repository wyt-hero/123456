package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/3 11:00
 * 根据银行编号查询银行名称
 */
@Data
public class BankNameResDTO {
    private String code;// 返回错误码
    private String message;//  返回错误信息
    private String caption;// 中文描述
    private String keyvalue;// 词条键值
    private String memo;// 备注
}
