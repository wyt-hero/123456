package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/7 16:54
 * 客户风险信息查询(C422)
 */
@Data
public class ZXDangerSearch {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    //usertype
    @SerializedName(value = "usertype",alternate = "individualOrInstitution")
    @JSONField(name = "usertype")
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口

    /**
     * 诚信类别
     */
    private String integrity_type;
    /**
     *  问卷客户类别
     */
    private String paper_client_type;
    /**
     *  选项状态
     */
    private String option_state;
}
