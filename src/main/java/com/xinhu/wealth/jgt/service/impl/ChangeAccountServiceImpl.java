package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.UpdateCustomerInfoDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.UpdateAccInDTO;
import com.xinhu.wealth.jgt.service.ChangeAccountService;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ChangeAccountServiceImpl implements ChangeAccountService {

    @Autowired
    private RestemplateUtil restTemplateUtil;



    /**
     * C502修改客户信息----
     *
     * @param param 用户输入信息
     * @return
     */
    @Override
    public ResultDTO modifyCustomerInfo(String param) {
        //方法返回值
        ResultDTO resultDTO = new ResultDTO();
        //用户传入数据
        log.info("用户传入数据:{}", param);
        //最终返回数据
        String str = "";
        try {
            if (param != null) {
                //正常获取到返回值
                resultDTO = changeCustomerInfo(param);
                log.info("返回结果：{}", resultDTO.toString());
            } else {
                //验签失败
                //成功
                resultDTO.setResult(ResultEntity.SINGERRO.getCode());
                //信息
                resultDTO.setMessage(ResultEntity.SINGERRO.getMsg());
            }

        } catch (Exception e) {
            //返回值获取失败
            log.error("错误信息：{}", e.getStackTrace());
            //赋值错误信息
            resultDTO.setResult(ResultEntity.FAIL.getCode());
            //信息
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
        }
        return resultDTO;
    }


    /**
     * C502修改客户信息----
     *
     * @param param 用户输入信息
     * @return
     */
    private ResultDTO changeCustomerInfo(String param) {
        UpdateCustomerInfoDTO updateCustomerInfoDTO = new UpdateCustomerInfoDTO();
        //方法返回值
        ResultDTO resultDTO = new ResultDTO();
        try {
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.C502.getUrl();
            //restTemplate方式调用接口
            //处理入参
            Gson gson = new Gson();
            UpdateAccInDTO updateAccInDTO = gson.fromJson(param, UpdateAccInDTO.class);
            Map map = JSONObject.parseObject(JSONObject.toJSONString(updateAccInDTO), Map.class);
            map.remove("fax");
            String jsonString = map.toString();
            log.info("修改转换：{}", map.toString());
            jsonString = jsonString.replaceAll(":", "=");
            jsonString = jsonString.replace(",", "&");
            jsonString = jsonString.replace(" ", "");
            jsonString = jsonString.substring(1, jsonString.length() - 1);
            log.info("修改客户信息入参：{}", jsonString);
            log.info("修改客户信息的链接：{}", url + "&" + jsonString);
            resultDTO = restTemplateUtil.postRest(jsonString, url + "&" + jsonString, null);
            if (resultDTO.getResult() == 0) {
                //日志信息
                log.info("修改客户信息入参：{}", resultDTO.toString());
                return resultDTO;
            } else {
                log.info("值为空：{}");
                return resultDTO;
            }
        } catch (Exception e) {
            log.error("转换异常：{}", e.getStackTrace());
            //赋值错误信息
            resultDTO.setResult(ResultEntity.FAIL.getCode());
            //信息
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
            return resultDTO;
        }
    }


}
