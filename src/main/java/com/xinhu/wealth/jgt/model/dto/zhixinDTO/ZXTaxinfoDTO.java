package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @date 2020-07-24-15-28-11
 */
@Data
public class ZXTaxinfoDTO {
    @SerializedName(value = "belong_type", alternate = "BelongType")
    @JSONField(name = "belong_type")
    private String belong_type;//纳税所属类
    @SerializedName(value = "is_main_tax", alternate = "IsMainTax")
    @JSONField(name = "is_main_tax")
    private String is_main_tax;//是否为主纳税人信息

    private String json_belong_id="";//JSON对象所属编号
    private String json_id="";//JSON对象自身编号

    @SerializedName(value = "no_tax_no_reason", alternate = "ReasonType")
    @JSONField(name = "no_tax_no_reason")
    private String no_tax_no_reason;//无居民国家/地区纳税人识别号的原因
    @SerializedName(value = "tax_explain", alternate = "Reason")
    @JSONField(name = "tax_explain")
    private String tax_explain;//如果无法提供纳税识别号码，填写原因

    @SerializedName(value = "tax_nation", alternate = "TaxpayerCountry")
    @JSONField(name = "tax_nation")
    private String tax_nation;//纳税居民国（地区）
    @SerializedName(value = "tax_no", alternate = "TaxpayerId")
    @JSONField(name = "tax_no")
    private String tax_no;//税务登记证号码
}
