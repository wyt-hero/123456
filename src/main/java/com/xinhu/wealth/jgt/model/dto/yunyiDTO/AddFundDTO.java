package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 18:18
 */
@Data
public class AddFundDTO {
    private String  Version ;//接口版本号
    private String   ProcessCode ;//功能代码
    private String   InstitutionMarking ;//机构标识
    private String   TransactionAccountID ;//交易账户
    private String   TACode ;//TA 代码
    private String   OSerialno ;//O3 流水号
    private String   SendTime ;//发送时间
    private String   SingText ;//签名
}
