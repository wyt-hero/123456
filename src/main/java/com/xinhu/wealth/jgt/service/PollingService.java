package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.util.PageInfo;

public interface PollingService {

    /**
     * 根据确认时间分页查询账户
     *
     * @param pageIndex          页码编号
     * @param rowSize            每页数量
     * @param transactionCfmDate 确认时间
     * @return
     */
    PageInfo accountSearch(Integer pageIndex, Integer rowSize, String transactionCfmDate);

    /**
     * 根据交易账户查询数据
     * @param taccCode
     * @return
     */
    PollingEntity queryOneByAccountID(String taccCode);

    Integer modifyPwdByTradAccount(PollingEntity pollingEntity);
}
