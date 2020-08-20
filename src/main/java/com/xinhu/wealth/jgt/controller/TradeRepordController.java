package com.xinhu.wealth.jgt.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xinhu.wealth.jgt.mapper.OrderMapper;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ParticipationDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.service.OrderService;
import com.xinhu.wealth.jgt.util.DateUtil;
import com.xinhu.wealth.jgt.util.YYSignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyt
 * @data 2020/4/24 18:59
 * 此类用于交易类申报
 * 包含：下单接口，
 * 下单接口含：
 * 认购、申购、基金转换、修改分红方式、撤单、赎回
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TradeRepordController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderService orderService;

    /**
     * 交易类申报接口
     *
     * @param body 用户输入数据
     * @return
     */
    @PostMapping(value = "/trade/repord", produces = "application/json;charset=utf-8")
    public String order(@RequestBody String body,
                        @RequestHeader("SingText") String sing,
                        @RequestHeader("ProcessCode") String processCode,
                        @RequestHeader("Version") String version,
                        @RequestHeader("PageIndex") String pageIndex,
                        @RequestHeader("RowSize") Integer RowSize,//每页数量
                        @RequestHeader("SendTime") String sendTime) {
        if (StringUtils.isEmpty(pageIndex)) {
            pageIndex = "1";
        }
        log.info("用户输入数据：{}", body);
        boolean bool = YYSignUtil.isSign(sendTime, body, sing);
        //todo  发布需加上验签代码
        bool=true;
        //云毅返回结果
        ResultDTO resultDTO=new ResultDTO();
        if (bool) {
            //入参转换
            String busintype="";
            try {
                Gson gson=new Gson();
                busintype = JSONObject.parseObject(body).getString("Busintype");
            } catch (JsonSyntaxException e) {
                log.info("入参转换异常：{}",e);
                return "body参数格式错误";
            }
            log.info("交易开始");
            //赋值入参实体类
            ParticipationDTO participationDTO = queryValue(body, busintype, pageIndex, processCode, sendTime, version);
            //调用下单接口
            resultDTO=orderService.queryOrder(participationDTO);
        } else {
            //验签失败
            resultDTO.setSendTime(DateUtil.getTime());
            resultDTO.setVersion(version);
            resultDTO.setRowSize(RowSize);
            resultDTO.setResult(1);
            resultDTO.setMessage("验签失败");
            resultDTO.setPageIndex(Integer.parseInt(pageIndex));
            resultDTO.setYYSign("");
        }
        log.info("交易结束");
        return JSONObject.toJSONString(resultDTO);
    }

    /**
     * 赋值入参实体类
     * @param body 入参json
     * @param busintype 功能号
     * @param pageIndex 当前页索引
     * @param processCode 业务代码
     * @param sendTime 请求时间
     * @param version 版本号
     * @return
     */
    private ParticipationDTO queryValue(String body,String busintype,String pageIndex,String processCode,String sendTime,String version){
        ParticipationDTO participationDTO=new ParticipationDTO();
        participationDTO.setBody(body);
        participationDTO.setBusintype(busintype);
        participationDTO.setPageIndex(pageIndex);
        participationDTO.setProcessCode(processCode);
        participationDTO.setSendTime(sendTime);
        participationDTO.setVersion(version);
        return participationDTO;
    }
}
