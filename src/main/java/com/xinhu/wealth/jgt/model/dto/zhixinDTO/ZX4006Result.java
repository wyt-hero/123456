package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/16 15:28
 * 机构基金账号登记(C512)
 */
@Data
public class ZX4006Result {
    private String code ;//返回错误码
    private String allot_no   ;//申请编号
    private String apply_date   ;//申请日期
    private String client_id   ;//客户编号
    private String message   ;//返回错误信息
    private String ta_acco   ;//TA账号
    private String trade_acco   ;//交易账号
    private String ZXResult;
}
