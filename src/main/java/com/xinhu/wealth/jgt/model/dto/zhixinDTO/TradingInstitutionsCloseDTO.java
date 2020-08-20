package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 14:57
 * 4.2基金账户销户------C513机构基金账户销户
 */
@Data
public class TradingInstitutionsCloseDTO {
//    private String  Oserialno=UUID.randomUUID().toString().replaceAll("-","");//流水号

    @JSONField(name = "Oserialno")
    private String  Oserialno;
    @SerializedName(value = "Appserialno",alternate = "allot_no")
    @JSONField(name = "Appserialno")
    private String  Appserialno;//申请编号

    public TradingInstitutionsCloseDTO() {
    }

    public TradingInstitutionsCloseDTO(String oserialno, String appserialno) {
        Oserialno = oserialno;
        Appserialno = appserialno;
    }
}
