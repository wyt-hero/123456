package com.xinhu.wealth.jgt.util;


import com.alibaba.fastjson.JSONObject;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;

/**
 * http请求方法
 * @author dxt
 * @date 2019年11月5日13:29:44
 * */
public class HttpSend {



    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url 地址
     * @return json字符串
     */
    public static String doGetStr(String url) {
        return HttpTool.nGet(url, null);
    }

    public static String doGetStr(String url, Object param) {
        return HttpTool.nGet(url, param);
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url 地址
     * @return json字符串
     */
       public static Object doGetObj(String url, Object resultEntity) {
        String str = HttpTool.nGet(url, null);
        return ObjJsonUtil.jsonToObj(str, resultEntity.getClass());
    }

    public static ResultDTO doGetResult(String url, Object param) {
        ResultDTO resultDTO = new ResultDTO();
        String str = HttpTool.nGet(url, param);
        resultDTO.setCode("9999");
        resultDTO.setResult(2);
        resultDTO.setData(str);
        //成功
        if (JGTConstant.ZX_SUC.equals(JSONObject.parseObject(str).getString("code"))) {
            resultDTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
            resultDTO.setCode("200");
            resultDTO.setResult(0);
        }
        //信息
        resultDTO.setMessage(JSONObject.parseObject(str).getString("message"));
        return resultDTO;
    }


    /**
     * 发送 GET 请求（HTTP）
     *
     * @param url 地址
     * @return 实体
     */
    public static Object doGetObj(String url, Object param, Object resultEntity) {
        String str = HttpTool.nGet(url, param);
        return ObjJsonUtil.jsonToObj(str, resultEntity.getClass());
    }

    /**
     * 发送 POST请求（HTTP），不带输入数据
     *
     * @param url 地址
     * @return json字符串
     */
    public static String doPostStr(String url) {
        return HttpTool.nPost(url, null);
    }

    public static String doPostStr(String url, Object param) {
        return HttpTool.nPost(url, param);
    }

    /**
     * 发送 POST请求（HTTP），不带输入数据
     *
     * @param url 地址
     * @return 实体
     */
    public static Object doPostObj(String url, Object resultEntity) {
        String str = HttpTool.nPost(url, null);
        return ObjJsonUtil.jsonToObj(str, resultEntity.getClass());
    }

    /**
     * 发送 POST 请求（HTTP）
     *
     * @param url 地址
     * @return 实体
     */
    public static Object doPostObj(String url, Object param, Object resultEntity) {
        String str = HttpTool.nPost(url, param);
        return ObjJsonUtil.jsonToObj(str, resultEntity.getClass());
    }

}
