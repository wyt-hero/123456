package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 16:01
 * 机构开户(C503)
 */
@Data
public class OrgnInDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    @SerializedName(value = "usertype", alternate = "individualOrInstitution")
    @JSONField(name = "usertype")
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口


    //必填 begin（植信）
    @SerializedName(value = "bank_account", alternate = "accountNo")
    @JSONField(name = "bank_account")
    private String bank_account;//银行账号
    @SerializedName(value = "bank_account_name", alternate = "accountName")
    @JSONField(name = "bank_account_name")
    private String bank_account_name;//银行户名
    @SerializedName(value = "bank_name", alternate = "accountAgency")
    @JSONField(name = "bank_name")
    private String bank_name;//银行名称
    @SerializedName(value = "bank_no", alternate = "bankCode")
    @JSONField(name = "bank_no")
    private String bank_no;//银行代码
    private String capital_mode = "1";//资金方式
    @SerializedName(value = "check_id_validate", alternate = "cerExpiryDate")
    @JSONField(name = "check_id_validate")
    private String check_id_validate;//证件年检有效期
    @SerializedName(value = "client_name", alternate = "investorName")
    @JSONField(name = "client_name")
    private String client_name;//客户姓名
    @SerializedName(value = "contact_id_enddate", alternate = "instTranCertValidDate")
    @JSONField(name = "contact_id_enddate")
    private String contact_id_enddate;//经办人证件有效截至日
    @SerializedName(value = "contact_id_no", alternate = "transactorCertNo")
    @JSONField(name = "contact_id_no")
    private String contact_id_no;//经办人证件号码
    @SerializedName(value = "contact_name", alternate = "transactorName")
    @JSONField(name = "contact_name")
    private String contact_name;//经办人姓名

    @SerializedName(value = "contract_id_kind_gb", alternate = "transactorCertType")
    @JSONField(name = "contract_id_kind_gb")
    private String contract_id_kind_gb;//经办人证件类型

    @SerializedName(value = "id_kind_gb", alternate = "certificateType")
    @JSONField(name = "id_kind_gb")
    private String id_kind_gb;//证件类别
    @SerializedName(value = "id_no", alternate = "certificateNo")
    @JSONField(name = "id_no")
    private String id_no;//证件号码
    @SerializedName(value = "mobile_tel", alternate = "mobile")
    @JSONField(name = "mobile_tel")
    private String mobile_tel;//手机号码
    //必填 end（植信）
    @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco;// 交易账号
    @SerializedName(value = "ta_acco", alternate = "tAAccountID")
    @JSONField(name = "ta_acco")
    private String ta_acco;// TA账号
    private String password ;
    private String acco_flag;//账户类型
    private String acco_name;//交易账号名称
    @SerializedName(value = "lianAddress", alternate = "postalAddress")
    private String lianAddress;//联系地址
    @SerializedName(value = "aml_risk_level", alternate = "riskType")
    @JSONField(name = "aml_risk_level")
    private String aml_risk_level;//反洗钱风险等级
    private String bad_integrity;//不良诚信记录
    private String bad_integrity_desc;//不良诚信说明
    private String bank_account_name_long;//长银行户名

    @SerializedName(value = "beneficiary", alternate = "accountBeneficiary")
    @JSONField(name = "beneficiary")
    private String beneficiary;//账户实际受益人
    private String birth_address;//出生地
    private String birthday;//出生日期

    @SerializedName(value = "branch_bank_no", alternate = "largePayNum")
    @JSONField(name = "branch_bank_no")
    private String branch_bank_no;//联行号
    private String broker;//经纪人

    @SerializedName(value = "business_scope", alternate = "instReprManageRange")
    @JSONField(name = "business_scope")
    private String business_scope;//经营范围
    @SerializedName(value = "check_send_way", alternate = "billSendMode")
    @JSONField(name = "check_send_way")
    private String check_send_way;//账单寄送方式
    private String chinapay_serial_no;//银商流水号
    private String client_full_name;//客户名称全称
    private String client_gender;//客户性别
    private String client_id;//客户编号
    private String communication_addr;//子账户联系地址
    private String company_name;//公司名称
    private String con_vocation;//经办人职业
    private String contact_address;//经办人地址
    private String contact_birthday;//经办人生日
    private String contact_email;//经办人电子邮件
    private String contact_faxno;//授权经办人传真
    private String contact_mobile_tel;//授权经办人手机
    private String contact_office_address;//经办人办公地址
    private String contact_office_zipcode;//经办人办公邮编
    private String contact_relationship;//经办人与该机构关系
    private String contact_sex;//授权经办人性别
    @SerializedName(value = "contact_tel", alternate = "operatorTel")
    @JSONField(name = "contact_tel")
    private String contact_tel;//经办人电话

    //    @SerializedName(value = "contact_zipcode", alternate = "PostalCode")
