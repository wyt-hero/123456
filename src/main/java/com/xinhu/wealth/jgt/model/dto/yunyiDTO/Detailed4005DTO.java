package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Detailed4005DTO {


    /**
     * 申报编号
     */
    @JSONField(name = "Oserialno")
    private String Oserialno;

    /**
     * 申请单编号
     */
    @JSONField(name = "Appserialno")
    private String Appserialno;

    /**
     * 交易确认日期
     */
    @JSONField(name = "TransactionCfmDate")
    private String TransactionCfmDate;


    /**
     * 交易账号
     */
    @JSONField(name = "TransactionAccountID")
    private String TransactionAccountID;


    /**
     * 基金帐号
     */
    @JSONField(name = "TAAccountID")
    private String TAAccountID;


    /**
     * 业务类型
     */
    @JSONField(name = "Busintype")
    private String Busintype;

    /**
     * 确认流水号
     */
    @JSONField(name = "TASerialno")
    private String TASerialno;


    /**
     * 确认结果  0000 成功 其它 失败
     */
    @JSONField(name = "ReturnCode")
    private String ReturnCode;

    /**
     * 客户编号
     */
    @JSONField(name = "InvestorCode")
    private String InvestorCode;
    /**
     * 错误信息
     */
    @JSONField(name = "ErrorMsg")
    private String ErrorMsg;






}
