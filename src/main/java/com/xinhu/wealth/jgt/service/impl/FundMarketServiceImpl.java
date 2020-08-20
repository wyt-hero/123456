package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.Body3001;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.MarketInfoDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.service.FundMarketService;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author wyt
 * @data 2020/3/7 17:18
 */
@Service("fundMarketService")
@Slf4j
public class FundMarketServiceImpl implements FundMarketService {
    @Autowired
    RestemplateUtil restemplateUtil;
    /**
     * 3.1行情信息查询-------S428基金行情信息查询
     *
     * @param json 用户输入参数
     * @return
     */
    @Override
    public ResultDTO queryFundMarket(String json, String indexPage, String pageSize) {
        Body3001 body = JSONObject.parseObject(json, Body3001.class);

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        if(StringUtils.isNotEmpty(body.getFundcode())){
            map.put("fund_code",body.getFundcode());
        }
        if(StringUtils.isNotEmpty(indexPage)){
            map.put("request_pageno",indexPage);
        }
        if(StringUtils.isNotEmpty(pageSize)){
            map.put("request_num",pageSize);
        }
        List<MarketInfoDTO> marketInfoDTOList = null;
        //返回参数接受
        ResultDTO resultDTO=new ResultDTO();
        try {
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.S428.getUrl();

            //restemplate方式调用接口
            resultDTO = restemplateUtil.getRestV2(map,url);
            if (resultDTO.getData()!=null) {
                //日志信息
                log.info("行情结果:{}",resultDTO);
                //将实体类数据返回
                return resultDTO;
            }else {
                log.info("值为空：{}");
                return resultDTO;
            }
        } catch (Exception e) {
            log.error("转换异常：{}",e.getStackTrace());
            //赋值错误信息
            resultDTO.setResult(ResultEntity.FAIL.getCode());
            //信息
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
            return resultDTO;
        }
    }
}
