package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrderYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.*;
import com.xinhu.wealth.jgt.model.entity.OrderEntity;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.service.TradeService;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import com.xinhu.wealth.jgt.util.YYSignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wyt
 * @data 2020/4/16 15:06
 * 此类用于，封装返回云毅实体类值
 * 包括：1、交易类回写，获取allotNo
 * 2、交易类回写入参,返回值为，回写的erroNo&errorInfo
 * 3、获取S416返回值
 * 4、下单定时任务，赋值净值字段
 * 5、下单定时任务，赋值份额和金额字段
 * 6、下单定时任务，赋值对方TA代码字段
 * 7、分红查询，S420返回结果，筛选出交易账号相同的数据
 * 8、分红查询，赋值注册登记人代码字段
 * 9、持仓结果
 * 10、持仓，赋值收费方式，TA 确认交易流水号,交易确认日期
 * 11、持仓，赋值账户状态
 */
@Component
@Slf4j
public class SearchFundResult {
    @Autowired
    TradeService tradeService;
    @Autowired
    RestemplateUtil restemplateUtil;
    @Autowired
    PollingMapper pollingMapper;

    /**
     * 交易类回写，获取allotNo
     *
     * @param orderEntity 入库实体类
     * @return
     */
    public String queryTradeAllotNo(OrderEntity orderEntity) {
        //申请编号
        String allot_no = "";
        if (StringUtils.isNotEmpty(orderEntity.getAllotNo())) {
            allot_no = orderEntity.getAllotNo();
        }
        return allot_no;
    }

    /**
     * 交易类回写入参,返回值为，回写的erroNo&errorInfo
     *
     * @param orderYunYiDTO 云毅入参实体类
     * @param allot_no 申请单编号
     * @param time 加签时间
     * @param orderEntity 入库实体类
     * @return RenGouZXDTO renGouZXDTO,ResultDTO resultDTO
     */
    public String queryTradeParam(OrderYunYiDTO orderYunYiDTO, String allot_no, String time, OrderEntity orderEntity) {
        //云毅回写入参
        OrderInYYDTO orderInYYDTO = new OrderInYYDTO();
        //TransactionAccountID;//交易账号
        orderInYYDTO.setTransactionAccountID(orderYunYiDTO.getTransactionAccountID());
        //Appserialno;//申请单编号
        orderInYYDTO.setAppserialno(allot_no);
        //TranStateDetail;//申报状态信息
        orderInYYDTO.setTranStateDetail("交易成功");
        //Oserialno;//申报编号
        orderInYYDTO.setOserialno(orderYunYiDTO.getOserialno());
        orderInYYDTO.setTranstate("0");
        //若申报编号为空，则说明下单失败，则回写时，下单状态为1，错误信息为，交易失败
        if (StringUtils.isEmpty(allot_no)) {
            orderInYYDTO.setTranstate("1");
            orderInYYDTO.setTranStateDetail("交易失败");
        }
        //转成string，准备加签
        String jsonHui = JSONObject.toJSONString(orderInYYDTO);
        //加签
        String sign = YYSignUtil.sign(time + "&" + jsonHui, JGTConstant.YY_PRI_KEY);
        //回写云毅ZXJY(交易类会写)
        String S407_1Url = PathZXConstants.ZXJY.getUrl();
        //map存储，请求头信息
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Version", orderEntity.getVersion());
        headers.put("ProcessCode", orderEntity.getProssCode());
        headers.put("SingText", sign);
        headers.put("Version", "1.1");
        //销售渠道 InstitutionCode
        headers.put("InstitutionCode", "334");
        headers.put("SendTime", time);
        headers.put("ProcessCode", "2005");

        //回写
        log.info("交易类回写入参：{}", jsonHui);
        log.info("交易类回写请求头：{}", headers);
        log.info("交易类会写路径", S407_1Url);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO = restemplateUtil.postRest(jsonHui, S407_1Url, headers);
        resultDTO = JSONObject.parseObject(resultDTO.getData() + "", ResultDTO.class);
        log.info("交易类回写返回结果:{}", resultDTO);
        //如果，查询成功，并且回写成功，修改状态
        //String data = resultDTO.getData() + "";
        String errorNo = "";
        String errorInfo="";
        if(resultDTO!=null){
            errorNo = resultDTO.getErrorNo();
            errorInfo=resultDTO.getErrorInfo();
        }
        return errorNo + "&" +errorInfo ;
    }


