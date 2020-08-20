package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 17:50
 */
@Data
public class OrgnLogoutDTO {

    private String O3Productcode;//O3产品代码
    private String TACode;//TA 代码
    private String TAAccountID;//TA 基金帐号
    private String OSerialno;
    private String Password;//密码


//    private String Version;//接口版本号
//    private String ProcessCode;//功能代码
//    private String InstitutionMarking;//机构标识
//    private String TransactionAccountID;//交易账户
//    private String OSerialno = UUID.randomUUID().toString().replaceAll("-", "");//O3 流水号
//    private String SendTime;//发送时间
//    private String SingText;//签名

}
