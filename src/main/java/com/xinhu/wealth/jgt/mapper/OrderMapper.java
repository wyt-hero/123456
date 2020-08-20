package com.xinhu.wealth.jgt.mapper;

import com.xinhu.wealth.jgt.model.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author wyt
 * @data 2020/4/9 18:04
 */
@tk.mybatis.mapper.annotation.RegisterMapper
@Mapper
@Component
public interface OrderMapper extends BaseMapper<OrderEntity> {
}
