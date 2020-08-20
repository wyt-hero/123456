package com.xinhu.wealth.jgt.service.impl;

import com.xinhu.wealth.jgt.mapper.ChangeAccMapper;
import com.xinhu.wealth.jgt.model.entity.ChangeAccEntity;
import com.xinhu.wealth.jgt.service.ChangeAccService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyt
 * @data 2020/4/17 12:01
 */
@Service
@Slf4j
public class ChangeAccImpl implements ChangeAccService {
    @Autowired
    ChangeAccMapper changeAccMapper;


    /**
     * 添加数据库
     * @param changeAccEntity
     * @return
     */
    @Override
    public boolean addChangeAcc(ChangeAccEntity changeAccEntity) {
        changeAccEntity.setCreateTime(LocalDateTime.now().toString());
        log.info("添加账户会写入参：{}",changeAccEntity.toString());
        int i = changeAccMapper.insertSelective(changeAccEntity);
        log.info("添加账户会写结果：{}",i);
        return i>0;
    }

    /**
     * 修改数据库
     * @param changeAccEntity
     * @return
     */
    @Override
    public boolean modifyChangeAcc(ChangeAccEntity changeAccEntity) {
        changeAccEntity.setUpdateTime(LocalDateTime.now().toString());
        log.info("修改账户入参：{}",changeAccEntity.toString());
        int i = changeAccMapper.updateByPrimaryKeySelective(changeAccEntity);
        log.info("修改账户结果：{}",i);
        return i>0;
    }

    /**
     * 根据申请单编号判断是否存在数据
     * @param oserialno 流水号
     * @return
     */
    @Override
    public boolean checkChangeAcc(String oserialno) {
        log.info("检查账户入参：{}",oserialno);
        ChangeAccEntity changeAccEntity=new ChangeAccEntity();
        changeAccEntity.setOserialno(oserialno);
        List<ChangeAccEntity> select = changeAccMapper.select(changeAccEntity);
        log.info("检查账户结果：{}",select.toString());
        boolean bool=select.size()==0?true:false;
        return bool;
    }

    /**
     * 条件查询
     * @return
     */
    @Override
    public List<ChangeAccEntity> queryChangeAcc(ChangeAccEntity changeAccEntity) {
        log.info("查询账户入参：{}",changeAccEntity.toString());
        List<ChangeAccEntity> select = changeAccMapper.select(changeAccEntity);
        log.info("查询账户结果：{}",select.toString());
        return select;
    }


}
