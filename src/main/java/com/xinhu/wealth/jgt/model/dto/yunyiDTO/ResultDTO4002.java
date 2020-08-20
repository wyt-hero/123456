package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 13:25
 */
@Data
public class ResultDTO4002<T>{
    //接口版本号
    @JSONField(name = "Version")
    private String Version;

    //发送时间
    @JSONField(name = "SendTime")
    private String SendTime;

    //验签信息
    @JSONField(name = "Result")
    private Integer Result;


    //接收状态信息
    @JSONField(name = "Message")
    private String Message;

    //接收接口返回数据
    @JSONField(name = "Data")
    private T Data;

    @JSONField(name = "YYSign")
    private String YYSign;
}
