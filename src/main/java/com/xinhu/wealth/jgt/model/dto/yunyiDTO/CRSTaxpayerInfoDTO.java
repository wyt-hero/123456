package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @date 2020-07-24-15-05-52
 * 纳税人信息列表
 */
@Data
public class CRSTaxpayerInfoDTO {
    private String BelongType;//纳税所属类
    private String IsMainTax;//是否为主纳税人信息
    private String TaxpayerId;//纳税人识别号
    private String TaxpayerCountry;//纳税居民国（地区）
    private String Reason;//如果无法提供纳税识别号码，填写原因
    private String ReasonType;//无居民国家/地区纳税人识别号的原因
}

