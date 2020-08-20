package com.xinhu.wealth.jgt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinhu.wealth.jgt.config.RestTemlateConfig;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wyt
 * @data 2020/3/16 17:40
 */
@Configuration
@Slf4j
public class RestemplateUtil {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private RestTemlateConfig restTemlateConfig;

    /**
     * @param parm 用户输入json数据
     * @param url  接口地址
     * @return 调用接口通用方法
     */
    public ResultDTO postRest(String parm, String url, Map<String,String> heads) {
        //返回数据
        ResultDTO resultDTO = new ResultDTO();
        HttpHeaders headers=new HttpHeaders();
        try {
            if (heads!=null){
                for (String key:heads.keySet()) {
                    headers.add(key,heads.get(key));
                }
            }
            //post请求方式
            HttpEntity<String> httpEntity = new HttpEntity<>(parm, headers);
            ResponseEntity<JSONObject> resultResponseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, JSONObject.class);
            if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                resultDTO.setData(resultResponseEntity.getBody()+"");
                //成功
                resultDTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
                resultDTO.setCode("200");
                //信息
                String data=resultDTO.getData()+"";
                if (data!=null&&!"null".equals(data)){
                    resultDTO.setMessage(JSONObject.parseObject(resultDTO.getData()+"").getString("message"));
                }
            }else {
                log.info("调用接口通用方法网络不通");
                resultDTO.setResult(ResultEntity.YUNYIERROR.getCode());//2 对应云毅错误代码   0成功
                resultDTO.setMessage(ResultEntity.FAIL.getMsg());
            }
        } catch (RestClientException e) {
            log.error("调用接口通用方法异常", e);
            resultDTO.setResult(ResultEntity.YUNYIERROR.getCode());//2 对应云毅错误代码   0成功
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
        }
        return resultDTO;
    }

    /**
     * sdk方式调用接口
     * @param parm
     * @param url
     * @return
     */
//    public ResultDTO sDKrest(String parm, String url) {
//        //返回数据
//        ResultDTO resultDTO = new ResultDTO();
//        try {
//            OpenResult call = apiUtils.call(url, parm);
//            System.out.println("调用接口返回的结果为:");
//            resultDTO.setData(call.getDetails());
//            resultDTO.setCode(Integer.parseInt(call.getCode()));
//            resultDTO.setMessage(call.getMessage());
//        } catch (Exception e) {
//            System.out.println("调用接口异常");
//            e.printStackTrace();
//        }
//        return resultDTO;
//    }

    /**
     * @param parm
     * @param url
     * @return
     */
    public ResultDTO getRest(String parm, String url) {
        //接收接口返回数据
        ResultDTO pageCodeDTO = new ResultDTO();
        try {
            //返回数据
            ResultDTO resultDTO = new ResultDTO();
            //get请求方式
            ResponseEntity<JSONObject> resultResponseEntity = restTemplate.exchange(url, HttpMethod.GET, null, JSONObject.class);
            if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                //调用成功，获取数据
                resultDTO.setData(resultResponseEntity.getBody().toJSONString());
                //成功
                resultDTO.setResult(0);
                resultDTO.setCode("200");
                //信息
                resultDTO.setMessage(JSONObject.parseObject(resultDTO.getData()+"").getString("message"));
            }
        } catch (Exception e) {
            log.error("errorMessage{}", e.getMessage());
            pageCodeDTO.setResult(202);
            pageCodeDTO.setMessage("连接超时");
            return pageCodeDTO;
        }
        return pageCodeDTO;
    }

    /**
     * @param parm 用户输入json数据
     * @param url  接口地址
     * @return 调用接口通用方法
     */
    public ResultDTO getRestV2(LinkedHashMap parm, String url) {
        //返回数据
        ResultDTO resultDTO = new ResultDTO();
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            addParam(uriBuilder,parm);
            ResponseEntity<JSONObject> resultResponseEntity = restTemplate.exchange(uriBuilder.build(), HttpMethod.GET, null, JSONObject.class);
            if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                String code = resultResponseEntity.getBody().get("code").toString();
                System.out.println(resultResponseEntity.getBody().toString());
                if(JGTConstant.ZX_SUC.equals(code)){
                    resultDTO.setData(resultResponseEntity.getBody().toJSONString());
                    resultDTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
                    resultDTO.setMessage(resultResponseEntity.getBody().get("message").toString());
                }else {
                    resultDTO.setData(resultResponseEntity.getBody().toJSONString());
                    resultDTO.setResult(ResultEntity.YUNYIERROR.getCode());
                    resultDTO.setMessage(resultResponseEntity.getBody().get("message").toString());
                }

            }else {
                log.info("调用接口通用方法网络不通");
                resultDTO.setResult(ResultEntity.YUNYIERROR.getCode());//2 对应云毅错误代码   0成功
                resultDTO.setMessage(ResultEntity.FAIL.getMsg());
            }
        } catch (Exception e) {
            log.error("调用接口通用方法异常", e);
            resultDTO.setResult(ResultEntity.YUNYIERROR.getCode());//2 对应云毅错误代码   0成功
            resultDTO.setMessage(ResultEntity.FAIL.getMsg());
        }
        return resultDTO;
    }

    //get方法拼接参数
    private static void addParam(URIBuilder uriBuilder, Object param) {
        String jsonStr = JSON.toJSONString(param);
        Map<String, Object> paramMap = JSON.parseObject(jsonStr);
        log.info("参数解析的map：" + paramMap);
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (null != entry.getValue()) {
                uriBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
    }
}
