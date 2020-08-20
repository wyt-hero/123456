package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @date 2020-08-11-18-09-47
 * 4.7修改交易密码云毅入参实体类
 */
@Data
public class ModifyZXTradePwd {

    /**
     *  机构标识
     */
    private String InstitutionMarking;

    /**
     *  //交易账户
     */
    private String  TransactionAccountID;
    /**
     * 证件类别
     */
    private String  CertificateType;
    /**
     * 证件号码
     */
    private String  CertificateNo;
    /**
     * 原密码
     */
    private String  Password;
    /**
     * 新密码
     */
    private String  NewPassword;


}
