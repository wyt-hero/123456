package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 16:25
 * 交易结果查询(S407)
 */
@Data
public class TradingResultDTO {
    private CodeMessageDTO codeMessageEntity;
    private Integer rowcount;//记录数
    private Integer total_count;//总记录数
    private TradeResultQuerysDTO tradeResultQuerysEntity;

}
