package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author wyt
 * @date 2020-07-24-15-28-40
 */
@Data
public class ZXControllerInfoDTO {
    @SerializedName(value = "birthday", alternate = "CIBirthDate")
    @JSONField(name = "birthday")
    private String birthday;//出生日期
    @SerializedName(value = "born_nation", alternate = "CIBirthCountry")
    @JSONField(name = "born_nation")
    private String born_nation;//出生国家/地区
    @SerializedName(value = "control_type", alternate = "CIApplicable")
    @JSONField(name = "control_type")
    private String control_type;//控制人适用情况
    @SerializedName(value = "crs_control_type", alternate = "CITaxResidentStatus")
    @JSONField(name = "crs_control_type")
    private String crs_control_type;//控制人税收居民身份
    @SerializedName(value = "current_addr", alternate = "CICurAddr")
    @JSONField(name = "current_addr")
    private String current_addr;//现居地址
    @SerializedName(value = "current_city", alternate = "CICurCity")
    @JSONField(name = "current_city")
    private String current_city;//当前居住城市
    @SerializedName(value = "current_nation", alternate = "CICurCountry")
    @JSONField(name = "current_nation")
    private String current_nation;//当前居住国家（地区）
//    @SerializedName(value = "eng_addr", alternate = "")
//    @JSONField(name = "eng_addr")
    private String eng_addr;//英文当前居住地址
    @SerializedName(value = "eng_born_nation", alternate = "CIEnBirthCountry")
    @JSONField(name = "eng_born_nation")
    private String eng_born_nation;//英文出生国家/地区
    @SerializedName(value = "eng_current_addr", alternate = "CIPYCurAdd")
    @JSONField(name = "eng_current_addr")
    private String eng_current_addr;//现居地址（英文或拼音）
    @SerializedName(value = "eng_home_place", alternate = "CIPYBirthPlace")
    @JSONField(name = "eng_home_place")
    private String eng_home_place;//出生地（英文或拼音）
    @SerializedName(value = "eng_last_name", alternate = "CIEnName")
    @JSONField(name = "eng_last_name")
    private String eng_last_name;//英文控制人名
    @SerializedName(value = "eng_name", alternate = "CIPYName")
    @JSONField(name = "eng_name")
    private String eng_name;//姓（英文或拼音）
    @SerializedName(value = "engcurrentnation", alternate = "CIEnCurCountry")
    @JSONField(name = "engcurrentnation")
    private String engcurrentnation;//英文当前居住国家（地区）
    private String hold_ratio;//直接/间接持股比例
    @SerializedName(value = "home_place", alternate = "CIBirthPlace")
    @JSONField(name = "home_place")
    private String home_place;//出生城市
    private String json_belong_id;//JSON对象所属编号
    private String json_id;//JSON对象自身编号
    @SerializedName(value = "name", alternate = "CIName")
    @JSONField(name = "name")
    private String name;//姓名
    @SerializedName(value = "taxinfo", alternate = "CITaxpayerInfoList")
    @JSONField(name = "taxinfo")
    private List<ZXTaxinfoDTO> taxinfo;//控制人纳税信息
}
