package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.UUID;

/**
 * @author wyt
 * @data 2020/3/5 15:07
 * 4.6增开基金账户-------C504机构增开
 * dto
 */
@Data
public class AddTradingInstitutionsDTO {
    @JSONField(name = "Oserialno")
    private String Oserialno= UUID.randomUUID().toString().replaceAll("-","");//流水号
    @SerializedName(value = "Appserialno", alternate = "allot_no")
    @JSONField(name = "Appserialno")
    private String Appserialno;//申请编号
}
