package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/16 16:45
 */
@Data
public class UpdateAccInDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口


    private String actual_busin;//实际经营业务
    private String address;//联系地址
    private String aml_risk_level;//反洗钱风险等级
    private String apply_time;//申请时间
    private String auto_buy;//分红方式
    private String bad_integrity;//不良诚信记录
    private String bad_integrity_desc;//不良诚信说明
    private String belong_organ;//所属机构
    private String beneficiary;//账户实际受益人
    private String broker_no;//理财经理编号
    private String broker_trust;//经纪人委托
    private String busin_ass_code;//业务辅助代码
    private String business_range;//经营的范围
    private String check_send_way;//账单寄送方式
    private String checkid_validate;//年检有效期
    private String chinapay_serial_no;//银商流水号
    private String city_no;//城市代码
    private String client_full_name;//客户名称全称


    @SerializedName(value = "bank_account", alternate = "accountNo")
    @JSONField(name = "bank_account")
    private String bank_account;//银行账号
    @SerializedName(value = "bank_account_name", alternate = "accountAgency")
    @JSONField(name = "bank_account_name")
    private String bank_account_name;//银行户名
    private String bank_name;//银行名称
    @SerializedName(value = "check_id_validate", alternate = "certificateDate")
    @JSONField(name = "check_id_validate")
    private String check_id_validate;//证件年检有效期


    @SerializedName(value = "bank_no", alternate = "bankCode")
    @JSONField(name = "bank_no")
    private String bank_no;//银行代码
    @SerializedName(value = "client_name", alternate = "accountName")
    @JSONField(name = "client_name")
    private String client_name;//客户姓名
    private String company_name;//公司名称
    private String con_vocation;//经办人职业
    private String contact_address;//经办人地址
    private String contact_birthday;//经办人生日
    private String contact_email;//经办人电子邮件
    private String contact_explain;//经办人备注
    private String contact_faxno;//授权经办人传真
    @SerializedName(value = "contact_id_enddate", alternate = "responsibleCerValidDate")
    @JSONField(name = "contact_id_enddate")
    private String contact_id_enddate;//经办人证件有效截至日
    @SerializedName(value = "contact_id_no", alternate = "responsibleCerNo")
    @JSONField(name = "contact_id_no")
    private String contact_id_no;//经办人证件号码
    private String contact_identification_mode;//经办人识别方式
    private String contact_mobile_tel;//经办人手机号码
    @SerializedName(value = "contact_name", alternate = "responsibleName")
    @JSONField(name = "contact_name")
    private String contact_name;//经办人姓名
    private String contact_office_address;//经办人办公地址
    private String contact_office_zipcode;//经办人办公邮编
    private String contact_relation;//经办人申请人关系
    private String contact_relationship;//经办人与该机构关系
    private String contact_right;//经办人权限
    private String contact_sex;//授权经办人性别
    private String contact_tel;//经办人电话
    private String contact_zipcode;//经办人邮编
    @SerializedName(value = "contract_id_kind_gb", alternate = "responsibleCerType")
    @JSONField(name = "contract_id_kind_gb")
    private String contract_id_kind_gb;//经办人证件类型
    private String contwarrant_validate;//授权书有效期
    private String cor_propertiy;//企业性质
    private String country_no;//县区代码
    private String cust_level;//客户等级
    private String e_mail;//电子信箱
    private String edit_regist_capital;//注册资本(可编辑)
    private String effect_begin_date;//合格投资人生效日期
    private String effect_end_date;//合格投资人截止日期
    private String emp_name;//理财师姓名
    private String emp_no;//理财师工号
    private String employee_num;//员工人数
    private String ext_company;//外部集团编号
    private String fax;//传真号码
    private String fax_trust;//允许传真委托
    private String full_custom_name;//客户名称的全称
    private String fund_nationality;//国籍(基金直销)
    private String has_usa_nationality;//是否具有美国国籍
    private String holding_name;//账户实际控制人
    private String holdingid_no;//(直)控股股东证件号码
    private String holdingid_type;//(直)控股股东证件类型
    private String holdingid_validate;//控股股东证件有效期
    private String home_tel;//家庭电话
    private String id_enddate;//证件有效截止日期
    @SerializedName(value = "id_kind_gb", alternate = "certificateType")
    @JSONField(name = "id_kind_gb")
    private String id_kind_gb;//证件类别
    @SerializedName(value = "id_no", alternate = "certificateNo")
    @JSONField(name = "id_no")
    private String id_no;//证件号码
    private String income_source;//资金来源
    private String instrepr_birthday;//法人生日
    private String instrepr_duty;//法人职务
    private String instrepr_id_enddate;//法人代表证件有效截至日
    private String instrepr_id_kind_gb;//法人证件类型
    private String instrepr_id_no;//法人证件号码
    private String instrepr_name;//法人代表姓名
    private String instrepr_office_address;//法人办公地址
    private String instrepr_office_zipcode;//法人办公邮编
    private String instrepr_sex;//法人性别
    private String invest;//证券投资经历
    private String invest_highest;//单笔投资规模
    private String invest_years;//投资年限
    private String is_annuity_flag;//企业年金账户标识
    private String link_man;//(直)联系人名称
    private String location_zipcode;//经常居住地邮编
    private String long_bill_path;//长对账单寄送途径
    @SerializedName(value = "mobile_tel", alternate = "telNo")
    @JSONField(name = "mobile_tel")
    private String mobile_tel;//手机号码
    private String net_check_flag;//网上下单是否需要审核
    private String office_tel;//单位电话
    private String order_date;//下单日期
    private String org_type;//机构类型
    private String organ_code;//组织机构代码
    private String organ_valid_date;//组织代码证有效期
    private String orgholding_name;//控股股东名称
    private String orgholding_organ_code;//控股股东组织代码证号码
    private String orgholding_tax_register;//控股股东税务登记证号码
    private String orgholding_type;//控股股东类别
    private String password = JGTConstant.ZX_PWD;//密码(默认密码)
    private String pre_acco_size;//预期账户规模
    private String principal_name;//(直)负责人姓名
    private String principal_no;//负责人证件号码
    private String principal_type;//负责人证件类型
    private String principal_validate;//负责人证件有效期
    private String profession_flag;//专业资质投资者
    private String province_no;//省份代码
    private String pub_qualified_investor_flag;//公募合格投资人标识
    private String qualification_no;//资格证书号码
    private String qualification_type;//资格证书类型
    private String qualification_validate;//资格证书有效期
    private String qualified_investor;//合格投资人标识
    private String recipients_name;//账单收件人姓名
    private String recipients_no;//账单收件人证件号码
    private String recipients_type;//账单收件人证件类型
    private String recipients_validate;//账单收件人证件有效期
    private String recommender  ;//推荐人
    private String recommender_city_no  ;//推荐人城市代码
    private String recommender_office  ;//推荐人所在单位部门
    private String recommender_phone  ;//推荐人手机号码
    private String recommender_province  ;//推荐人省份代码
    private String recommender_type  ;//推荐人类型
    private String reg_address  ;//(直)注册地址
    private String reg_capital  ;//注册资本
    private String seller  ;//业务员
    private String shstock_account  ;//上海主股东账号
    private String sms_flag  ;//短信发送标志
    private String special_info  ;//特殊信息
    private String statement_flag  ;//对账单寄送选择
    private String szstock_account  ;//深圳主股东账号
    @SerializedName(value = "ta_acco", alternate = "tACode")
    @JSONField(name = "ta_acco")
    private String ta_acco  ;//TA账号/基金账号
    private String tax_organ  ;//税务登记证发证机构
    private String tax_register  ;//国税税务登记号码
    private String trade  ;//行业
    @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco  ;//交易账号
    private String trade_acco_name  ;//交易账号名称
    private String trade_conduct_person  ;//交易经办人
    private String trade_source  ;//交易来源
    private String truename_inbank_flag  ;//银行卡实名状态
    private String trust_address  ;//委托人地址
    private String trust_beneficiary_name  ;//信托受益人的姓名
    private String trust_beneficiary_phone  ;//信托受益人电话
    private String trust_contact  ;//委托经办人
    private String trust_faxno  ;//委托人传真
    private String trust_name  ;//委托人姓名
    private String trust_no  ;//委托人证件号码
    private String trust_phone  ;//委托人联系电话
    private String trust_type  ;//委托人证件类型
    private String warranty_validate  ;//授权委托书有效期
    private String zipcode  ;//邮政编码
    private String cust_fund_time_limit  ;//客户投资期限
    private String investmentvarieties  ;//投资品种
    private String net_audit_flag  ;//网上复核标志
    private String pre_yield_flag  ;//期望收益
    private String product_id  ;//产品ID
    private String annuity_flag  ;//是否年金产品
    private String assist_idno  ;//辅助身份证明文件号码
    private String assist_idtype  ;//辅助身份证明文件类型
    private String assist_validate  ;//辅助身份证明文件有效日期
    private String associationt_code  ;//产品ID
    private String birthday  ;//出生日期
    private String client_short_name  ;//客户简称
    private String discount_rate  ;//折扣比率
    private String fund_setup_date  ;//产品成立日期
    private String holdid_email  ;//实际控制人邮箱
    private String holdid_phone  ;//实际控制人联系电话
    private String holding_email  ;//控股股东邮箱
    private String holding_phone  ;//控股股东电话号码
    private String industry_detail  ;//行业明细
    private String info_json  ;//控制人/受益人等JSON信息
    private String internalacco_flag  ;//是否内部账户
    private String manager_id  ;//投管人代码
    private String money_type  ;//注册币种
    private String multi_investmentvarieties  ;//多投资品种
    private String nonper_cust_type  ;//非自然人类型
    private String other_manager  ;//其他客户经理
    private String portfolio_code  ;//投资组合代码
    private String principal_email  ;//负责人邮箱
    private String principal_phone  ;//负责人联系电话
    private String product_category  ;//产品类别额
    private String product_code  ;//产品代码
    private String product_duration  ;//产品续存期
    private String product_name  ;//产品名称
    private String product_record_date  ;//产品背案日期
    private String product_record_no  ;//产品背案编号
    private String product_record_orgdesc  ;//产品背案机构说明
    private String product_record_orgflag  ;//产品背案机构
    private String product_sizeflag  ;//产品规模
    private String product_type  ;//产品类型
    private String reg_nation  ;//注册国家
    private String send_aml_flag  ;//反洗钱信息上报标识
    private String trustee_id  ;//托管人的编号
    private String trustee_name    ;//托管人名称
    private String work_nation  ;//办公国家
}
