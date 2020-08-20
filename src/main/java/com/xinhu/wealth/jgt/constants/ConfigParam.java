package com.xinhu.wealth.jgt.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 机构通项目常量
 * 普通常量在此处配置
 */
@Component
public class ConfigParam {
    /**
     * 植信接口地址
     * 此处 读取配置文件 随环境修改路径
     */
    public static String zxBaseUrl;


    public String getZxBaseUrl() {
        return zxBaseUrl;
    }

    @Value("${zhixin.base_url}")
    public  void setZxBaseUrl(String zxBaseUrl) {
        this.zxBaseUrl = zxBaseUrl;
    }

}
