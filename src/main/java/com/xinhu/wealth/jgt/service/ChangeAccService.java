package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.entity.ChangeAccEntity;

import java.util.List;

/**
 * @author wyt
 * @data 2020/4/17 11:57
 */
public interface ChangeAccService {
    /**
     * 添加数据库
     * @param changeAccEntity
     * @return
     */
    boolean addChangeAcc(ChangeAccEntity changeAccEntity);

    /**
     * 修改数据库
     * @param changeAccEntity
     * @return
     */
    boolean modifyChangeAcc(ChangeAccEntity changeAccEntity);

    /**
     * 根据申请单编号判断是否存在数据
     * @param oserialno
     * @return
     */
    boolean checkChangeAcc(String oserialno);

    /**
     * 条件查询
     * @return
     */
    List<ChangeAccEntity> queryChangeAcc(ChangeAccEntity changeAccEntity);

}
