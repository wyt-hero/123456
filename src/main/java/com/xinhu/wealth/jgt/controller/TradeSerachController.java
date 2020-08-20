package com.xinhu.wealth.jgt.controller;

import com.alibaba.fastjson.JSONObject;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ParticipationDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.service.OrderService;
import com.xinhu.wealth.jgt.util.DateUtil;
import com.xinhu.wealth.jgt.util.YYSignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyt
 * @data 2020/4/26 10:49
 * 此接口用于交易类查询
 * 包含：
 * 交易确认查询（2.2）
 * 分红查询（2.3）
 * 持仓查询（2.4）
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TradeSerachController {
    @Autowired
    OrderService orderService;
    /**
     * 交易类查询接口
     *
     * @param body 用户输入数据
     * @return
     */
    @PostMapping(value = "/trade/search", produces = "application/json;charset=utf-8")
    public String tradeSearch(@RequestBody String body,
                              @RequestHeader("SingText") String sing,
                              @RequestHeader("ProcessCode") String processCode,
                              @RequestHeader("Version") String version,
                              @RequestHeader(name = "PageIndex",required = false) Integer pageIndex,
                              @RequestHeader("RowSize") Integer rowSize ,
                              @RequestHeader("SendTime") String sendTime) {

        log.info("用户输入数据：{}", body);
        boolean bool = YYSignUtil.isSign(sendTime, body, sing);
        //todo  发布需加上验签代码
        bool=true;
        //云毅返回值实体类
        ResultDTO resultDTO=new ResultDTO();
        log.info("交易类查询接口验签开始");
        log.info("交易类查询接口验签结果：{}",bool);
        if (bool){
            //获取功能号
            //赋值入参实体类
            ParticipationDTO participationDTO = queryValue(body, rowSize, pageIndex + "", processCode, sendTime, version);
            //调用交易类查询接口
            resultDTO=orderService.queryOrderSearch(participationDTO);
        }else {
            resultDTO.setVersion(version);
            resultDTO.setSendTime(DateUtil.getTime());
            resultDTO.setResult(ResultEntity.YUNYISINGERROR.getCode());
            resultDTO.setMessage(ResultEntity.YUNYISINGERROR.getMsg());
            resultDTO.setYYSign("");
        }
        return JSONObject.toJSONString(resultDTO);
    }

    /**
     * 入参实体类赋值
     * @param body 入参
     * @param rowSize 一页总行数
     * @param pageIndex 当前页索引
     * @param processCode 业务代码
     * @param sendTime 请求时间
     * @param version 版本号
     * @return
     */
    private ParticipationDTO queryValue(String body, Integer rowSize, String pageIndex, String processCode, String sendTime, String version){
        ParticipationDTO participationDTO=new ParticipationDTO();
        participationDTO.setBody(body);
        participationDTO.setRowSize(rowSize);
        participationDTO.setPageIndex(pageIndex);
        participationDTO.setProcessCode(processCode);
        participationDTO.setSendTime(sendTime);
        participationDTO.setVersion(version);
        return participationDTO;
    }
}
