package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

import java.util.List;

/**
 * @author wyt
 * @date 2020-07-24-15-06-32
 * 控制人信息列表
 */
@Data
public class CRSControllerInfoDTO {
    private String CIBirthDate;//出生日期
    private String  CIBirthCountry;//出生国家/地区
    private String  CIName;//姓名
    private String  CICurAddr;//现居地址
    private String  CIINSTO;//直接/间接持股比例
    private String  CIPYName;//姓名（英文或拼音）
    private String CIPYBirthPlace;//出生地（英文或拼音）
    private String  CIPYCurAdd;//现居地址（英文或拼音）
    private String  CITaxMainId;//纳税信息主ID
    private String  CIEnName;//英文名
    private String  CIBirthPlace;//出生城市
    private String  CIEnBirthCountry;//英文出生国家/地区
    private String  CITaxResidentStatus;//控制人税收居民身份
    private String  CIApplicable;//控制人适用情况
    private String  CICurCountry;//当前居住国家（地区）
    private String  CICurCity;//当前居住城市
    private String  CIEnCurCountry;//英文当前居住国家（地区）
    private String  CIEnCurCity;//英文当前居住城市
    private String  CICurCityNo;//当前办公/居住城市编号
    private String  CICurProvinceNo;//当前办公/居住省份编号
    private List<CRSTaxpayerInfoDTO> CRSTaxpayerInfoList;//控制人之纳税人信息列表
}
