package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/9 18:55
 * 2.5回写入参
 */
@Data
public class OrderInYYDTO {
    @JSONField(name = "TransactionAccountID")
    private String TransactionAccountID;//交易账号
    @JSONField(name = "Appserialno")
    private String Appserialno;//申请单编号
    @JSONField(name = "Transtate")
    private String Transtate;//交易申报状态
    @JSONField(name = "TranStateDetail")
    private String TranStateDetail="回写";//申报状态信息
    @JSONField(name = "Oserialno")
    private String Oserialno;//申报编号
}
