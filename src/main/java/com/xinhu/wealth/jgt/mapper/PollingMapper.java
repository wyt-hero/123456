package com.xinhu.wealth.jgt.mapper;

import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author wyt
 * @data 2020/3/25 15:44
 */
@tk.mybatis.mapper.annotation.RegisterMapper
@Mapper
@Component
public interface PollingMapper extends BaseMapper<PollingEntity>{

    List<PollingEntity> selectPollingByTime(@Param("time") String time, @Param("agoTime") String agoTime);

    /**
     * 查询确认结果
     * @return
     */
    List<PollingEntity> selectConfirmResult();

    List<PollingEntity> accountSearchV1(@Param("time") String time);
}
