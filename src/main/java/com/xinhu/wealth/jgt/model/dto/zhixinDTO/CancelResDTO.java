package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/10 11:06
 */
@Data
public class CancelResDTO {
    @JSONField(name = "O3Productcode")
    private String   O3Productcode;//O3产品代码
    @JSONField(name = "TACode")
    private String   TACode;//TA 代码
    @JSONField(name = "TAAccountID")
    private String   TAAccountID;//TA 基金帐号
    @JSONField(name = "Oserialno")
    private String   Oserialno;//申报编号
    @JSONField(name = "Appserialno")
    private String   Appserialno;//申请单编号
    @JSONField(name = "Transtate")
    private String   Transtate;//交易申报状态
    @JSONField(name = "TranStateDetail")
    private String   TranStateDetail;//交易申报状态信息
}
