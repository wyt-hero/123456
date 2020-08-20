package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author wyt
 * @date 2020-07-24-15-27-14
 */
@Data
public class CRSZXInfoDTO {
    private String client_id;//客户编号
    @SerializedName(value = "current_addr", alternate = "")
    @JSONField(name = "current_addr")
    private String current_addr;//英文当前办公地址/现居地址
    @SerializedName(value = "current_work_addr", alternate = "CRSCurOfficeAddr")
    @JSONField(name = "current_work_addr")
    private String current_work_addr;//当前办公地址
    @SerializedName(value = "current_work_city", alternate = "CRSCurOfficeCity")
    @JSONField(name = "current_work_city")
    private String current_work_city;//当前办公城市/现居地址
    @SerializedName(value = "current_work_nation", alternate = "CRSCurOfficeCountry")
    @JSONField(name = "current_work_nation")
    private String current_work_nation;//当前办公国家（地区）
    @SerializedName(value = "cust_flag", alternate = "CRSCustomerType")
    @JSONField(name = "cust_flag")
    private String cust_flag;//税收居民身份
    @SerializedName(value = "eng_current_addr", alternate = "CRSPYCurAddress")
    @JSONField(name = "eng_current_addr")
    private String eng_current_addr;//现居地址（英文或拼音）
    @SerializedName(value = "eng_current_work_nation", alternate = "CRSEnCurOfficeCountry")
    @JSONField(name = "eng_current_work_nation")
    private String eng_current_work_nation;//英文当前办公国家（地区）/英文现居地址(国家)
    @SerializedName(value = "eng_mail_addr", alternate = "CRSEnContactAddr")
    @JSONField(name = "eng_mail_addr")
    private String eng_mail_addr;//英文联系地址/英文机构地址
    @SerializedName(value = "eng_mail_city", alternate = "CRSEnContactCity")
    @JSONField(name = "eng_mail_city")
    private String eng_mail_city;//英文联系城市/英文机构地址(城市)
    @SerializedName(value = "eng_mail_nation", alternate = "CRSEnContactCountry")
    @JSONField(name = "eng_mail_nation")
    private String eng_mail_nation;//英文联系国家(地区)/英文机构地址(国家)
    @SerializedName(value = "eng_name", alternate = "CRSEnName")
    @JSONField(name = "eng_name")
    private String eng_name;//姓（英文或拼音）
    @SerializedName(value = "eng_reg_addr", alternate = "CRSEnRegisterAddr")
    @JSONField(name = "eng_reg_addr")
    private String eng_reg_addr;//英文注册地址
    @SerializedName(value = "eng_reg_city", alternate = "CRSEnRegisterCity")
    @JSONField(name = "eng_reg_city")
    private String eng_reg_city;//英文注册地址(城市)
    @SerializedName(value = "eng_reg_nation", alternate = "CRSEnRegisterCountry")
    @JSONField(name = "eng_reg_nation")
    private String eng_reg_nation;//英文注册国家/地区
    @SerializedName(value = "is_need_controller", alternate = "CRSExistNonReside")
    @JSONField(name = "is_need_controller")
    private String is_need_controller;//是否存在非居民控制人
    private String json_id;//JSON对象自身编号
    @SerializedName(value = "last_date", alternate = "CRSLastUpdateDate")
    @JSONField(name = "last_date")
    private String last_date;//最后更新日期
    @SerializedName(value = "mail_addr", alternate = "CRSMailingAddr")
    @JSONField(name = "mail_addr")
    private String mail_addr;//邮寄地址
    @SerializedName(value = "mail_city", alternate = "CRSContactCity")
    @JSONField(name = "mail_city")
    private String mail_city;//联系（邮寄）城市/机构地址(城市)
    @SerializedName(value = "mail_nation", alternate = "CRSMailingAddrCountry")
    @JSONField(name = "mail_nation")
    private String mail_nation;//邮寄地址所在国家（地区）
    @SerializedName(value = "ori_cust_type", alternate = "CRSOrgType")
    @JSONField(name = "ori_cust_type")
    private String ori_cust_type;//机构类型
    @SerializedName(value = "reg_addr", alternate = "CRSRegisterAddr")
    @JSONField(name = "reg_addr")
    private String reg_addr;//注册地址
    @SerializedName(value = "reg_city", alternate = "CRSRegisterCity")
    @JSONField(name = "reg_city")
    private String reg_city;//注册地址(城市)
    @SerializedName(value = "reg_nation", alternate = "CRSRegisteredCountry")
    @JSONField(name = "reg_nation")
    private String reg_nation;//注册国家/地区
    @SerializedName(value = "taxinfo", alternate = "CRSTaxpayerInfoList")
    @JSONField(name = "taxinfo")
    private List<ZXTaxinfoDTO> taxinfo;//纳税人信息列表
    @SerializedName(value = "controllerinfo", alternate = "CRSControllerInfoList")
    @JSONField(name = "controllerinfo")
    private List<ZXControllerInfoDTO> controllerinfo;//控制人信息列表


    //todo 直销没有该字段，是jgt页面试出来的
    @SerializedName(value = "mail_city_no", alternate = "CRSMailCityNo")
    @JSONField(name = "mail_city_no")
    private String mail_city_no;//联系（邮寄）城市编号

    @SerializedName(value = "mail_provice_code", alternate = "CRSMailProvinceNo")
    @JSONField(name = "mail_provice_code")
    private String  mail_provice_code;//联系（邮寄）省份编号
    @SerializedName(value = "current_work_city_no", alternate = "CRSCurOfficeCityNo")
    @JSONField(name = "current_work_city_no")
    private String current_work_city_no;//当前办公/居住城市编号

    private String code;
    private String message;
}
