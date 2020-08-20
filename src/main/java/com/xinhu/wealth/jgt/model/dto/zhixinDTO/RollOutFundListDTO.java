package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wyt
 * @data 2020/3/10 10:46
 */
@Data
public class RollOutFundListDTO {

    private String ProcessCode;//功能代码
    private String InstitutionMarking;//机构标识
    private String MsgSerialno;//消息流水号
    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    @JSONField(name = "Fdate")
    private String Fdate= df.format(new Date());//日期

    @SerializedName(value = "Fundcode", alternate = "fund_code")
    @JSONField(name = "Fundcode")
    private String Fundcode;//基金代码
}
