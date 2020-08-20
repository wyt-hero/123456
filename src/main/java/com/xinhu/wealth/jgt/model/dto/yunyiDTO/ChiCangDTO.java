package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 18:53
 * 2.4 持仓
 */
@Data
public class ChiCangDTO {
    private String  InstitutionCode;//销售渠道号
    private String   InstitutionMarking;//机构标识
    private String   InvestorCode;//客户号
    private String   TransactionAccountID;//交易账号
    private String   TAAccountID;//基金帐号
    private String   Fundcode;//基金代码
    private String   Fdate;//日期
    private String   PageIndex;//页码编号
    private String   RowSize;//每页数量
    private String   SendTime;//发送时间
    private String   SingText;//签名
}
