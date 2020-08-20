package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/16 18:10
 * 认购实体类
 */
@Data
public class SubscriptionDTO {
    //接口版本号
    private final String Version="v1.0";
    //发送时间
    private String SendTime;
    //页码编号
    private Integer PageIndex;
    //页数
    private Integer PageSize;
    //每页数量
    @SerializedName(value = "RowSize", alternate = "rowcount")
    @JSONField(name = "rowcount")
    private Integer RowSize;
    //总条数
    @SerializedName(value = "TotalCount", alternate = "total_count")
    @JSONField(name = "total_count")
    private Integer TotalCount;
    //接收返回数据
    private JSONObject jsonObject;
}