    /**
     * 获取S416返回值
     *
     * @return
     */
    public List<TradingConfirmDTO> queryS416Res() {
        String now = LocalDate.now().toString().replaceAll("-", "");
        //todo
        now="20200522";
        Gson gson = new Gson();
        LinkedHashMap s416Param = new LinkedHashMap();
        s416Param.put("request_num", 10000);
        s416Param.put("request_pageno", 1);
        s416Param.put("start_date", now);
        String s416Url = PathZXConstants.S416.getUrl();
        log.info("S416入参：{}", s416Param);
        log.info("S416URL：{}", s416Url);
        //根据日期查询
        ResultDTO resultDTO = restemplateUtil.getRestV2(s416Param, s416Url);
        JSONObject jsonObject = JSONObject.parseObject(resultDTO.getData() + "");
        List<TradingConfirmDTO> tradingConfirmDTOList = null;//接收S416接口返回值
        //判断是否为空
        if (jsonObject != null && !jsonObject.equals("[]")) {

            try {
                //获取查询结果
                String data = jsonObject.getString("tradeConfirmQuerys");//转成集合
                //将查询结果转为集合
                tradingConfirmDTOList = gson.fromJson(data, new TypeToken<List<TradingConfirmDTO>>() {
                }.getType());
                //赋值returnCode,确认标志
                for (TradingConfirmDTO tradingConfirmDTO : tradingConfirmDTOList) {
                    if ("1".equals(tradingConfirmDTO.getReturnCode())){
                        tradingConfirmDTO.setReturnCode("0000");
                    }
                }
            } catch (JsonSyntaxException e) {
                log.info("2.2下单定时任务入参转换异常：{}", e);
                return null;
            }
            log.info("S416植信返回数据：{}", tradingConfirmDTOList);
            //查询出数据库所有数据
            List<PollingEntity> pollingEntityList = pollingMapper.selectAll();
            if (tradingConfirmDTOList == null) {
                return null;
            }
            for (int i = tradingConfirmDTOList.size() - 1; i >= 0; i--) {
                TradingConfirmDTO tradingConfirmDTO = tradingConfirmDTOList.get(i);
                boolean flag = true;
                for (PollingEntity pollingEntity : pollingEntityList) {
                    //挑出交易账号一致的数据，（植信返回结果，与数据库数据对比）
                    if (pollingEntity != null && StringUtils.isNotEmpty(pollingEntity.getTransactionAccountID())
                            && tradingConfirmDTO.getTransactionAccountID().equals(pollingEntity.getTransactionAccountID())) {
                        flag = false;
                    }
                }
                if (flag) {
                    //如果没有对比到相同交易账号，则将本条确认结果结果移除
                    tradingConfirmDTOList.remove(i);
                    continue;
                }

            }
        }
        log.info("2.2下单定时任务，获取S416查询结果：{}", tradingConfirmDTOList);
        return tradingConfirmDTOList;
    }

