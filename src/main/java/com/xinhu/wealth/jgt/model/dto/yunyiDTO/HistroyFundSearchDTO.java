package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:20
 * 历史基金行情查询(S429)
 */
@Data
public class HistroyFundSearchDTO {
    private CodeMessageDTO codeMessageEntity;
    private Integer rowcount;//记录数
    private Integer total_count;//总记录数
    private HistoryFundMarketInfoQueryDTO historyFundMarketInfoQueryEntity;
}
