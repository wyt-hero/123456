package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 17:39
 * 基金行情信息查询（三方）(K406)
 */
@Data
public class FundMarketSearchDTO {
    private CodeMessageDTO codeMessageEntity;
    private Integer rowcount;//记录数
    private Integer total_count;//总记录数
    private NetValueQuerysDTO netValueQuerysEntity;
}
