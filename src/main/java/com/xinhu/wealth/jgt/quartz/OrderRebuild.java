package com.xinhu.wealth.jgt.quartz;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.mapper.*;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrderYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.*;
import com.xinhu.wealth.jgt.model.entity.*;
import com.xinhu.wealth.jgt.service.impl.SearchFundResult;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wyt
 * @data 2020/4/26 10:36
 * 此类包含：
 * 1、交易类回写定时任务
 * 2、确认定时任务
 * 3、分红定时任务
 * 4、持仓定时任务
 */
@Slf4j
@Component
public class OrderRebuild {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SearchFundResult searchFundResult;
    @Resource
    PollingMapper pollingMapper;
    @Resource
    JgtTradingConfirmMapper jgtTradingConfirmMapper;
    @Resource
    JgtBonusMapper jgtBonusMapper;
    @Resource
    JgtPositionShareMapper jgtPositionShareMapper;
    @Autowired
    RestemplateUtil restemplateUtil;

    /**
     * 交易类回写
     */
    public void tradingSearchBack() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        log.info("交易类查询回写begin：{}");
        Gson gson = new Gson();
        //接收入参转换的实体类
        OrderYunYiDTO orderYunYiDTO = new OrderYunYiDTO();
        //验证是否存在相同数据实体类
        OrderEntity orderEntity = new OrderEntity();
        //未执行
        orderEntity.setStatus(0);
        //获取数据库里的交易类查询传参
        //1、根据状态Status字段，查询未回写的数据
        List<OrderEntity> orderEntityList = orderMapper.select(orderEntity);
        ResultDTO rest = new ResultDTO();//接收返回值
        //循环取出交易类查询传参
        for (int i = 0; i < orderEntityList.size(); i++) {
            //循环取出交易类查询传参
            orderEntity = orderEntityList.get(i);
            //2、数据转换
            String body = orderEntity.getBody();
            try {
                //入参转换
                orderYunYiDTO = gson.fromJson(body, new TypeToken<OrderYunYiDTO>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("交易类会写入参转换异常：{}", e);
                return;
            }
            //植信返回结果数据转换
            String zxResult = orderEntity.getZXResult();
            log.info("数据库获取的交易类型，植信返回的结果：{}", zxResult);
            //获取申请单编号
            String allot_no = searchFundResult.queryTradeAllotNo(orderEntity);
            //云毅回写返回值，tradeResultQuerys+"&"+errorNo
            String str = searchFundResult.queryTradeParam(orderYunYiDTO, allot_no, time, orderEntity);
            String errorNo = "";
            String errorInfo = "";
            //分别获取errorInfo和errorNo
            if (StringUtils.isNotEmpty(str)) {
                String[] split = str.split("&");
                if (split.length > 0) {
                    errorNo = split[0];
                    errorInfo = split[1];
                }
            }
            //如果回写结果不为空，若errorNo为0或1之外的情况，数据库不做处理
            if ("0".equals(errorNo)) {
                //如果，查询成功，并且回写成功，修改状态
                //获取数据库id，修改状态为已执行
                orderEntity.setStatus(1);
                //orderEntity.setZXSearchResult(tradeResultQuerys);
                orderEntity.setUpdateTime(LocalDateTime.now());
                orderMapper.updateByPrimaryKeySelective(orderEntity);
            } else if ("1".equals(errorNo)) {
                //回写失败，将status改为2
                //获取数据库id，修改状态为已执行
                orderEntity.setStatus(2);
                orderEntity.setUpdateTime(LocalDateTime.now());
                orderEntity.setYYErrMes(errorInfo);
                orderMapper.updateByPrimaryKeySelective(orderEntity);
            }else {
                //此种情况为errorNo非0和1，不修改数据库数据，记录日志
                log.info("交易类回写，其他情况，errorNo：{}",errorNo,"errorInfo:{}",errorInfo);
            }
        }
    }

    /**
     * 2.2下单定时任务
     */
    @Transactional
    public void queryOrderRes() {
        //获取当前时间
        String now = LocalDate.now().toString().replaceAll("-", "");
        JgtTradingConfirmEntity jgtTradingConfirmEntity = new JgtTradingConfirmEntity();
        jgtTradingConfirmEntity.setTransactionCfmDate(now);
        //删除当天的数据，去重
        log.info("删除：{}日数据，去重", now);
        jgtTradingConfirmMapper.delete(jgtTradingConfirmEntity);
        //获取S416返回值,去除交易账号不同的数据
        List<TradingConfirmDTO> tradingConfirmDTOList = searchFundResult.queryS416Res();
        TradingConfirmDTO tradingConfirmDTO = new TradingConfirmDTO();//接收S416返回值
        if (tradingConfirmDTOList!=null){
            for (int i = tradingConfirmDTOList.size() - 1; i >= 0; i--) {
                tradingConfirmDTO = tradingConfirmDTOList.get(i);
                if (tradingConfirmDTO != null) {
                    //赋值份额和金额字段
                    tradingConfirmDTO = searchFundResult.queryS501Res(tradingConfirmDTO);
                    //赋值对方TA代码字段
                    tradingConfirmDTO = searchFundResult.queryS443Res(tradingConfirmDTO);
                }
                //将结果复制给实体类TradingConfirmDTO
                jgtTradingConfirmEntity = new JgtTradingConfirmEntity();
                log.info("2.2交易确认查询入库数据转换前：{}", tradingConfirmDTO);
                BeanUtils.copyProperties(tradingConfirmDTO, jgtTradingConfirmEntity);
                //赋值净值
                jgtTradingConfirmEntity.setNAV(tradingConfirmDTO.getNAV());
                log.info("2.2交易确认查询入库数据：{}", jgtTradingConfirmEntity);
                //将交易确认结果插入到数据库
                jgtTradingConfirmMapper.insert(jgtTradingConfirmEntity);
            }
        }
    }

