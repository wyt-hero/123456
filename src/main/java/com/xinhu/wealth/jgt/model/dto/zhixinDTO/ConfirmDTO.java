package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/25 17:37
 */
@Data
public class ConfirmDTO {
    //InvestorCode
    private String InvestorCode;//客户号
    @JSONField(name = "Oserialno")
    private String Oserialno;//申报编号
    //OrgAppserialno
    @SerializedName(value = "Appserialno", alternate = "allot_no")
    @JSONField(name = "Appserialno")
    private String Appserialno;//申请单编号
}
