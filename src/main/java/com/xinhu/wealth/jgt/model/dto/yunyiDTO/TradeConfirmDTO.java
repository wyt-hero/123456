package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 13:47
 * 交易成交查询(S416)
 */
@Data
public class TradeConfirmDTO {
    private CodeMessageDTO codeMessageEntity;
    private TradeConfirmQuerysDTO tradeConfirmQuerysEntity;
}
