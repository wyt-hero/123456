package com.xinhu.wealth.jgt.mapper;

import com.xinhu.wealth.jgt.model.entity.TradeIncreaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author wyt
 * @data 2020/4/16 10:29
 * 基金账户增开
 */
@tk.mybatis.mapper.annotation.RegisterMapper
@Mapper
@Component
public interface TradeMapper extends BaseMapper<TradeIncreaseEntity> {
}
