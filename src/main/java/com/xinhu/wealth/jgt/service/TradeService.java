package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.entity.TradeIncreaseEntity;

import java.util.List;

/**
 * @author wyt
 * @data 2020/4/16 10:31
 */
public interface TradeService {
    /**
     * 增加
     * @param tradeIncreaseEntity
     * @return
     */
    boolean addTrade(TradeIncreaseEntity tradeIncreaseEntity);

    /**
     * 修改数据
     * @param tradeIncreaseEntity
     * @return
     */
    boolean modifyTrade(TradeIncreaseEntity tradeIncreaseEntity);

    /**
     * 验证数据是否存在
     * @param tradeIncreaseEntity
     * @return
     */
    boolean checkTrade(TradeIncreaseEntity tradeIncreaseEntity);

    /**
     * 条件查询
     * @param tradeIncreaseEntity
     * @return
     */
    List<TradeIncreaseEntity> queryTrade(TradeIncreaseEntity tradeIncreaseEntity);
}