//    @JSONField(name = "contact_zipcode")
    private String contact_zipcode;//经办人邮编
    @SerializedName(value = "cor_propertiy", alternate = "nature")
    @JSONField(name = "cor_propertiy")
    private String cor_propertiy;//企业性质
    private String discount_rate;//费率折扣
    private String e_mail;//电子信箱

    @SerializedName(value = "edit_regist_capital", alternate = "registeredCapital")
    @JSONField(name = "edit_regist_capital")
    private String edit_regist_capital;//注册资本(可编辑)
    private String education;//学历
    @SerializedName(value = "effect_end_date", alternate = "quaInvestorEndDay")
    @JSONField(name = "effect_end_date")
    private String effect_end_date;//合格投资人截止日期
    @SerializedName(value = "effect_start_date", alternate = "quaInvestorStartDay")
    @JSONField(name = "effect_start_date")
    private String effect_start_date;//合格投资人生效日期
    private String fax;//传真号码
    private String fax_trust;//允许传真委托

    private String fund_acco;//基金账号
    private String fund_card;//资金卡号
    private String fund_nationality;//国籍(基金直销)
    @SerializedName(value = "home_tel", alternate = "tel")
    @JSONField(name = "home_tel")
    private String home_tel;//家庭电话
    //赋值 todo   ,,,,,,,,,,,,,,,,,,,,,,
    @SerializedName(value = "id_enddate", alternate = "certificateDate")
    @JSONField(name = "id_enddate")
    private String id_enddate;//证件有效截止日期
    private String instrepr_birthday;//法人生日
    private String instrepr_duty;//法人职务
    @SerializedName(value = "instrepr_id_enddate", alternate = "instReprCertValidDate")
    @JSONField(name = "instrepr_id_enddate")
    private String instrepr_id_enddate;//法人代表证件有效截至日
    @SerializedName(value = "instrepr_id_kind_gb", alternate = "instReprIDType")
    @JSONField(name = "instrepr_id_kind_gb")
    private String instrepr_id_kind_gb;//法人证件类型

    @SerializedName(value = "instrepr_id_no", alternate = "instReprIDCode")
    @JSONField(name = "instrepr_id_no")
    private String instrepr_id_no;//法人证件号码
    @SerializedName(value = "instrepr_name", alternate = "instReprName")
    @JSONField(name = "instrepr_name")
    private String instrepr_name;//法人代表姓名
        @SerializedName(value = "instrepr_office_address", alternate = "address")
    @JSONField(name = "instrepr_office_address")
    private String instrepr_office_address;//法人办公地址
    private String instrepr_office_zipcode;//法人办公邮编
    private String instrepr_sex;//法人性别
    private String invest;//证券投资经历
    private String invest_risk_tolerance;//投资人风险承受能力
    @SerializedName(value = "leader_id_enddate", alternate = "responsibleCerValidDate")
    @JSONField(name = "leader_id_enddate")
    private String leader_id_enddate;//负责人证件有效期
    @SerializedName(value = "leader_id_no", alternate = "responsibleCerNo")
    @JSONField(name = "leader_id_no")
    private String leader_id_no;//负责人证件号码
    @SerializedName(value = "leader_id_type_gb", alternate = "responsibleCerType")
    @JSONField(name = "leader_id_type_gb")
    private String leader_id_type_gb;//负责人证件类型
    private String leader_mobile_tel;//负责人电话
    @SerializedName(value = "leader_name", alternate = "responsibleName")
    @JSONField(name = "leader_name")
    private String leader_name;//负责人姓名
    private String marriage_status;//婚姻状况
    private String mobile_trust;//是否支持手机委托
    private String net_audit_flag;//网上复核标志
    private String net_check_flag;//网上下单是否需要审核
    private String net_trust;//是否支持网上委托
    private String office_tel;//单位电话
    private String ofund_prof_code;//职业
    private String ofund_user_type;//用户类型
    @SerializedName(value = "open_city_no", alternate = "accountCity")
    @JSONField(name = "open_city_no")
    private String open_city_no;//开户行所在城市
    private String open_province_code;//开户行所在省份
    @SerializedName(value = "org_type", alternate = "investProductType")
    @JSONField(name = "org_type")
    private String org_type;//机构类型
    @SerializedName(value = "organ_code", alternate = "orgNo")
    @JSONField(name = "organ_code")
    private String organ_code;//组织机构代码
    @SerializedName(value = "organ_valid_date", alternate = "orgNoValidDate")
    @JSONField(name = "organ_valid_date")
    private String organ_valid_date;//组织代码证有效期
    @SerializedName(value = "orgholding_id_enddate", alternate = "shareholderCerValidDate")
    @JSONField(name = "orgholding_id_enddate")
    private String orgholding_id_enddate;//控股股东证件有效期
    @SerializedName(value = "orgholding_id_no", alternate = "shareholderCerNo")
    @JSONField(name = "orgholding_id_no")
    private String orgholding_id_no;//控股股东证件号码
    @SerializedName(value = "orgholding_id_type_gb", alternate = "shareholderCerType")
    @JSONField(name = "orgholding_id_type_gb")
    private String orgholding_id_type_gb;//控股股东证件类型
    @SerializedName(value = "orgholding_name", alternate = "accountController")
    @JSONField(name = "orgholding_name")
    private String orgholding_name;//账户实际控制人
    @SerializedName(value = "orgholding_organ_code", alternate = "shareholderOrgNo")
    @JSONField(name = "orgholding_organ_code")
    private String orgholding_organ_code;//控股股东组织代码证号码

    @SerializedName(value = "orgholding_tax_register", alternate = "shareholderTaxNo")
    @JSONField(name = "orgholding_tax_register")
    private String orgholding_tax_register;//控股股东税务登记证号码

    @SerializedName(value = "orgholding_type", alternate = "shareholderType")
    @JSONField(name = "orgholding_type")
    private String orgholding_type;//控股股东类别
    private String other_user_id;//对方平台ID
    private String pre_user_flag;//预用户标志
    @SerializedName(value = "product_id", alternate = "agentProductionNO")
    @JSONField(name = "product_id")
    private String product_id;//产品ID
    @SerializedName(value = "profession_flag", alternate = "profeInvestorFlag")
    @JSONField(name = "profession_flag")
    private String profession_flag;//专业资质投资者

    @SerializedName(value = "pub_professional_investor_class", alternate = "suitablyInvestorsType")
    @JSONField(name = "pub_professional_investor_class")
    private String pub_professional_investor_class;//公募专业投资人细化分类

    @SerializedName(value = "pub_professional_investor_flag", alternate = "suitablyInvestorsFlg")
    @JSONField(name = "pub_professional_investor_flag")
    private String pub_professional_investor_flag;//公募专业投资人标志

    @SerializedName(value = "pub_professional_investor_valid_date", alternate = "suitablyInvestorsValidDate")
    @JSONField(name = "pub_professional_investor_valid_date")
    private String pub_professional_investor_valid_date;//公募专业投资人有效期

    @SerializedName(value = "pub_qualified_investor_flag", alternate = "quaInvestorFlg")
    @JSONField(name = "pub_qualified_investor_flag")
    private String pub_qualified_investor_flag;//公募合格投资人标识
    @SerializedName(value = "qualification_no", alternate = "qualificationNo")
    @JSONField(name = "qualification_no")
    private String qualification_no;//资格证书号码
    @SerializedName(value = "qualification_type", alternate = "qualificationType")
    @JSONField(name = "qualification_type")
    private String qualification_type;//资格证书类型
    @SerializedName(value = "qualification_validate", alternate = "qualificationValidDate")
    @JSONField(name = "qualification_validate")
    private String qualification_validate;//资格证书有效期
    private String qualified_investor;//合格投资人

    @SerializedName(value = "recipients_name", alternate = "billRecipientName")
    @JSONField(name = "recipients_name")
    private String recipients_name;//账单收件人姓名
    @SerializedName(value = "recipients_no", alternate = "billRecipientNo")
    @JSONField(name = "recipients_no")
    private String recipients_no;//账单收件人证件号码
    @SerializedName(value = "recipients_type", alternate = "billRecipientType")
    @JSONField(name = "recipients_type")
    private String recipients_type;//账单收件人证件类型
    @SerializedName(value = "recipients_validate", alternate = "billRecipientValidDate")
    @JSONField(name = "recipients_validate")
    private String recipients_validate;//账单收件人证件有效期
    private String recommender;//推荐人
    private String recommender_phone;//推荐人手机号码
    //RegisteredAddress
    @SerializedName(value = "reg_addrs", alternate = "registeredAddress")
    @JSONField(name = "reg_addrs")
    private String reg_addrs;//注册地址
    private String reg_capital;//注册资本
    private String remit_flag = "01";//是否开通汇款
    private String special_info;//特殊信息
    @SerializedName(value = "statement_flag", alternate = "billSendWay")
    @JSONField(name = "statement_flag")
    private String statement_flag;//对账单寄送选择
    private String sub_check;//审核权限
    private String sub_email;//子账户邮箱
    private String sub_phone_number;//子账户电话号码
    private String sub_query;//查询权限
    private String sub_telephone;//子账户手机号码
    private String sub_trade;//交易权限
    private String sub_zip;//子账户邮编
    private String subacco_id_kind_gb;//子账户证件类型
    private String subacco_id_no;//子账户证件号
    private String subacco_id_validate;//子账户证件有效期
    private String subacco_name;//子账户名称
    @SerializedName(value = "ta_no", alternate = "tACode")
    @JSONField(name = "ta_no")
    private String ta_no;//TA编号
    private String tax_organ;//税务登记证发证机构
    @SerializedName(value = "tax_register", alternate = "taxNo")
    @JSONField(name = "tax_register")
    private String tax_register;//国税税务登记号码
    private String trade;//行业
    private String trade_conduct_person;//交易经办人
    private String trade_source;//交易来源
    private String trust_benefit_name;//信托收益人姓名
    //ClientCerNo
    @SerializedName(value = "trust_id_no", alternate = "clientCerNo")
    @JSONField(name = "trust_id_no")
    private String trust_id_no;//委托人证件号码
    @SerializedName(value = "trust_id_type", alternate = "clientCerType")
    @JSONField(name = "trust_id_type")
    private String trust_id_type;//委托人证件类型
    @SerializedName(value = "trust_name", alternate = "clientName")
    @JSONField(name = "trust_name")
    private String trust_name;//委托人名称
    @SerializedName(value = "trust_phone", alternate = "clientTel")
    @JSONField(name = "trust_phone")
    private String trust_phone;//委托人联系电话
    @SerializedName(value = "warranty_validate", alternate = "operatorAuthorExpiryDate")
    @JSONField(name = "warranty_validate")
    private String warranty_validate;//授权委托书有效期
    private String year_income;//年收入
    @SerializedName(value = "zipcode", alternate = "postalCode")
    @JSONField(name = "zipcode")
    private String zipcode;//邮政编码
    private String annuities_flag;//是否年金产品
    private String assist_idno;//辅助身份证明文件号码
    private String assist_idtype;//辅助身份证明文件类型
    private String assist_validate;//辅助身份证明文件有效日期
    @SerializedName(value = "client_short_name", alternate = "investorAbbreviation")
    @JSONField(name = "client_short_name")
    private String client_short_name;//客户简称
    private String cust_fund_time_limit;//客户投资期限
    private String cust_kind;//客户分类
    private String first_capital_mode;//首开资金方式
    private String fund_setup_date;//产品成立日期
    private String holdid_email;//实际控制人邮箱
    private String holdid_phone;//实际控制人联系电话
    private String holding_email;//控股股东邮箱
    private String holding_phone;//控股股东电话号码
    private String industry_detail;//行业明细
    private String info_json;//控制人/受益人等JSON信息
    private String internal_acco_flag;//是否内部账户
    private String investmentvarieties;//投资品种
    private String manager_id;//投管人代码
    private String money_type;//注册币种
    private String multi_investmentvarieties;//多投资品种
    private String nonper_cust_type;//非自然人类型
    @SerializedName(value = "orgcontrolling_name", alternate = "shareholderName")
    @JSONField(name = "orgcontrolling_name")
    private String orgcontrolling_name;//控股股东名称
    private String other_acco_manager;//其他客户经理
    private String pre_check_flag;//预检查标识
    private String principal_email;//负责人邮箱
    private String product_category;//产品类别
    private String product_code;//产品代码
    private String product_duration;//产品续存期
    private String product_name;//产品名称
    private String product_record_date;//产品备案日期
    private String product_record_no;//产品备案编号
    private String product_record_orgdesc;//产品机构说明
    private String product_record_orgflag;//产品备案机构
    private String product_sizeflag;//产品规模
    private String product_type;//产品类型
    private String reg_nation;//注册国家

    private String send_aml_flag;//反洗钱信息上报标识
    private String trustee_id;//托管人的编号
    private String trustee_name;//托管人名称
    private String work_nation;//办公国家
}
