package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 14:53
 * 4.1交易账户处理------C503机构开户
 */
@Data
public class TradingInstitutionsOpenDTO {
    @JSONField(name = "Oserialno")
    private String Oserialno;//流水号
    @SerializedName(value = "Appserialno", alternate = "allot_no")
    @JSONField(name = "Appserialno")
    private String Appserialno;//申请编号
}
