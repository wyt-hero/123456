package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.service.TradeAccountService;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wyt
 * @data 2020/3/7 17:35
 */
@Service
@Slf4j
public class TradeAccountImpl implements TradeAccountService {
    @Autowired
    RestemplateUtil restemplateUtil;
    /**
     * 交易账户处理-----C503机构开户
     *
     * @param json 用户传入参数
     * @return
     */
    public ResultDTO modifyInstitutionsAccount(String json) {
        //方法返回值
        ResultDTO result=new ResultDTO();
        try {
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.C503.getUrl();
            //restemplate方式调用接口
            String substring = json.substring(1, json.length() - 1);
            substring=substring.replaceAll(" ","");
            //System.out.println("C503url："+url +"&"+substring);
            log.info("C503url开户连接：{}",url +"&"+substring);
            result = restemplateUtil.postRest(json="", url +"&"+substring,null);
            if (result!=null) {
                //日志信息
                log.info("交易账户处理-----C503机构开户:{}",result.toString());

            }else {
                log.info("值为空：{}");
                //成功
                result.setResult(ResultEntity.TRUENULL.getCode());
                //信息
                result.setMessage(ResultEntity.TRUENULL.getMsg());
            }
        } catch (Exception e) {
            log.error("转换异常：{}",e.getStackTrace());
            //赋值错误信息
            result.setResult(ResultEntity.FAIL.getCode());
            //信息
            result.setMessage(ResultEntity.FAIL.getMsg());
        }
        //将实体类数据返回
        return result;
    }

    /**
     * 4.2基金账户销户----机构基金账号销户(C513)
     *
     * @param json 用户传入参数
     * @return
     */
    public ResultDTO modifyFundHousehold(String json) {
        //方法返回值
        ResultDTO resultDTO=new ResultDTO();
        try {
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.C513.getUrl();
            //restemplate方式调用接口
            String ta_acco = JSONObject.parseObject(json).getString("ta_acco");
            resultDTO = restemplateUtil.postRest(json="", url +"&"+"ta_acco="+ta_acco,null);
            if (resultDTO!=null) {
                //日志信息
                log.info("4.2基金账户销户----机构基金账号销户(C513):{}",resultDTO.toString());
                //日志信息
                log.info("认购结果:{}",resultDTO.toString());
            }else {
                log.info("值为空：{}");
                //成功
                resultDTO.setResult(ResultEntity.TRUENULL.getCode());
                //信息
                resultDTO.setMessage(ResultEntity.TRUENULL.getMsg());
            }
        } catch (Exception e) {
            log.error("转换异常：{}",e.getStackTrace());
            //赋值错误信息
            resultDTO.setResult(ResultEntity.FAIL.getCode());
            //信息
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
        }
        return resultDTO;
    }

    /**
     * 4.3账户结果回写----柜台审核结果查询(C479)
     * 4.4基金账户销户结果回写
     * 4.5基金账户确认结果查询
     *
     * @param json 用户传入参数
     * @return
     */
    public ResultDTO querytAuditResult(String json) {
        //方法返回值
        ResultDTO resultDTO=new ResultDTO();
        try {
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.C479.getUrl();
            //restemplate方式调用接口
            String trade_acco = JSONObject.parseObject(json).getString("trade_acco");
            resultDTO = restemplateUtil.postRest(json, url +"&trade_acco="+trade_acco,null);
            if (resultDTO!=null) {
                //日志信息
                log.info("认购结果:{}",resultDTO.toString());
            }else {
                log.info("值为空：{}");
                //成功
                resultDTO.setResult(ResultEntity.TRUENULL.getCode());
                //信息
                resultDTO.setMessage(ResultEntity.TRUENULL.getMsg());
            }
        } catch (Exception e) {
            log.error("转换异常：{}",e.getStackTrace());
            //赋值错误信息
            resultDTO.setResult(ResultEntity.FAIL.getCode());
            //信息
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
        }
        return resultDTO;
    }

    /**
     * 4.4基金账户销户结果回写
     * 4.5基金账户确认结果查询
     *
     * @param json 用户传入参数
     * @return
     */
    @Override
    public ResultDTO queryAccountResult(String json) {
        //方法返回值
        ResultDTO resultDTO=new ResultDTO();
        try {
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.S486.getUrl();
            //restemplate方式调用接口
            log.info("4.5接口请求路径：{}",url+json);
            resultDTO = restemplateUtil.postRest(json, url+json,null);
            if (resultDTO!=null) {
                //日志信息
                log.info("4.4基金账户销户结果回写或4.5基金账户确认结果查询:{}",resultDTO.toString());
                //日志信息
                log.info("认购结果:{}",resultDTO.toString());
            }else {
                log.info("值为空：{}");
                //成功
                resultDTO.setResult(ResultEntity.TRUENULL.getCode());
                //信息
                resultDTO.setMessage(ResultEntity.TRUENULL.getMsg());
            }
        } catch (Exception e) {
            log.error("转换异常：{}",e.getStackTrace());
            //赋值错误信息
            resultDTO.setResult(ResultEntity.FAIL.getCode());
            //信息
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
        }
        return resultDTO;
    }

    /**
     * 4.6增开基金账户-----机构增开(C504)
     *@param json 用户传入参数
     * @return
     */
    public ResultDTO modifyInstitution(String json) {
        //方法返回值
        ResultDTO resultDTO=new ResultDTO();
        try {
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.C512.getUrl();
            //restemplate方式调用接口
            json=json.substring(1,json.length()-1);
            resultDTO = restemplateUtil.postRest(json="", url +"&"+json,null);
            if (resultDTO!=null) {
                //日志信息
                log.info("4.6增开基金账户-----机构增开(C504):{}",resultDTO.toString());
            }else {
                log.info("值为空：{}");
                //成功
                resultDTO.setResult(ResultEntity.TRUENULL.getCode());
                //信息
                resultDTO.setMessage(ResultEntity.TRUENULL.getMsg());
            }
        } catch (Exception e) {
            log.error("转换异常：{}",e.getStackTrace());
            //赋值错误信息
            resultDTO.setResult(ResultEntity.FAIL.getCode());
            //信息
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
        }
        return resultDTO;
    }


}
