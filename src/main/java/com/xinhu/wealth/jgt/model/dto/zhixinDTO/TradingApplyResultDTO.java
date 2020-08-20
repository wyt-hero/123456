package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 13:39
 * 2.5交易申请结果回写-------S407交易结果查询
 */
@Data
public class TradingApplyResultDTO {
    @SerializedName(value = "errorNo", alternate = "code")
    @JSONField(name = "errorNo")
    private String errorNo;//接收状态
    @SerializedName(value = "errorInfo", alternate = "message")
    @JSONField(name = "errorInfo")
    private String errorInfo;//接收状态信息
}
