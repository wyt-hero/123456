package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:49
 * 机构增开(C504)
 */
@Data
public class InstitutionsAddDTO {
    //private CodeMessageDTO codeMessageEntity;
    private String code;//返回错误码
    private String message;//返回错误信息
    private String allot_no;//申请编号
    private String invest_risk_tolerance;//投资人风险承受能力
    private String trade_acco;//交易账号
}
