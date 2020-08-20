package com.xinhu.wealth.jgt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.service.PollingService;
import com.xinhu.wealth.jgt.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PollingServiceImpl implements PollingService {

    /**
     * 账户mapper
     */
    @Autowired
    private PollingMapper pollingMapper;

    /**
     * 根据确认时间分页查询账户
     *
     * @param pageIndex          页码编号
     * @param rowSize            每页数量
     * @param transactionCfmDate 确认时间
     * @return
     */
    @Override
    public PageInfo accountSearch(Integer pageIndex, Integer rowSize, String transactionCfmDate) {
        PageInfo pageInfo = new PageInfo();
        if (pageIndex!=null&&rowSize!=null){
            Page page = PageHelper.startPage(pageIndex, rowSize);
            List<PollingEntity> list = pollingMapper.accountSearchV1(transactionCfmDate);
            pageInfo = new PageInfo(page.getTotal(), pageIndex, rowSize, list);
        }
        return pageInfo;
    }

    /**
     * 根据交易账户查询数据
     *
     * @param taccCode
     * @return
     */
    @Override
    public PollingEntity queryOneByAccountID(String taccCode) {
        PollingEntity pollingEntity = new PollingEntity();
        pollingEntity.setTransactionAccountID(taccCode);
        log.info("根据交易账户查询数据，入参{}", pollingEntity);
        pollingEntity = pollingMapper.selectOne(pollingEntity);
        return pollingEntity;
    }

    @Override
    public Integer modifyPwdByTradAccount(PollingEntity pollingEntity) {
        log.info("修改密码入参：{}",pollingEntity);
        return pollingMapper.updateByPrimaryKeySelective(pollingEntity);
    }
}
