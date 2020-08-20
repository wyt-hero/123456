package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:36
 * 柜台审核结果查询(C479)child
 */
@Data
public class CounterAuditResultQuerysDTO {
    private String cust_confirm;//审核标志
    private String audit_flag;//复核标志
    private String reject_reason;//驳回原因
    private String request_no;//申请编号
}
