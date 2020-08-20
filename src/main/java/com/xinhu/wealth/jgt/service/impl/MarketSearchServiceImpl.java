package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.MarketInfoDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.PriceIncreaseDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.TainfoQuerysDTO;
import com.xinhu.wealth.jgt.service.FundMarketService;
import com.xinhu.wealth.jgt.service.MarketSearchService;
import com.xinhu.wealth.jgt.util.DateUtil;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import com.xinhu.wealth.jgt.util.YYSignUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 行情查询及其他查询serviceImpl
 *
 * @author wyt
 * @data 2020/3/20 15:58
 */
@Slf4j
@Service
public class MarketSearchServiceImpl implements MarketSearchService {

    /**
     * 查询市场行情service
     */
    @Autowired
    private FundMarketService fundMarketService;

    @Autowired
    ThreadPoolExecutor executor;


    /**
     * 远程调用连接工具类
     */
    @Autowired
    private RestemplateUtil restTemplateUtil;

    /**
     * 行情查询及其他查询接口
     *
     * @param body        云毅body
     * @param sing        签名
     * @param processCode 功能代码
     * @param version     版本
     * @param pageIndex   页码编号
     * @param rowSize     每页数量
     * @param sendTime    发送时间
     * @return
     */
    @Override
    public String accountSearch(String body, String sing, String processCode, String version, String pageIndex, String rowSize, String sendTime) {
        log.info("用户输入数据：{}", body);
        long S428TimeBegain = System.currentTimeMillis();
        log.info("3.1行情查询开始时间：{}", S428TimeBegain);
        //验签
        boolean bool = YYSignUtil.isSign(sendTime, body, sing);
        //todo 验签
        bool = true;
        ResultDTO resultDTO = new ResultDTO();
        if (bool) {
            //判断是什么业务，3.1行情信息查询
            log.info("ProcessCode：{}", processCode);
            //接收返回值 3.1行情信息查询
            if ("3001".equals(processCode)) {
                if (StringUtils.isEmpty(pageIndex)) {
                    pageIndex = "1";//不填写默认第一页
                } else if (StringUtils.isEmpty(rowSize)) {
                    rowSize = "100";
                }
                if (StringUtils.isNotEmpty(rowSize)) {
                    //调用植信接口最新基金行情查询(S428)
                    resultDTO = fundMarketService.queryFundMarket(body, pageIndex, rowSize);
                    //判断是否为空 不为空说明查询到最新基金行情
                    if (resultDTO.getData() != null && !resultDTO.getData().equals("[]")) {
                        JSONObject jsonObject = JSONObject.parseObject(resultDTO.getData() + "");
                        String fundMarketInfoQuerys = jsonObject.getString("fundMarketInfoQuerys");
                        Gson gson = new Gson();
                        //转成集合
                        List<MarketInfoDTO> marketInfoDTOList = gson.fromJson(fundMarketInfoQuerys, new TypeToken<List<MarketInfoDTO>>() {
                        }.getType());
                        //do TA信息查询(S443)：根据ta_code查询对应名称RegistrarName
                        String s443Url = PathZXConstants.S443.getUrl();
                        //接收TA信息查询(S443) 查询行情信息入参
                        //  使用线程池，管理线程（目的优化行情信息查询速度）
                        CountDownLatch latch = new CountDownLatch(marketInfoDTOList.size());
                        for (MarketInfoDTO it : marketInfoDTOList) {
                            Market3001 market3001 = new Market3001(latch, it);
                            executor.execute(market3001);
                        }
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            log.error("线程异常：", e);
                        }
                        resultDTO.setTotalCount(jsonObject.getInteger("total_count"));
                        resultDTO.setRowSize(Integer.parseInt(rowSize));
                        //返回给云毅
                        resultDTO.setData(marketInfoDTOList);
                    }
                }
                resultDTO.setSendTime(sendTime);
                resultDTO.setVersion(version);
                resultDTO.setPageIndex(Integer.parseInt(pageIndex));
                resultDTO.setResult(0);
                //log.info("返回结果:{}", resultDTO);
            }
            //加签
            String sign = YYSignUtil.sign(DateUtil.getTime() + "&" + resultDTO, JGTConstant.YY_PRI_KEY);
            resultDTO.setYYSign(sign);
            resultDTO.setSendTime(DateUtil.getTime());
            //计时
            S428TimeBegain = System.currentTimeMillis() - S428TimeBegain;
            log.info("3.1行情查询结束时间：{}", S428TimeBegain);
            return JSONObject.toJSONString(resultDTO);
        } else {
            resultDTO.setSendTime(DateUtil.getTime());
            resultDTO.setVersion(version);
            if (StringUtils.isNotEmpty(rowSize)) {
                resultDTO.setRowSize(Integer.parseInt(rowSize));
            }
            resultDTO.setResult(1);
            resultDTO.setMessage("验签失败");
            resultDTO.setPageIndex(Integer.parseInt(pageIndex));
            resultDTO.setYYSign("");
            log.info("3.1行情查询结束时间：{}", LocalDateTime.now());
            return JSONObject.toJSONString(resultDTO);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class Market3001 implements Runnable {

        CountDownLatch latch;

        MarketInfoDTO it;

        @Override
        public void run() {
            LinkedHashMap map = new LinkedHashMap();
            String s443Url = PathZXConstants.S443.getUrl();
            if (StringUtils.isNotEmpty(it.getRegistrarCode())) {
                map.put("ta_code", it.getRegistrarCode());
            }
            //TA信息查询(S443) 查询行情信息
            ResultDTO S443 = restTemplateUtil.getRestV2(map, s443Url);
            //获取返回值的data部分
            JSONObject json = JSONObject.parseObject(S443.getData() + "");
            //获取tainfoQuerys部分
         /*   String tainfoQuerys = json.getString("tainfoQuerys");
            List<TainfoQuerysDTO> tainfoQuerysDTOList = gson.fromJson(tainfoQuerys, new TypeToken<List<TainfoQuerysDTO>>() {
            }.getType());*/
            TainfoQuerysDTO tainfoQuerysDTO = JSONObject.parseObject(json.getJSONArray("tainfoQuerys").getJSONObject(0).toJSONString(), TainfoQuerysDTO.class);
            //发行人名称
            String taName = tainfoQuerysDTO.getTa_name();
            it.setRegistrarName(taName);
            it.setRegistrarCode(tainfoQuerysDTO.getTa_code());
            if (StringUtils.isNotEmpty(taName)) {
                it.setIssueName(taName);
            }
            //根据fund_code查询K426接口
            String k406Url = PathZXConstants.K406.getUrl();
            //时间
            ResultDTO resultK406 = restTemplateUtil.postRest("", k406Url + "&fund_code=" + it.getFundcode(), null);
            String returnList = JSONObject.parseObject(resultK406.getData() + "").getString("netValues");
            List<PriceIncreaseDTO> priceIncreaseDTOList = JSONArray.parseArray(returnList, PriceIncreaseDTO.class);
            if (priceIncreaseDTOList != null && priceIncreaseDTOList.size() > 0) {
                PriceIncreaseDTO priceIncreaseDTO = priceIncreaseDTOList.get(0);
                if (priceIncreaseDTO != null) {
                    it.setRateOfWeek(priceIncreaseDTO.getPrice_increase_week());
                    it.setRateOfMoon(priceIncreaseDTO.getPrice_increase_month1());
                    it.setRateOfYear(priceIncreaseDTO.getPrice_increase_year());
                    it.setYield(priceIncreaseDTO.getIncome_ratio());
                }
            }
            if (StringUtils.isNotEmpty(it.getFundIncome()) && Double.parseDouble(it.getFundIncome()) > 0) {
                it.setFundIncomeFlag("0");//代表正
            } else {
                it.setFundIncomeFlag("1");//代表负
            }
            if (StringUtils.isNotEmpty(it.getYield()) && Double.parseDouble(it.getYield()) > 0) {
                it.setYieldFlag("0");//代表正
            } else {
                it.setYieldFlag("1");//代表负
            }
            //查询S414接口，获取，当日累计最大份额
            //1、获取Fundcode
            String fundcode = it.getFundcode();
            if (StringUtils.isNotEmpty(fundcode)) {
                //获取S414链接
                String S414Url = PathZXConstants.S414.getUrl() + "&fund_code=" + fundcode;
                ResultDTO resultDTO = restTemplateUtil.postRest("", S414Url, null);
                String results = JSONObject.parseObject(resultDTO.getData() + "").getString("results");
                JSONArray jsonArray = JSONObject.parseArray(results);
                if (jsonArray.size() > 0) {
                    String max_trust_limit = JSONObject.parseObject(jsonArray.get(0) + "").getString("max_trust_limit");
                    it.setInstDayMaxSumBuy(max_trust_limit);//法人当日累计购买最大金额
                }
            }
            String declare_state = it.getDeclare_state();//申购状态
            if (StringUtils.isNotEmpty(declare_state)){
                //获取份额类别
                String share_type = it.getShare_type();
                //1、获取S435连接
                String s435Url="";
                String s435Half="&fund_code="+fundcode+"&share_type="+share_type+"&cust_type=0";
                log.info("S45链接：{}",s435Half);
                String add_value = "";//法人追加认购最高金额/
                String high_value = "";//最高认购金额/法人追加认购最低金额
                String low_value="";//认购单笔最低购买金额/
                if ("1".equals(declare_state)){
                    //可申购022
                    //拼接连接
                    s435Url= PathZXConstants.S435.getUrl()+"&fund_busin_code=022"+s435Half;
                    ResultDTO resultDTO = restTemplateUtil.postRest("", s435Url, null);
                    add_value = JSONObject.parseObject(resultDTO.getData() + "").getString("add_value");
                    low_value = JSONObject.parseObject(resultDTO.getData() + "").getString("low_value");
                    high_value = JSONObject.parseObject(resultDTO.getData() + "").getString("high_value");
                }else {
                    //认购020
                    s435Url= PathZXConstants.S435.getUrl()+"&fund_busin_code=020"+s435Half;
                    ResultDTO resultDTO = restTemplateUtil.postRest("", s435Url, null);
                    add_value = JSONObject.parseObject(resultDTO.getData() + "").getString("add_value");
                    high_value = JSONObject.parseObject(resultDTO.getData() + "").getString("high_value");
                    low_value = JSONObject.parseObject(resultDTO.getData() + "").getString("low_value");
                }
                if(StringUtils.isNotEmpty(add_value)){
                    it.setInstAppSubsAmnt(add_value);//法人追加认购金
                }
                if(StringUtils.isNotEmpty(high_value)){
                    it.setInstMaxPurchase(high_value);//法人最大申购金额
                }
                if (StringUtils.isNotEmpty(low_value)){
                    it.setInstMinPurchase(low_value);//法人单笔最低购买金额
                }
                //赎回赋值024
                s435Url= PathZXConstants.S435.getUrl()+"&fund_busin_code=024"+s435Half;
                ResultDTO resultDTO = restTemplateUtil.postRest("", s435Url, null);
                high_value = JSONObject.parseObject(resultDTO.getData() + "").getString("high_value");
                if(StringUtils.isNotEmpty(high_value)){
                    it.setInstMaxRedeem(high_value);//法人最大赎回份额
                }
            }
            latch.countDown();
        }
    }
}