    /**
     * 分红确认查询
     */
    public void queryOrderBouns() {
        String now = LocalDate.now().toString().replaceAll("-", "");
        JgtBonusEntity jgtBonusEntity = new JgtBonusEntity();
        jgtBonusEntity.setTransactionCfmDate(now);
        //删除今日分红确认查询的数据库数据，去重
        jgtBonusMapper.delete(jgtBonusEntity);
        //调用分红查询得到结果，筛选出交易账号于数据库中相同的数据
        List<BonusDTO> bonusDTOList = searchFundResult.queryS420Res();
        if (bonusDTOList != null) {
            List<PollingEntity> pollingEntityList = pollingMapper.selectAll();
            //接收每条分红查询数据
            BonusDTO bonusDTO = new BonusDTO();
            for (int i = bonusDTOList.size() - 1; i >= 0; i--) {
                try {
                    bonusDTO = bonusDTOList.get(i);
                    //赋值注册登记人代码字段
                    bonusDTO = searchFundResult.queryS443Res(bonusDTO);
                    jgtBonusEntity = new JgtBonusEntity();
                    BeanUtils.copyProperties(bonusDTO, jgtBonusEntity);
                    jgtBonusMapper.insert(jgtBonusEntity);
                } catch (Exception e) {
                    log.error("初始化当日分红失败", e);
                }
            }
        }
    }

    /**
     * 持仓查询
     */
    @Transactional
    public void queryOrderShare() {
        String now = LocalDate.now().toString().replaceAll("-", "");
        JgtPositionShareEntity deleteAll=new JgtPositionShareEntity();
        deleteAll.setTransactionCfmDate(now);
        deleteAll.setHoldingIncome(null);
        deleteAll.setDayIncome(null);
        deleteAll.setByIncome(null);
        deleteAll.setAccIncome(null);
        jgtPositionShareMapper.delete(deleteAll);
        deleteAll.setTransactionCfmDate(now);
        jgtPositionShareMapper.delete(deleteAll);
        List<PollingEntity> pollingEntityList = pollingMapper.selectAll();
        //接收S403结果集
        List<PositionShareDTO> positionShareDTOList = new ArrayList<>();
        //接收S403结果单条
        PositionShareDTO positionShareDTO = new PositionShareDTO();
        Gson gson = new Gson();
        for (PollingEntity pollingEntity : pollingEntityList) {
            //2.4持仓结果
            positionShareDTOList = searchFundResult.queryS403Res(pollingEntity);
            if (pollingEntityList.size() == 0) {
                continue;
            }
            if (positionShareDTOList==null){
                continue;
            }
            //此类用于接收收益相关的字段值
            FoundIncomeDTO foundIncomeDTO=new FoundIncomeDTO();
            for (int i = 0; i < positionShareDTOList.size(); i++) {
                try {
                    positionShareDTO = positionShareDTOList.get(i);
                    //2.4持仓，赋值收费方式，TA 确认交易流水号,交易确认日期
                    positionShareDTO=searchFundResult.queryS416Res(positionShareDTO);
                    //2.4持仓，赋值账户状态
                    positionShareDTO=searchFundResult.queryS405Res(positionShareDTO);
                    //获取持有收益
                    String s476Url= PathZXConstants.S476.getUrl()+"&fund_acco="+positionShareDTO.getTAAccountID()+"&fund_code="+positionShareDTO.getFundCode();
                    ResultDTO resultDTO = restemplateUtil.postRest("", s476Url, null);
                    String fundIncomeDetilQueries = JSONObject.parseObject(resultDTO.getData() + "").getString("fundIncomeDetilQueries");
                    if(StringUtils.isNotEmpty(fundIncomeDetilQueries)&& !"[]".equals(fundIncomeDetilQueries)){
                        log.info("获取income_by_hold总结果：{}",fundIncomeDetilQueries);
                        JSONObject jsonObject = JSONObject.parseArray(fundIncomeDetilQueries).getJSONObject(0);
                        //获取收益相关的值
                        foundIncomeDTO = JSONObject.parseObject(jsonObject.toJSONString(), FoundIncomeDTO.class);
                        if (foundIncomeDTO!=null){
                            //赋值持有收益
                            positionShareDTO.setByIncome(foundIncomeDTO.getIncome_by_hold());
                            //赋值持仓收益
                            positionShareDTO.setHoldingIncome(foundIncomeDTO.getHolding_income());
                            //赋值昨日盈亏，即基金收益
                            positionShareDTO.setDayIncome(foundIncomeDTO.getFund_curr_income());
                        }
                    }
                    JgtPositionShareEntity jgtPositionShareEntity = new JgtPositionShareEntity();
                    log.info("2.4定时任务，插入数据库数据positionShareDTO:{}",positionShareDTO);
                    BeanUtils.copyProperties(positionShareDTO, jgtPositionShareEntity);
                    jgtPositionShareEntity.setTransactionCfmDate(now);
                    log.info("2.4定时任务，插入数据库数据：{}",jgtPositionShareEntity);
                    int insertCount = jgtPositionShareMapper.insertSelective(jgtPositionShareEntity);
                    log.info("持仓查询插入数据库条数：{}",insertCount);
                } catch (Exception e) {
                    log.error("初始化当日持仓失败", e);
                }

            }
        }
    }

}
