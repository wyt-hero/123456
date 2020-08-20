package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

import java.util.List;

/**
 * @author wyt
 * @date 2020-07-24-14-48-25
 */
@Data
public class CRSInfoDTO {
    //CRSInfo;//CRS 信息
    private String CRSAccountType;//CRS 账户类型
    private String CRSOrgType;//机构类型
    private String CRSNonResideFlg;//机构居民/非居民标志
    private String CRSExistNonReside;//是否存在非居民控制人
    private String CRSRegisteredCountry;//注册国家/地区
    private String CRSCurOfficeAddr;//当前办公地址
    private String CRSCurOfficeCountry;//当前办公国家（地区）
    private String CRSMailingAddr;//邮寄地址
    private String CRSMailingAddrCountry;//邮寄地址所在国家地区）
    private String CRSCustomerType;//FATCA、CRS 客户分类(税收居民身份)
    private String CRSBirthCountry;//出生国家/地区
    private String CRSCurAddress;//现居地址
    private String CRSPYName;//姓名（英文或拼音）
    private String CRSPYBirthPlace;//出生地英文或拼音）
    private String CRSPYCurAddress;//现居地址（英文或拼音）
    private String CRSTaxInfoMainId;//纳税信息主ID
    private String CRSMainTaxInfoFlg;//是否主纳税人信息
    private String CRSContactCity;//联系（邮寄）城市
    private String CRSEnContactCountry;//英文联系国家(地 区)
    private String CRSEnContactCity;//英文联系城市
    private String CRSEnContactAddr;//英文联系地址
    private String CRSCurOfficeCity;//当前办公/居住城市
    private String CRSEnCurOfficeCountry;//英文当前办公/居住国家（地区）
    private String CRSEnName;//英文名
    private String CRSBirthCity;//出生城市
    private String CRSEnBirthCountry;//英文出生国家/地区
    private String CRSDocumentSigner;//声明文件签名人身份
    private String CRSLastUpdateDate;//最后更新日期
    private String CRSRegisterCity;//注册地址(城市)
    private String CRSRegisterAddr;//注册地址
    private String CRSEnRegisterCountry;//英文注册国家/地区
    private String CRSEnRegisterCity;//英文注册地址(城市)
    private String CRSEnRegisterAddr;//英文注册地址
    private String CRSMailCityNo;//联系（邮寄）城市编号
    private String CRSMailProvinceNo;//联系（邮寄）省份编号
    private String CRSCurOfficeCityNo;//当前办公/居住城市编号
    private String CRSCurOfficeProvinceNo;//当前办公/居住省份编号
    private String CRSRegisterCityNo;//注册地城市编号
    private String CRSProvinceNo;//注册地省份编号
    private String CRSAccountAdminId;//CRS账户管理人ID
    private String CRSOrgName;//CRS机构名称
    private String CRSCerType;//CRS证件类型
    private String CRSCerNo;//CRS证件号码
    private String CRSZipCode;//CRS邮政编码
    private String CRSFirstSendDate;//首次发送日期
    private String CRSCDDDate;//尽职调查日期
    private String CRSObtainStatementFlg;//是否取得声明
    private String CRSReportAddrType;//上报地址类型
    private List<CRSTaxpayerInfoDTO> CRSTaxpayerInfoList;//纳税人信息列表
    private List<CRSControllerInfoDTO> CRSControllerInfoList;//控制人信息列表
}
