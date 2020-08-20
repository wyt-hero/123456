package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/9 13:22
 * 4.9提交风险问卷答案(C509)入参
 */
@Data
public class YYDangeAssess {
    /**
     * 交易账户
     */
    private String  TransactionAccountID;

    /**
     * 机构标识
     */
    private String IndividualOrInstitution;
    /**
     * 投资人户名（账户全称）
     */
    private String  InvestorName;
    /**
     * 证件类别
     */
    private String  CertificateType;
    /**
     * 证件号码
     */
    private String  CertificateNo;
    /**
     * 答题内容
     */
    private String  QuestionContent;

}
