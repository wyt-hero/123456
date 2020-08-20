package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ParticipationDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;

/**
 * @author wyt
 * @data 2020/3/5 15:33
 */
public interface OrderService {
    /**
     * 认购
     * @param participationDTO 存放入参
     * @return
     */
    ResultDTO querySubscriber(ParticipationDTO participationDTO);

    /**
     * 申购，（返回值与认购相同，使用一个entity）
     *
     * @param participationDTO 存放入参
     * @return
     */
    ResultDTO queryPurchase(ParticipationDTO participationDTO);

    /**
     * 赎回
     *
     * @param participationDTO 存放入参
     * @return
     */
    ResultDTO queryRedemption(ParticipationDTO participationDTO);

    /**
     * 基金转换
     *
     * @param participationDTO 存放入参
     * @return
     */
    ResultDTO modifyFunChange(ParticipationDTO participationDTO);

    /**
     * 修改分红方式
     *
     * @param participationDTO 存放入参
     * @return
     */
    ResultDTO modifyBonusType(ParticipationDTO participationDTO);

    /**
     * 撤单
     *
     * @param participationDTO 存放入参
     * @return
     */
    ResultDTO queryCancellations(ParticipationDTO participationDTO);


    /**
     * 2.1下单总接口
     * @param participationDTO 存放入参
     * @return
     */
    ResultDTO queryOrder(ParticipationDTO participationDTO);
    /**
     * 2.2确认------S416 交易成交查询
     *
     * @param
     * @return
     */
    ResultDTO queryTradeConfirm(ParticipationDTO participationDTO);

    /**
     * 2.3分红-----S420-分红查询
     *
     * @param
     * @return
     */
    ResultDTO queryBonusSearch(ParticipationDTO participationDTO);

    /**
     * 2.4持仓-----S403份额查询
     *
     * @param
     * @return
     */
    ResultDTO queryShare(ParticipationDTO participationDTO);

    /**
     * 交易类查询接口
     * @param
     * @return
     */
    ResultDTO queryOrderSearch(ParticipationDTO participationDTO);

}
