package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;

/**
 * @author wyt
 * @data 2020/3/5 16:02
 */
public interface FundMarketService {
    /**
     * 3.1行情信息查询-------S428基金行情信息查询
     *
     * @param json 用户输入参数
     * @return
     */
    ResultDTO queryFundMarket(String json, String indexPage, String pageSize);

}
