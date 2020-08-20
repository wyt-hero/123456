package com.xinhu.wealth.jgt.mapper;

import com.xinhu.wealth.jgt.model.entity.CancleAccEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author wyt
 * @data 2020/4/10 09:31
 */
@tk.mybatis.mapper.annotation.RegisterMapper
@Mapper
@Component
public interface CancleAccMapper extends BaseMapper<CancleAccEntity> {


    /**
     * 查询销户状态成功的数据
     * @return
     */
    List<CancleAccEntity> selectConfirm();
}
