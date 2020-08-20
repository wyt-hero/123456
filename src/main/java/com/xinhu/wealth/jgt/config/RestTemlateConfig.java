package com.xinhu.wealth.jgt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author wyt
 * @data 2020/3/4 09:52
 */
@Configuration
public class RestTemlateConfig {
    static HttpHeaders headers = new HttpHeaders();
    static MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(20000);
        factory.setReadTimeout(20000);
        return factory;
    }

    public static HttpHeaders getHeaders() {
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }
    public static HttpHeaders getHeaders(Map<String,String> map) {
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (map!=null){
            for (String key:map.keySet()) {
                headers.add(key,map.get(key));
            }
        }
        return headers;
    }
}
