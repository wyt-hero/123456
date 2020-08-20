package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;

/**
 * @author wyt
 * @data 2020/3/5 16:24
 */
public interface ChangeAccountInfoService {
    /**
     * C502修改客户信息----
     *
     * @param json 用户输入信息
     * @return
     */
    ResultDTO modifyCustomerInfo(String json);
}
