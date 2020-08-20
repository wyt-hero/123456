package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 16:44
 */
@Data
public class OrgnInZXDTO {
    private String Version;//接口版本号
    private String ProcessCode;//功能代码
    private String InstitutionMarking;//机构标识
    private String Productcode;//产品代码
    private String O3Productcode;//O3 产品代码
    private String OSerialno;//O3 流水号

    private String Password;//密码

    private String TransactionAccountID;//投资人基金交易帐号
    private String Busintype;//业务类型
    private String ProducName;//产品名称
    private String ProducShortName;//产品简称
    private String InvestorName;//投资人户名
    private String InvestorAbbreviation;//投资人简称
    private String IndividualOrInstitution;//个人/机构标识
    private String AgentProductionNO;//代销登记产品编号
    private String CertificateType;//证件类型
    private String CertificateNo;//证件号码
    private String CertificateDate;//证件有效期
    private String AccountCardID;//基金账户卡的凭证号
    private String InvestProductType;//机构类型
    private String TransactorName;//经办人姓名
    private String TransactorCertNo;//经办人证件号码
    private String TransactorCertType;//经办人证件类型
    private String InstTranCertValidDate;//经办人证件有效期
    private String SHSecuritiesAccountID;//上交所证券账号
    private String SZSecuritiesAccountID;//深交所证券账号
    private String EditAccountFlag;//是否同步修改账户
    private String AccountName;//银行账户户名
    private String BankCode;//银行代码
    private String AccountProvince;//账户所在省份
    private String AccountCity;//账户所在城市
    private String AccountAgency;//开户行
    private String AccountNo;//银行账号
    private String LargePayNum;//大额支付号
    private String InstReprManageRange;//机构法人经营范围
    private String InstReprName;//法人代表姓名
    private String InstReprIDType;//法人代表证件类型
    private String InstReprIDCode;//法人代表证件代码
    private String InstReprCertValidDate;//机构法人证件有效日期
    private String TACode;//TA 代码
    private String TAAccountID;//基金账户
    private String Address;//法人联系地址
    private String TelNo;//法人联系电话
    private String AccountFile;//账户电子文件
    private String FileNumber;//文件编号
    private String RegAccountName;//监管子账户名称
    private String CerExpiryDate;//证件年检有效期
    private String UnionPayCDNo;//银联 CD 卡号
    private String OperatorTel;//经办人联系电话
    private String OperatorAuthorExpiryDate;//经办人授权书有效期
    private String CorporateSex;//法人性别
    private String CorporateDuty;//法人职务
    private String CorporateOfficeMail;//法人办公邮箱
    private String CorporateMobile;//法人手机号码
    private String CorporateMail;//法人电子邮件
    private String Nature;//企业性质
    private String RegisteredAddress;//注册地址
    private String EmployeesNumber;//员工人数
    private String RegisteredCapital;//注册资本(万元)
    private String Country;//国家
    private String Province;//省份
    private String City;//城市
    private String PostalCode;//邮政编码
    private String PostalAddress;//通讯地址
    private String Tel;//联系电话
    private String Mobile;//手机号码
    private String Fax;//传真号码
    private String Mail;//电子邮件
    private String BillSendMode;//对账单寄送方式
    private String BillSendWay;//对账单寄送途径
    private String BillSendCity;//对账单寄送城市
    private String OrgNo;//组织机构代码
    private String TaxNo;//税务登记证号码
    private String RiskType;//反洗钱风险类型
    private String OrgNoValidDate;//组织代码证有效期
    private String QualificationType;//资格证书类型
    private String QualificationNo;//资格证书号码
    private String QualificationValidDate;//资格证书有效期
    private String BillRecipientType;//账单收件人证件类型
    private String BillRecipientNo;//账单收件人证件号码
    private String BillRecipientValidDate;//账单收件人证件有效期
    private String ResponsibleCerType;//负责人证件类型
    private String ResponsibleCerNo;//负责人证件号码
    private String ResponsibleCerValidDate;//负责人证件有效期
    private String AccountController;//账户实际控制人
    private String ShareholderCerType;//控股股东证件类型
    private String ShareholderCerNo;//控股股东证件号码
    private String ShareholderCerValidDate;//控股股东证件有效期
    private String ShareholderOrgNo;//控股股东组织代码
    private String ShareholderTaxNo;//控股股东税务登记证号码
    private String BillRecipientName;//账单收件人姓名
    private String ResponsibleName;//负责人姓名
    private String AMLRemarks;//反洗钱备注
    private String AccountBeneficiary;//账户实际受益人
    private String TrustAssetsFlg;//是否信托资产
    private String ClientName;//委托人名称
    private String ClientTel;//委托人联系电话
    private String ShareholderType;//控股股东类别
    private String ClientCerType;//委托人证件类型
    private String ClientCerNo;//委托人证件号码
    private String Operator;//委托经办人
    private String OperatorAddr;//委托人地址
    private String OperatorFax;//委托人传真
    private String InfoSendType;//信息发送方式
    private String IncomeSource;//收入来源
    private String CustomerTradeType;//客户交易性质
    private String ExpectedAccountSize;//预期账户规模
    private String ActualBusiness;//实际经营业务
    private String FundSource;//资金来源
    private String SuitablyInvestorsFlg;//适当性专业投资者标识
    private String SuitablyInvestorsType;//适当性专业投资者类别
    private String AccessQualificationFlg;//适当性准入资格标识
    private String SuitablyInvestorsValidDate;//适当性专业投资者有效期
    private String BadCreditRecor;//不良诚信记录
    private String BadCreditExplain;//不良诚信说明
    private String InvestmentVariety;//投资品种
    private String InvestmentTerm;//客户投资期限
    private String CRSInfo;//CRS 信息
    private String BeneficiaryInfo;//受益人信息
    private String ShareholderInfo;//股东信息
    private String SendTime;//发送时间
    private String SingText;//签名

    //todo 测试
    private String QuaInvestorFlg;//合格投资人标志(未找到对应字段)
    private String QuaInvestorStartDay;//合格投资人认定日期
    private String QuaInvestorEndDay;//合格投资人截止日期
    private String ProfeInvestorFlag;//专业资质投资者标志
    private String  ShareholderName;//控股股东名称
}
