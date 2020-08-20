package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 13:25
 */
@Data
public class ResultDTO<T>{
    //接口版本号
    @JSONField(name = "Version")
    private String Version;
    //发送时间
    @JSONField(name = "SendTime")
    private String SendTime;
    //接收状态
    @SerializedName(value = "Code", alternate = "code")
    @JSONField(name = "Code")
    private String Code;
    //验签信息
    @JSONField(name = "Result")
    private Integer Result;
    //接收状态信息
    @SerializedName(value = "Message", alternate = "message")
    @JSONField(name = "Message")
    private String Message;
    //页码编号
    @JSONField(name = "PageIndex")
    private Integer PageIndex;

    //页数
    @JSONField(name = "PageSize")
    private Integer PageSize;

    public Integer getPageSize() {
        if(this.TotalCount!=null&&this.RowSize!=null){
            PageSize=TotalCount/RowSize;
            if(TotalCount%RowSize!=0){
                PageSize++;
            }
        }
        return PageSize;
    }

    //每页数量
    @SerializedName(value = "RowSize", alternate = "rowcount")
    @JSONField(name = "RowSize")
    private Integer RowSize;
    //总条数
    @SerializedName(value = "TotalCount", alternate = "total_count")
    @JSONField(name = "TotalCount")
    private Integer TotalCount;
    //接收接口返回数据
    @JSONField(name = "Data")
    private T Data;

    @JSONField(name = "YYSign")
    private String YYSign;

    //回写code
    private String errorNo;

    private String errorInfo;

}
