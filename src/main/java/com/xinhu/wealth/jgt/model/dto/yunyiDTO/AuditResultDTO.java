package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:35
 * 柜台审核结果查询(C479)
 */
@Data
public class AuditResultDTO {
    private CodeMessageDTO codeMessageEntity;
    private CounterAuditResultQuerysDTO counterAuditResultQuerysEntity;
}
