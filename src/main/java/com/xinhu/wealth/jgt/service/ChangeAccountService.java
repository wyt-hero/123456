package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;


public interface ChangeAccountService {

    /**
     * C502修改客户信息----
     *
     * @param param 用户输入信息
     * @return
     */
    ResultDTO modifyCustomerInfo(String param);

}
