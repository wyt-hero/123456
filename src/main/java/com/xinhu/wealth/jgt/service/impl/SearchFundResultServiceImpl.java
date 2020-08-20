package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.AccResYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.InstitutionsAccountDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrderYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrgnLogoutDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.CancelResDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.OrderInYYDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.OrgnInZXDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.model.entity.OrderEntity;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.service.SearchFundResultService;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import com.xinhu.wealth.jgt.util.YYSignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class SearchFundResultServiceImpl implements SearchFundResultService {

    @Autowired
    private RestemplateUtil restTemplateUtil;
    @Autowired
    private PollingMapper pollingMapper;

    /**
     * 获取账户开户的回写结果
     *
     * @return
     */
    @Override
    public ResultDTO queryAccResult(ResultDTO resultDTO, AccResYunYiDTO accResYunYiDTO, OrgnInZXDTO orgnInZXDTO, String time) {
        Gson gson = new Gson();
        //开户成功
        //获取植信data
        InstitutionsAccountDTO institutionsAccountDTO = gson.fromJson(resultDTO.getData() + "", InstitutionsAccountDTO.class);

        if (institutionsAccountDTO!=null){
            //赋值回写入参
            //Investorcode;客户号
            accResYunYiDTO.setInvestorcode(institutionsAccountDTO.getClient_id());
            //TransactionAccountID;交易账号
            accResYunYiDTO.setTransactionAccountID(institutionsAccountDTO.getTrade_acco());
            //Oserialno;//申报编号null
            accResYunYiDTO.setOserialno(orgnInZXDTO.getOSerialno());
            //Appserialno;//申请单编号
            accResYunYiDTO.setAppserialno(institutionsAccountDTO.getAllot_no());
            accResYunYiDTO.setTranStateDetail("账户申报");
            //ranstate;//交易申报状态,null
            // Transtate;//交易申报状态
            accResYunYiDTO.setTranstate("0");
        }
        String jsonHui = JSONObject.toJSONString(accResYunYiDTO);
        //加签
        String sign = YYSignUtil.sign(time + "&" + jsonHui, JGTConstant.YY_PRI_KEY);
        Map<String, String> headers = new HashMap<>();
        headers.put("SingText", sign);
        headers.put("Version", "1.1");
        headers.put("SendTime", time);
        headers.put("InstitutionCode", "334");
        headers.put("ProcessCode", "4003");
        //得到植信数据
        //回写
        log.info("SZ50回写入参：{}", jsonHui);
        log.info("SZ50回写请求头入参：{}", headers);
        ResultDTO rest = restTemplateUtil.postRest(jsonHui, PathZXConstants.SZ50_1.getUrl(), headers);
        log.info("SZ50回写相应：{}", rest);
        return rest;
    }

    /**
     * 调用植信S445接口做账户申请查询
     *
     * @return
     */
    @Override
    public String queryUserInfoByTradeNo(String allotNo) {
        //获取S445url
        String S445URL = PathZXConstants.S445.getUrl() + "&trade_acco=" + allotNo;
        ResultDTO resultDTO = restTemplateUtil.postRest("", S445URL, null);
        String code = "";//成功或失败代码
        String confirmFlag = "";//修改确认标识
        boolean bool = false;
        if (StringUtils.isNotEmpty(resultDTO.getData().toString())) {
            code = JSONObject.parseObject(resultDTO.getData().toString()).getString("code");
            confirmFlag = JSONObject.parseObject(resultDTO.getData().toString()).getString("confirm_flag");
        }
        if ("ETS-5BP0000".equals(code)) {
            bool = true;
        }
        return confirmFlag;
    }

    /**
     * 调用修改账户回写
     *
     * @return
     */
    @Override
    public ResultDTO queryErrorNo(AccResYunYiDTO accResYunYiDTO, OrgnInZXDTO orgnInZXDTO, String allotNo, String time) {
        //根据流水号获取客户号
        PollingEntity pollingEntity=new PollingEntity();
        pollingEntity.setOSerialno(orgnInZXDTO.getOSerialno());
        pollingEntity = pollingMapper.selectOne(pollingEntity);
        if (pollingEntity!=null){
            String accResult = pollingEntity.getAccResult();
            String client_id = JSONObject.parseObject(accResult).getString("client_id");
            //客户号
            accResYunYiDTO.setInvestorcode(client_id);
        }

        //TransactionAccountID;交易账号
        accResYunYiDTO.setTransactionAccountID(orgnInZXDTO.getTransactionAccountID());
        //Oserialno;//申报编号null
        accResYunYiDTO.setOserialno(orgnInZXDTO.getOSerialno());
        //Appserialno;//申请单编号
        accResYunYiDTO.setAppserialno(allotNo);
        accResYunYiDTO.setTranStateDetail("账户申报");
        //ranstate;//交易申报状态,null
        // Transtate;//交易申报状态
        accResYunYiDTO.setTranstate("0");

        String jsonHui = JSONObject.toJSONString(accResYunYiDTO);
        //加签
        String sign = YYSignUtil.sign(time + "&" + jsonHui, JGTConstant.YY_PRI_KEY);
        Map<String, String> headers = new HashMap<>();
        headers.put("SingText", sign);
        headers.put("Version", "1.1");
        headers.put("SendTime", time);
        headers.put("InstitutionCode", "334");
        headers.put("ProcessCode", "4003");
        //得到植信数据
        //回写
        log.info("修改账户信息回写入参：{}", jsonHui);
        log.info("修改账户信息回写请求头入参：{}", headers);
        ResultDTO rest = restTemplateUtil.postRest(jsonHui, PathZXConstants.SZ50_1.getUrl(), headers);
        log.info("修改账户信息回写响应：{}", rest);
        return rest;
    }

    /**
     * 销户回写
     *
     * @param orgnLogoutDTO
     * @param allotNo
     * @param time
     * @return
     */
    @Override
    public ResultDTO queryAllotNo(OrgnLogoutDTO orgnLogoutDTO, String allotNo, String time) {
        //回写入参
        CancelResDTO cancelResDTO = new CancelResDTO();

        //TACode;//TA 代码
        cancelResDTO.setTACode(orgnLogoutDTO.getTACode());
        //TAAccountID;//TA 基金帐号
        cancelResDTO.setTAAccountID(orgnLogoutDTO.getTAAccountID());
        log.info("销户回写的查询销户结果:{}", allotNo);
        //Appserialno;//申请单编号
        cancelResDTO.setAppserialno(allotNo);
        //Oserialno
        cancelResDTO.setOserialno(orgnLogoutDTO.getOSerialno());
        //Transtate;//交易申报状态
        cancelResDTO.setTranstate("0");
        //TranStateDetail;//交易申报状态信息
        cancelResDTO.setTranStateDetail("账户销户成功");
        String jsonHui = JSONObject.toJSONString(cancelResDTO);
        //赋值headers
        //转成string，准备加签
        //加签
        String sign = YYSignUtil.sign(time + "&" + jsonHui, JGTConstant.YY_PRI_KEY);
        //map存储，请求头信息
        Map<String, String> headers = new LinkedHashMap<>();
        //Version;//接口版本号
        headers.put("ProcessCode", "4004");
        headers.put("SingText", sign);
        headers.put("Version", "1.1");
        //销售渠道 InstitutionCode
        headers.put("InstitutionCode", "334");
        headers.put("SendTime", time);
        //获取账户类销户链接
        //回写
        log.info("销户回写数据：{}", jsonHui);
        log.info("销户回写数据请求头：{}", headers);
        ResultDTO rest = restTemplateUtil.postRest(jsonHui, PathZXConstants.SZ50_1.getUrl(), headers);
        return rest;
    }

    /**
     * 交易类回写，获取allotNo
     *
     * @param orderEntity
     * @return
     */
    @Override
    public String queryTradeAllotNo(OrderEntity orderEntity) {
        //申请编号
        String allot_no = "";
        if (StringUtils.isNotEmpty(orderEntity.getAllotNo())) {
            allot_no = orderEntity.getAllotNo();
        }
        return allot_no;
    }

    /**
     * 交易类回写入参,返回值为，回写的erroNo
     *
     * @param orderYunYiDTO
     * @param allot_no
     * @param time
     * @param orderEntity
     * @return RenGouZXDTO renGouZXDTO,ResultDTO resultDTO
     */
    @Override
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
        if (StringUtils.isEmpty(allot_no)) {
            orderInYYDTO.setTranstate("1");
            orderInYYDTO.setTranStateDetail("交易失败");
        }
        //转成string，准备加签
        String jsonHui = JSONObject.toJSONString(orderInYYDTO);
        //加签
        String sign = YYSignUtil.sign(time + "&" + jsonHui, JGTConstant.YY_PRI_KEY);
        //回写云毅S407_1
        //获取文件上传的网址
        String S407_1Url = PathZXConstants.C4AB.getUrl();
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
        String errorNo = "";
        log.info("交易类回写入参body：{}", jsonHui);
        log.info("交易类回写请求头：{}", headers);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO = restTemplateUtil.postRest(jsonHui, S407_1Url, headers);
        resultDTO = JSONObject.parseObject(resultDTO.getData() + "", ResultDTO.class);
        log.info("交易类回写返回结果:{}", resultDTO);
        //如果，查询成功，并且回写成功，修改状态
        //String data = resultDTO.getData() + "";
        errorNo = resultDTO.getErrorNo();
        return errorNo + "&" + resultDTO.getErrorInfo();
    }

}
