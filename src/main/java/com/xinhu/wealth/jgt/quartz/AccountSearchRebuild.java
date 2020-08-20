package com.xinhu.wealth.jgt.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.mapper.CancleAccMapper;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.model.entity.CancleAccEntity;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 基金账户确认结果查询定时任务
 *
 * @author 陈小宁
 * @data 2020/4/15
 */
@Slf4j
@Component
public class AccountSearchRebuild {

    /**
     * 账户mapper
     */
    @Autowired
    private PollingMapper pollingMapper;

    @Autowired
    private CancleAccMapper cancleAccMapper;

    /**
     * 远程调用链接工具
     */
    @Autowired
    private RestemplateUtil restTemplateUtil;


    /*
     *
     * 从植信查询确认账户保存到数据库
     * */
    public void queryAccountConfirm() {//查询确认账户
        log.info("***********账户确认查询定时任务");
        List<PollingEntity> list = pollingMapper.selectConfirmResult();
        if (list == null || list.size() == 0) {
            log.info("***********账户确认查询定时任务没有查到数据");
            return;
        }
        log.info("***********账户确认查询定时任务共有{}条数据", list.size());
        int count=0;
        for (PollingEntity entity : list) {
            count++;
            //根据交易账号获取确认数据
            JSONObject object = queryS486ByTransactionAccountID(entity.getTransactionAccountID());
            if (object!=null&&Integer.parseInt(object.getString("total_count")) > 0) {
                JSONArray jsonArray = JSONArray.parseArray(object.getString("accountConfirmQueries"));
                JSONObject jsonObjectS486 = null;
                PollingEntity pollingEntity = new PollingEntity();
                if(jsonArray!=null){
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonTemp = jsonArray.getJSONObject(i);
                        if (StringUtils.isNotEmpty(entity.getTaCode()) && entity.getTaCode().equals(jsonTemp.getString("allot_no"))) {
                            jsonObjectS486 = jsonTemp;
                            if (jsonObjectS486 != null) {
                                String json = queryMap(jsonObjectS486, entity);
                                log.info("**************账户确认查询定时任务json={}", json.toString());
                                pollingEntity.setId(entity.getId());
                                pollingEntity.setConfirmResult(json);
                                String now = LocalDate.now().toString().replaceAll("-", "");
                                pollingEntity.setAffirmDate(now);
                                log.info("账户确认查询入参结果：{}", json);
                                pollingMapper.updateByPrimaryKeySelective(pollingEntity);
                            }
                        }

                    }
                }
            }
        }
        log.info("当前执行次数：{}",count);
        //销户确认开始
        //获取销户成功，且未加入确认信息的数据
        List<CancleAccEntity> select = cancleAccMapper.selectConfirm();
        if (select != null && select.size() > 0) {
            for (CancleAccEntity cancle : select) {
                //获取交易账号
                String transactionAccountID = "";
                if (cancle != null) {
                    transactionAccountID = JSONObject.parseObject(cancle.getBody()).getString("TransactionAccountID");
                }
                JSONObject object = queryS486ByTransactionAccountID(transactionAccountID);
                if (object!=null&&Integer.parseInt(object.getString("total_count")) > 0) {
                    JSONArray jsonArray = JSONArray.parseArray(object.getString("accountConfirmQueries"));
                    JSONObject jsonObjectS486 = null;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonTemp = jsonArray.getJSONObject(i);
                        String allot_no = JSONObject.parseObject(cancle.getZXResult()).getString("allot_no");
                        if (StringUtils.isNotEmpty(allot_no) && allot_no.equals(jsonTemp.getString("allot_no"))) {
                            jsonObjectS486 = jsonTemp;
                            if (jsonObjectS486 != null) {
                                Object json = queryMap(jsonObjectS486, cancle);
                                log.info("**************销户确认查询定时任务json={}", json);
                                cancle.setConfirmResult(json.toString());
                                cancle.setAffirmDate(JSONObject.parseObject(json.toString()).getString("TransactionCfmDate"));
                                cancle.setUpdate_time(LocalDateTime.now());
                                cancleAccMapper.updateByPrimaryKeySelective(cancle);
                            }
                        }
                    }
                }
            }
        }
        //销户确认结束
    }

    private JSONObject queryS486ByTransactionAccountID(String transactionAccountID) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("trade_acco", transactionAccountID);
        //调用植信S486接口根据交易账号查询确认账户
        ResultDTO resultDTO = restTemplateUtil.getRestV2(map, PathZXConstants.S486.getUrl());
        log.info("*****************账户确认查询定时任务-S486-trade_acco={}返回的数据={}", transactionAccountID, resultDTO.toString());
        if (resultDTO.getResult() != ResultEntity.YUNYISUCCESS.getCode()) {
            return null;
        }
        JSONObject object = JSON.parseObject((String) resultDTO.getData());
        return object;
    }

    /**
     * 赋值confirm_reult,并添加到pollling表
     *
     * @param jsonObjectS486
     * @param entity
     * @return
     */
    private String queryMap(JSONObject jsonObjectS486, PollingEntity entity) {
        log.info("赋值confirm_reult,并添加到pollling表入参：{}", entity);
        String confirmFlag = jsonObjectS486.getString("confirm_flag");
        //获取基金账号
        String taAccountID = "";
        boolean isChange = StringUtils.isNotEmpty(entity.getBeforeBody());
        if (isChange) {
            taAccountID = getTAAccountID(entity.getTransactionAccountID(), entity.getBeforeBody());
        } else {
            taAccountID = getTAAccountID(entity.getTransactionAccountID(), entity.getBody());
        }
        PollingEntity pollingEntity = new PollingEntity();
        pollingEntity.setId(entity.getId());
        pollingEntity.setAffirmDate(jsonObjectS486.getString("affirm_date"));//确认日期
        String allotNo = jsonObjectS486.getString("allot_no");//申请编号
        String confirmId = jsonObjectS486.getString("confirm_id");//确认编号
        String cofirmCause = jsonObjectS486.getString("cofirm_cause");//确认失败原因
        //存入数据库
        Map<String, Object> map = new HashMap<>();
        Map mapParam = new HashMap();//参数拼接
        mapParam.put("Appserialno", allotNo);
        JSONObject jsonBody = new JSONObject();
        if (isChange) {
            jsonBody = JSONObject.parseObject(entity.getBeforeBody());
        } else {
            jsonBody = JSONObject.parseObject(entity.getBody());
        }
        mapParam.put("Busintype", jsonBody.getString("Busintype"));
        mapParam.put("Oserialno", entity.getOSerialno());
        if ("1".equals(confirmFlag)) {
            mapParam.put("ReturnCode", "0000");
        } else {
            mapParam.put("ReturnCode", "9999");
        }
        mapParam.put("TAAccountID", taAccountID);
        if (StringUtils.isEmpty(taAccountID)) {
            mapParam.put("ErrorMsg", "基金帐号为空");
            mapParam.put("ReturnCode", "9999");
            mapParam.put("TAAccountID","0000");
        }
        mapParam.put("TASerialno", confirmId);
        mapParam.put("TransactionAccountID", entity.getTransactionAccountID());
        mapParam.put("TransactionCfmDate", jsonObjectS486.getString("affirm_date"));
        mapParam.put("InvestorCode", JSONObject.parseObject(entity.getAccResult()).getString("client_id"));//客户号
        mapParam.put("ErrorMsg", cofirmCause);
        log.info("账户确认查询最终返回结果：{}", mapParam);
        return JSONObject.toJSONString(mapParam);
    }

    /**
     * 赋值confirm_reult,并添加到pollling表
     *
     * @param jsonObjectS486
     * @param cancle
     * @return
     */
    private Object queryMap(JSONObject jsonObjectS486, CancleAccEntity cancle) {

        String confirmFlag = jsonObjectS486.getString("confirm_flag");
        //获取基金账号
        String taAccountID = cancle.getTAACount();
        PollingEntity pollingEntity = new PollingEntity();
        pollingEntity.setId(cancle.getId());
        pollingEntity.setAffirmDate(jsonObjectS486.getString("affirm_date"));//确认日期
        String allotNo = jsonObjectS486.getString("allot_no");//申请编号
        String confirmId = jsonObjectS486.getString("confirm_id");//确认编号
        String cofirmCause = jsonObjectS486.getString("cofirm_cause");//确认失败原因
        Map mapParam = new HashMap();
        mapParam.put("Appserialno", allotNo);
        mapParam.put("Oserialno", cancle.getOSerialno());
        if ("1".equals(confirmFlag)) {
            mapParam.put("ReturnCode", "0000");
        } else {
            mapParam.put("ReturnCode", "9999");
        }
        mapParam.put("TAAccountID", taAccountID);
        mapParam.put("TASerialno", confirmId);
        String transactionAccountID = JSONObject.parseObject(cancle.getBody()).getString("TransactionAccountID");
        mapParam.put("TransactionAccountID", transactionAccountID);
        mapParam.put("TransactionCfmDate", jsonObjectS486.getString("affirm_date"));
        mapParam.put("Busintype", jsonObjectS486.getString("002"));
        if (StringUtils.isEmpty(taAccountID)) {
            mapParam.put("ErrorMsg", "基金帐号为空");
            mapParam.put("ReturnCode", "9999");
        }
        mapParam.put("ErrorMsg", cofirmCause);
        Object json = JSONObject.toJSON(mapParam);
        return json;
    }

    /**
     * @param tradeAcco 基金账户
     * @param body      入参body
     * @return 获取基金账号
     * todo ...................
     */

    private String getTAAccountID(String tradeAcco, String body) {
        String taCode = getTACode(body);
        LinkedHashMap<String, String> map405 = new LinkedHashMap<>();
        map405.put("trade_acco", tradeAcco);
        //基金账号查询(S405)
        ResultDTO resultDTO405 = restTemplateUtil.getRestV2(map405, PathZXConstants.S405.getUrl());
        log.info("*****************账户确认查询定时任务-S405-trade_acco={}返回的数据={}", tradeAcco, resultDTO405.toString());
        String ta_acco = "";
        if (resultDTO405.getResult() == ResultEntity.YUNYISUCCESS.getCode()) {
            JSONObject object405 = JSONObject.parseObject((String) resultDTO405.getData());
            if (Integer.parseInt(object405.getString("total_count")) > 0) {
                JSONArray jsonArray405 = JSONArray.parseArray(object405.getString("fundAccountQuerys"));
                JSONObject temp = jsonArray405.getJSONObject(0);
                String ta_no = temp.getString("ta_no");
                if (StringUtils.isNotEmpty(taCode)&&taCode.equals(ta_no)) {
                    ta_acco = temp.getString("ta_acco");
                }
            }
        }
        log.info("ta_acco最终结果为：{}", ta_acco);
        return ta_acco;
    }

    /**
     * 获取TaCode
     *
     * @param body
     * @return
     */
    private String getTACode(String body) {
        String taCode = JSONObject.parseObject(body).getString("TACode");
        return taCode;
    }
}
