package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;

/**
 * @author wyt
 * @data 2020/3/5 16:14
 */
public interface TradeAccountService {
    /**
     * 交易账户处理-----C503机构开户
     *
     * @param json 用户输入参数
     * @return
     */
    ResultDTO modifyInstitutionsAccount(String json);

    /**
     * 4.2基金账户销户----机构基金账号销户(C513)
     *
     * @param json 用户输入参数
     * @return
     */
    ResultDTO modifyFundHousehold(String json);

    /**
     * 4.3账户结果回写----柜台审核结果查询(C479)
     * 4.4基金账户销户结果回写
     * 4.5基金账户确认结果查询
     *
     * @param json 用户输入参数
     * @return
     */
    ResultDTO querytAuditResult(String json);

    /**
     * 4.4基金账户销户结果回写
     * 4.5基金账户确认结果查询
     *
     * @param json 用户输入参数
     * @return
     */
    ResultDTO queryAccountResult(String json);

    /**
     * 4.6增开基金账户-----机构增开(C504)
     *@param json 用户输入参数
     * @return
     */
    ResultDTO modifyInstitution(String json);




}
