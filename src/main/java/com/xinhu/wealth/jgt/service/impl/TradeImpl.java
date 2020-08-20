package com.xinhu.wealth.jgt.service.impl;

import com.xinhu.wealth.jgt.mapper.TradeMapper;
import com.xinhu.wealth.jgt.model.entity.TradeIncreaseEntity;
import com.xinhu.wealth.jgt.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wyt
 * @data 2020/4/16 10:36
 */
@Service
@Slf4j
public class TradeImpl implements TradeService {
    @Autowired
    TradeMapper tradeMapper;

    /**
     * 添加
     * @param tradeIncreaseEntity
     * @return
     */
    @Override
    public boolean addTrade(TradeIncreaseEntity tradeIncreaseEntity) {
        log.info("基金账户入库参数：{}",tradeIncreaseEntity);
        int insert = tradeMapper.insert(tradeIncreaseEntity);
        boolean bool=insert>0;
        return bool;
    }

    /**
     * 修改
     * @param tradeIncreaseEntity
     * @return
     */
    @Override
    public boolean modifyTrade(TradeIncreaseEntity tradeIncreaseEntity) {
        log.info("基金账户修改入参：{}",tradeIncreaseEntity);
        tradeIncreaseEntity.setCreateTime(null);
        int i = tradeMapper.updateByPrimaryKeySelective(tradeIncreaseEntity);
        boolean bool=i>0;
        return bool;
    }

    /**
     * 验证
     * @param tradeIncreaseEntity
     * @return
     */
    @Override
    public boolean checkTrade(TradeIncreaseEntity tradeIncreaseEntity) {
        log.info("基金账户验证入参：{}",tradeIncreaseEntity);
        tradeIncreaseEntity.setCreateTime(null);
        List<TradeIncreaseEntity> select = tradeMapper.select(tradeIncreaseEntity);
        boolean bool=select.size()==0;//等于零可以添加
        return bool;
    }

    /**
     * 查询
     * @param tradeIncreaseEntity
     * @return
     */
    @Override
    public List<TradeIncreaseEntity> queryTrade(TradeIncreaseEntity tradeIncreaseEntity) {
        log.info("基金账户查询入参：{}",tradeIncreaseEntity);
        tradeIncreaseEntity.setCreateTime(null);
        List<TradeIncreaseEntity> select = tradeMapper.select(tradeIncreaseEntity);
        return select;
    }
}
