package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/27 17:01
 */
@Data
public class TainfoQuerysDTO {
    private String multi_tradeacco;// 是否支持多交易账号
    private String ta_code  ;// TA代码
    private String ta_name  ;// TA名称
    private String ta_state  ;// TA状态
    private String ta_type  ;// TA类型
}