    /**
     * 2.2下单定时任务，赋值净值字段
     *
     * @param tradingConfirmDTO 返回云毅实体类
     * @return
     */
    public TradingConfirmDTO queryS428Res(TradingConfirmDTO tradingConfirmDTO) {
        log.info("2.2下单定时任务，赋值净值字段接收参数：{}", tradingConfirmDTO);
        //获取值 基金转换业务的目标基金的净值
        LinkedHashMap s428Param = new LinkedHashMap();
        s428Param.put("fund_code", tradingConfirmDTO.getFundCode());
        log.info("S428接口入参：{}", s428Param);
        String S428Url = PathZXConstants.S428.getUrl();
        log.info("S428接口URL：{}", S428Url);
        //根据基金代码查询，最新基金行情结果，目的是获取净值
        ResultDTO resultDTO = restemplateUtil.getRestV2(s428Param, S428Url);
        //转成集合
        JSONObject S428Json = JSONObject.parseObject(resultDTO.getData() + "");
        List<MarketInfoDTO> marketInfoDTOList = null;
        try {
            Gson gson = new Gson();
            marketInfoDTOList = gson.fromJson(S428Json.getString("fundMarketInfoQuerys"), new TypeToken<List<MarketInfoDTO>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("2.2下单定时任务S428返回结果转换异常：{}", e);
            return null;
        }
        //赋值净值字段
        if (marketInfoDTOList != null && marketInfoDTOList.size() > 0) {
            MarketInfoDTO marketInfoDTO = marketInfoDTOList.get(0);
            tradingConfirmDTO.setTargetNAV(marketInfoDTO.getNAV());//净值
        }
        log.info("2.2下单定时任务，赋值净值字段返回参数：{}", tradingConfirmDTO);
        return tradingConfirmDTO;
    }

    /**
     * 2.2下单定时任务，赋值份额和金额字段
     *
     * @param tradingConfirmDTO 返回云毅实体类
     * @return
     */
    public TradingConfirmDTO queryS501Res(TradingConfirmDTO tradingConfirmDTO) {
        log.info("2.2下单定时任务，赋值份额和金额字段接收参数：{}", tradingConfirmDTO);
        if (tradingConfirmDTO != null) {
            //赋值
            LinkedHashMap s501Param = new LinkedHashMap();
            s501Param.put("allot_no", tradingConfirmDTO.getAppserialno());
            String S501Url = PathZXConstants.S501.getUrl();
            log.info("S501URl：{}", S501Url);
            log.info("S501入参：{}", s501Param);
            //查询交易申请接口，得到交易申请结果
            ResultDTO resultDTO = restemplateUtil.getRestV2(s501Param, S501Url);
            JSONObject S501Json = JSONObject.parseObject(resultDTO.getData() + "");
            List<OrgTradeApplyQuerysEntity> orgTradeApplyQuerysEntityList = null;
            try {
                Gson gson = new Gson();
                orgTradeApplyQuerysEntityList = gson.fromJson(S501Json.getString("results"), new TypeToken<List<OrgTradeApplyQuerysEntity>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("2.2下单定时任务S501返回结果异常：{}", e);
                return null;
            }
            if (orgTradeApplyQuerysEntityList != null && orgTradeApplyQuerysEntityList.size() > 0) {
                //赋值，份额和金额字段
                OrgTradeApplyQuerysEntity orgTradeApplyQuerysEntity = orgTradeApplyQuerysEntityList.get(0);
                tradingConfirmDTO.setApplicationAmount(new BigDecimal(orgTradeApplyQuerysEntity.getBalance()));//金额
                tradingConfirmDTO.setApplicationVol(new BigDecimal(orgTradeApplyQuerysEntity.getShares()));//份额

            }
        }

        log.info("2.2下单定时任务，赋值份额和金额字段返回参数：{}", tradingConfirmDTO);
        return tradingConfirmDTO;
    }

    /**
     * 2.2下单定时任务，赋值对方TA代码字段
     *
     * @param tradingConfirmDTO 返回云毅实体类
     * @return
     */
    public TradingConfirmDTO queryS443Res(TradingConfirmDTO tradingConfirmDTO) {
        log.info("2.2下单定时任务，赋值对方TA代码字段接收参数：{}", tradingConfirmDTO);
        //获取S443返回值
        String s443Url = PathZXConstants.S443.getUrl();
        LinkedHashMap s443Param = new LinkedHashMap();
        s443Param.put("fund_code", tradingConfirmDTO.getFundCode());
        log.info("S443URL：{}", s443Url);
        log.info("S443入参：{}", s443Param);
        //根据基金代码，获取S443接口的值，目的是赋值对方TA代码字段
        ResultDTO S443 = restemplateUtil.getRestV2(s443Param, s443Url);
        //获取返回值的data部分
        JSONObject json = JSONObject.parseObject(S443.getData() + "");
        List<TainfoQuerysDTO> tainfoQuerysDTOList = null;
        try {
            //转换为集合
            Gson gson = new Gson();
            tainfoQuerysDTOList = gson.fromJson(json.getString("tainfoQuerys"), new TypeToken<List<TainfoQuerysDTO>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("2.2下单定时任务S443返回结果转换异常：{}", e);
            return null;
        }
        //赋值对方TA代码字段
        if (tainfoQuerysDTOList != null && tainfoQuerysDTOList.size() > 0) {
            TainfoQuerysDTO tainfoQuerysDTO = tainfoQuerysDTOList.get(0);
            tradingConfirmDTO.setTargetRegistrarCode(tainfoQuerysDTO.getTa_code());//对方TA代码
        }
        log.info("2.2下单定时任务，赋值对方TA代码字段返回参数：{}", tradingConfirmDTO);
        return tradingConfirmDTO;
    }


    /**
     * 分红查询，S420返回结果，筛选出交易账号相同的数据
     *
     * @return
     */
    public List<BonusDTO> queryS420Res() {
        Gson gson = new Gson();
        LinkedHashMap s420Param = new LinkedHashMap();
        String now = LocalDate.now().toString().replaceAll("-", "");
        s420Param.put("begin_date", now);
        s420Param.put("request_num", 10000);
        s420Param.put("request_pageno", 1);
        String s420Url = PathZXConstants.S420.getUrl();
        log.info("2.3分红查询入参：{}", s420Param);
        log.info("2.3分红查询URL：{}", s420Url);
        //获取分红查询结果
        ResultDTO resultDTO = restemplateUtil.getRestV2(s420Param, s420Url);
        //BonusDTO
        //获取植信data
        JSONObject jsonObject = JSONObject.parseObject(resultDTO.getData() + "");
        List<BonusDTO> bonusDTOList = new ArrayList<>();
        //判断是否为空
        if (jsonObject != null && !jsonObject.equals("[]")) {
            //do TA信息查询(S443)根据基金代码查询 ta_code
            //转成集合--2.3分红
            try {
                bonusDTOList = gson.fromJson(jsonObject.getString("bonusQuerys"), new TypeToken<List<BonusDTO>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("S420获取分红结果转换异常：{}", e);
                return null;
            }
            if (bonusDTOList != null) {
                List<PollingEntity> pollingEntityList = pollingMapper.selectAll();
                for (int i = bonusDTOList.size() - 1; i >= 0; i--) {
                    BonusDTO bonusDTO = bonusDTOList.get(i);
                    boolean flag = true;
                    for (PollingEntity pollingEntity : pollingEntityList) {
                        //筛选出交易账号相同的分红数据
                        if (pollingEntity != null && StringUtils.isNotEmpty(pollingEntity.getTransactionAccountID())
                                && bonusDTO.getTransactionAccountID().equals(pollingEntity.getTransactionAccountID())) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        //将交易账号不同的分红数据移除
                        bonusDTOList.remove(i);
                        continue;
                    }
                }
            }
        }
        log.info("分红查询返回结果：{}", bonusDTOList);
        return bonusDTOList;
    }

    /**
     * 分红查询，赋值注册登记人代码字段
     *
     * @param bonusDTO 返回云毅实体类
     * @return
     */
    public BonusDTO queryS443Res(BonusDTO bonusDTO) {
        log.info("分红查询，赋值注册登记人代码字段入参：{}", bonusDTO);
        String s443Url = PathZXConstants.S443.getUrl();
        LinkedHashMap s443Param = new LinkedHashMap();
        s443Param.put("fund_code", bonusDTO.getFundCode());
        log.info("S443URL：{}", s443Url);
        log.info("S443入参：{}", s443Param);
        //根据基金代码查询S443接口,赋值注册登记人代码字段
        ResultDTO S443 = restemplateUtil.getRestV2(s443Param, s443Url);
        //获取返回值的data部分
        JSONObject json = JSONObject.parseObject(S443.getData() + "");
        //获取tainfoQuerys部分
        String tainfoQuerys = json.getString("tainfoQuerys");
        //转换为集合
        Gson gson = new Gson();
        List<TainfoQuerysDTO> tainfoQuerysDTOList = null;
        try {
            tainfoQuerysDTOList = gson.fromJson(tainfoQuerys, new TypeToken<List<TainfoQuerysDTO>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("分红查询，S443返回值转换异常：{}", e);
            return null;
        }
        if (tainfoQuerysDTOList != null && tainfoQuerysDTOList.size() > 0) {
            TainfoQuerysDTO tainfoQuerysDTO = tainfoQuerysDTOList.get(0);
            //赋值注册登记人代码字段
            bonusDTO.setRegistrarCode(tainfoQuerysDTO.getTa_code());
        }
        log.info("分红查询，赋值注册登记人代码字段返回结果：{}", bonusDTO);
        return bonusDTO;
    }


    /**
     * 2.4持仓结果
     *
     * @param pollingEntity 入库实体类
     * @return
     */
    public List<PositionShareDTO> queryS403Res(PollingEntity pollingEntity) {

        if (pollingEntity == null || StringUtils.isEmpty(pollingEntity.getTransactionAccountID())) {
            return null;
        }
        LinkedHashMap s403Param = new LinkedHashMap();
        s403Param.put("request_num", 1000);
        s403Param.put("request_pageno", 1);
        s403Param.put("trade_acco", pollingEntity.getTransactionAccountID());
        String s403Url = PathZXConstants.S403.getUrl();
        ResultDTO resultDTO = restemplateUtil.getRestV2(s403Param, s403Url);
        //获取植信data
        JSONObject jsonObject = JSONObject.parseObject(resultDTO.getData() + "");

        String data = jsonObject.getString("shareQuerys");
        //转成集合
        Gson gson = new Gson();
        List<PositionShareDTO> positionShareDTOList = null;
        try {
            positionShareDTOList = gson.fromJson(data, new TypeToken<List<PositionShareDTO>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("2.4持仓结果转换异常：{}", e);
        }
        if (positionShareDTOList == null) {
            return null;
        }
        log.info("2.4持仓结果:{}", positionShareDTOList);
        return positionShareDTOList;

    }

    /**
     * 2.4持仓，赋值收费方式，TA 确认交易流水号,交易确认日期
     * @param positionShareDTO 返回云毅实体类
     * @return
     */
    public PositionShareDTO queryS416Res(PositionShareDTO positionShareDTO) {
        log.info("2.4持仓，赋值收费方式，TA 确认交易流水号,交易确认日期入参结果：{}", positionShareDTO);

        LinkedHashMap s416Param = new LinkedHashMap();
        s416Param.put("trade_acco", positionShareDTO.getTransactionAccountID());
        s416Param.put("fund_code", positionShareDTO.getFundCode());
        String s416Url = PathZXConstants.S416.getUrl();
        ResultDTO resultDTO = restemplateUtil.getRestV2(s416Param, s416Url);
        //获取植信data
        JSONObject jsonObject1 = JSONObject.parseObject(resultDTO.getData() + "");
        String data1 = jsonObject1.getString("tradeConfirmQuerys");
        //转成集合
        Gson gson = new Gson();
        List<TradingConfirmDTO> tradingConfirmDTOList = null;
        try {
            tradingConfirmDTOList = gson.fromJson(data1, new TypeToken<List<TradingConfirmDTO>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("2.4持仓，赋值收费方式，TA 确认交易流水号,交易确认日期，转换异常：{}", e);
        }
        if (tradingConfirmDTOList != null && tradingConfirmDTOList.size() > 0) {
            TradingConfirmDTO tradingConfirmDTO = tradingConfirmDTOList.get(0);
            //赋值收费方式
            positionShareDTO.setShareClass(tradingConfirmDTO.getShareClass());
            //TA 确认交易流水号
            positionShareDTO.setTASerialNO(tradingConfirmDTO.getTASerialNO());
            //交易确认日期
            positionShareDTO.setTransactionCfmDate(tradingConfirmDTO.getTransactionCfmDate());
        }
        log.info("2.4持仓，赋值收费方式，TA 确认交易流水号,交易确认日期返回结果：{}", positionShareDTO);
        return positionShareDTO;
    }

    /**
     * 2.4持仓，赋值账户状态
     *
     * @param positionShareDTO 返回云毅实体类
     * @return
     */
    public PositionShareDTO queryS405Res(PositionShareDTO positionShareDTO) {
        log.info("2.4持仓，赋值账户状态入参结果：{}", positionShareDTO);
        LinkedHashMap S405Param = new LinkedHashMap();
        S405Param.put("trade_acco", positionShareDTO.getTransactionAccountID());
        String S405Url = PathZXConstants.S405.getUrl();
        ResultDTO resultDTO = restemplateUtil.getRestV2(S405Param, S405Url);
        JSONObject s405Object = JSONObject.parseObject(resultDTO.getData().toString());
        if (s405Object != null && s405Object.getInteger("total_count") > 0) {
            String accountStatus = null;
            try {
                accountStatus = s405Object.getJSONArray("fundAccountQuerys").getJSONObject(0).getString("trade_acco_status");
            } catch (Exception e) {
                log.info("2.4持仓，赋值账户状态转换异常：{}", e);
            }
            positionShareDTO.setAccountStatus(accountStatus);


        }
        log.info("2.4持仓，赋值账户状态返回结果：{}", positionShareDTO);
        return positionShareDTO;
    }


}
