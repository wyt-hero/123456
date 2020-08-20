package com.xinhu.wealth.jgt.service;


import com.xinhu.wealth.jgt.model.dto.yunyiDTO.AccResYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrderYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrgnLogoutDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.OrgnInZXDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;


public interface SearchFundResultService {

    /**
     * 获取账户开户的回写结果
     * @return
     */
    ResultDTO queryAccResult(ResultDTO resultDTO, AccResYunYiDTO accResYunYiDTO, OrgnInZXDTO orgnInZXDTO, String time);

    /**
     * 调用植信S445接口做账户申请查询
     * @return
     */
    String queryUserInfoByTradeNo(String allotNo);

    /**
     * 调用修改账户回写
     * @return
     */
    ResultDTO queryErrorNo(AccResYunYiDTO accResYunYiDTO, OrgnInZXDTO orgnInZXDTO, String allotNo, String time);

    /**
     * 销户回写
     * @param orgnLogoutDTO
     * @param allotNo
     * @param time
     * @return
     */
    ResultDTO queryAllotNo(OrgnLogoutDTO orgnLogoutDTO, String allotNo, String time);

    /**
     * 交易类回写，获取allotNo
     *
     * @param orderEntity
     * @return
     */
    String queryTradeAllotNo(com.xinhu.wealth.jgt.model.entity.OrderEntity orderEntity);

    /**
     * 交易类回写入参,返回值为，回写的erroNo
     *
     * @param orderYunYiDTO
     * @param allot_no
     * @param time
     * @param orderEntity
     * @return
     *
     * RenGouZXDTO renGouZXDTO,ResultDTO resultDTO
     */
    String queryTradeParam(OrderYunYiDTO orderYunYiDTO, String allot_no, String time, com.xinhu.wealth.jgt.model.entity.OrderEntity orderEntity);



}
