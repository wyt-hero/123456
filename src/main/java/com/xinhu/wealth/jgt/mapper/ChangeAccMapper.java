package com.xinhu.wealth.jgt.mapper;

import com.xinhu.wealth.jgt.model.entity.ChangeAccEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author wyt
 * @data 2020/4/17 11:55
 */
@Mapper
@RegisterMapper
@Component
public interface ChangeAccMapper extends BaseMapper<ChangeAccEntity> {
}
