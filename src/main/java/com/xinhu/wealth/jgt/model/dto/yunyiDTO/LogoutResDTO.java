package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

import java.util.UUID;

/**
 * @author wyt
 * @data 2020/4/2 18:06
 * S486需申请接口授权
 */
@Data
public class LogoutResDTO {
    private String  Version;//接口版本号
    private String   InstitutionCode;//销售渠道
    private String   ProcessCode;//功能代码
    private String   O3Productcode;//O3产品代码
    private String   TACode;//TA 代码
    private String   TAAccountID;//TA 基金帐号
    private String   Oserialno= UUID.randomUUID().toString().replaceAll("-","");//申报编号
    private String   Appserialno;//申请单编号
    private String   Transtate;//交易申报状态
    private String   TranStateDetail;//交易申报状态信息
    private String   SendTime;//发送时间
    private String   SingText;//签名
}
