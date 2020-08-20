package com.xinhu.wealth.jgt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinhu.wealth.jgt.mapper.CancleAccMapper;
import com.xinhu.wealth.jgt.model.entity.CancleAccEntity;
import com.xinhu.wealth.jgt.service.CancleAccService;
import com.xinhu.wealth.jgt.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wyt
 * @data 2020/5/21 09:36
 */
@Service
public class CancleAccImpl implements CancleAccService {
    @Autowired
    private CancleAccMapper cancleAccMapper;
    @Override
    public PageInfo canclePageSearch(Integer pageIndex, Integer rowSize, String transactionCfmDate) {
        PageInfo pageInfo = new PageInfo();
        if (pageIndex!=null&&rowSize!=null){
            Page page = PageHelper.startPage(pageIndex, rowSize);
            CancleAccEntity cancleAccEntity=new CancleAccEntity();
            cancleAccEntity.setAffirmDate(transactionCfmDate);
            List<CancleAccEntity> list = cancleAccMapper.select(cancleAccEntity);
            pageInfo = new PageInfo(page.getTotal(), pageIndex, rowSize, list);
        }
        return pageInfo;
    }
}
