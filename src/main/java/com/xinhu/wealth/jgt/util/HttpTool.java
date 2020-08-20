package com.xinhu.wealth.jgt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.constants.WebConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * HTTP请求
 *
 * @author dxt
 * @date 2019年11月4日11:01:42
 */
@Slf4j
public class HttpTool {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 10000;


    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        // 1秒不活动后验证连接
        connMgr.setValidateAfterInactivity(1000);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();

        //DefaultConnectionKeepAliveStrategy 默认实现
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                Args.notNull(response, "HTTP response");
                final HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    final HeaderElement he = it.nextElement();
                    final String param = he.getName();
                    final String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (final NumberFormatException ignore) {
                        }
                    }
                }
                return 1;
            }

        };
        final CloseableHttpClient httpclient = HttpClients.custom().setConnectionManagerShared(true)
                .setConnectionManager(connMgr)
                .setKeepAliveStrategy(myStrategy)
                .setDefaultRequestConfig(requestConfig)
                .evictExpiredConnections()
                .build();

    }

    /**
     * get请求
     * 响应json字符串
     */
    public static String nGet(String url, Object param) {
        if (!url.startsWith(WebConstant.URL_HEAD)) {
            url = WebConstant.URL_START + url;
        }

        CloseableHttpClient client = HttpClients.createDefault();//创建协议端
        HttpResponse response = null;
        String jsonStr = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (param != null) {
                addParam(uriBuilder, param);
            }
            log.info("get方法拼接参数" + uriBuilder.build());

            String result = null;
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            /**请求发送成功，并得到响应**/
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity(), "UTF-8");
                //jsonStr = StringEscapeUtils.unescapeJava(strResult);
                jsonStr =   JSONObject.parseObject(strResult).toString();//也能转码
            } else {
                log.error("请求状态非正常  当前状态码：" + response.getStatusLine());
            }
        } catch (URISyntaxException e) {
            log.info("get请求url转uriBuilder异常：" + e);
        } catch (ClientProtocolException e) {
            log.info("请求异常：" + e);
        } catch (IOException e) {
            log.info("请求超时：" + e);
            jsonStr = new JSONObject().fluentPut(ResultEntity.OUT_TIME.getCode()+"", ResultEntity.OUT_TIME.getMsg()).toString();
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return new JSONObject().put("40404","请求超时").toString();
            log.info("接口响应：" +jsonStr);
            return jsonStr;
        }
    }


    /**
     * get请求
     * @param  param 有序参数
     * 响应json字符串
     */
    public static String srotGet(String url, LinkedHashMap param) {
        if (!url.startsWith(WebConstant.URL_HEAD)) {
            url = WebConstant.URL_START + url;
        }
        CloseableHttpClient client = HttpClients.createDefault();//创建协议端
        HttpResponse response = null;
        String jsonStr = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (param != null) {
                addLinkedParam(uriBuilder, param);
            }
            log.info("get方法拼接参数" + uriBuilder.build());

            String result = null;
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            /**请求发送成功，并得到响应**/
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity(), "UTF-8");
                //jsonStr = StringEscapeUtils.unescapeJava(strResult);
                jsonStr =   JSONObject.parseObject(strResult).toString();//也能转码
            } else {
                log.error("请求状态非正常  当前状态码：" + response.getStatusLine());
            }
        } catch (URISyntaxException e) {
            log.info("get请求url转uriBuilder异常：" + e);
        } catch (ClientProtocolException e) {
            log.info("请求异常：" + e);
        } catch (IOException e) {
            log.info("请求超时：" + e);
            jsonStr = new JSONObject().fluentPut(ResultEntity.OUT_TIME.getCode()+"", ResultEntity.OUT_TIME.getMsg()).toString();
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return new JSONObject().put("40404","请求超时").toString();
            return jsonStr;
        }
    }
    //get方法拼接参数
    private static void addParam(URIBuilder uriBuilder, Object param) throws URISyntaxException {
        String jsonStr = ObjJsonUtil.objToJson(param);
        log.info("入参解析前：{}",jsonStr);
        Map<String, Object> paramMap = JSON.parseObject(jsonStr);
        log.info("参数解析的map：" + paramMap);
        if (paramMap!=null){
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                if (null != entry.getValue()&& StringUtils.isNotEmpty(entry.getValue()+"")) {
                    uriBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
        }
        log.info("除空结果：{}",uriBuilder.getQueryParams());
        log.info("连接：{}",uriBuilder.build());
    }



    //get方法拼接有序参数
    private static void addLinkedParam(URIBuilder uriBuilder, LinkedHashMap<String,Object> param) {
        //String jsonStr = ObjJsonUtil.objToJson(param);
        //Map<String, Object> paramMap = JSON.parseObject(jsonStr);
        log.info("map：" + param);
        param.forEach((key, value) -> {
            if (value != null) {
                uriBuilder.addParameter(key, String.valueOf(value));
            }
        });
    }
    /**
     * post请求
     *
     * @param url   请求地址
     * @param param 请求参数
     */
    public static String nPost(String url, Object param) {

        if (!url.startsWith(WebConstant.URL_HEAD)) {
            url = WebConstant.URL_START + url;
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = null;
        String jsonStr="";
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Accept-Charset", "utf-8");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json;");
        String charSet = "UTF-8";
        if (param != null) {
            String str = ObjJsonUtil.objToJson(param);
            StringEntity entity = new StringEntity(str, charSet);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");//发送json数据需要设置contentType
            httpPost.setEntity(entity);
        }
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String strResult = EntityUtils.toString(responseEntity, "UTF-8");
                //jsonStr = StringEscapeUtils.unescapeJava(strResult);
                jsonStr =   JSONObject.parseObject(strResult).toString();//也能转码
            } else {
                log.error("请求状态非正常  当前状态码：" + response.getStatusLine());
            }
        } catch (IOException e) {
            log.info("请求超时：" + e);
            log.error("请求异常：" , e);
            jsonStr = new JSONObject().fluentPut(ResultEntity.OUT_TIME.getCode()+"", ResultEntity.OUT_TIME.getMsg()).toString();
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonStr;
        }
    }





}
