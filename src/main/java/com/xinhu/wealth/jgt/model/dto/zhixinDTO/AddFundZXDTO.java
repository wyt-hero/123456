package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 18:23
 */
@Data
public class AddFundZXDTO {

    private String bank_account ;//银行账号
    private String bank_account_name   ;//银行户名
    private String bank_name   ;//银行名称

    private String bank_no   ;//银行代码

    private String capital_mode   ;//资金方式
    private String check_id_validate   ;//证件年检有效期
    private String client_name   ;//客户姓名
    private String contact_id_enddate   ;//经办人证件有效截至日
    private String contact_id_no   ;//经办人证件号码
    private String contact_name   ;//经办人姓名
    private String contract_id_kind_gb   ;//经办人证件类型
    private String id_kind_gb   ;//证件类别
    private String id_no   ;//证件号码
    private String mobile_tel   ;//手机号码
    private String ta_acco   ;//TA账号
    private String ta_no   ;//TA编号
}
