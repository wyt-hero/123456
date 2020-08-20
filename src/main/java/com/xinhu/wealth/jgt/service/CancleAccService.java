package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.util.PageInfo;

/**
 * @author wyt
 * @data 2020/5/21 09:35
 */
public interface CancleAccService {
    PageInfo canclePageSearch(Integer pageIndex, Integer rowSize, String transactionCfmDate);
}
