package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/9 13:26
 * 提交风险问卷答案(C509)
 */
@Data
public class ZXDangeAssess {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式

    @SerializedName(value = "usertype",alternate = "individualOrInstitution")
    @JSONField(name = "usertype")
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口

    @SerializedName(value = "full_name",alternate = "investorName")
    @JSONField(name = "full_name")
    private String full_name  ;//账户全称
    @SerializedName(value = "id_kind_gb",alternate = "certificateType")
    @JSONField(name = "id_kind_gb")
    private String id_kind_gb  ;//证件类别
    @SerializedName(value = "id_no",alternate = "certificateNo")
    @JSONField(name = "id_no")
    private String id_no  ;//证件号码
    @SerializedName(value = "elig_content",alternate = "questionContent")
    @JSONField(name = "elig_content")
    private String elig_content  ;//客户答题内容
    private String invest_risk_tolerance  ;//投资人风险承受能力
    private String lowest_risk_question_content  ;//最低风险判定问题内容
    private String paper_client_type  ;//问卷客户类别
    private String risk_end_date  ;//问卷有效期
    private String pre_check_flag    ;//预检查标识
}
