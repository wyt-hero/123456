package com.xinhu.wealth.jgt.mapper;

import com.xinhu.wealth.jgt.model.entity.JgtPositionShareEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.BaseMapper;

@RegisterMapper
@Component
@Mapper
public interface JgtPositionShareMapper extends BaseMapper<JgtPositionShareEntity> {
}
