package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/9 13:38
 * 4.9提交风险问卷答案(C509)返参
 */
@Data
public class YY4009Result {
    /**
     * 投资人风险承受能力
     */
    @SerializedName(value = "RiskTolerance",alternate = "invest_risk_tolerance")
    @JSONField(name = "RiskTolerance")
    private String  RiskTolerance;
    /**
     * 问卷有效期
     */
    @SerializedName(value = "RiskDate",alternate = "risk_end_date")
    @JSONField(name = "RiskDate")
    private String  RiskDate;
    /**
     * 试卷得分
     */
    @SerializedName(value = "PaperScore",alternate = "paper_score")
    @JSONField(name = "PaperScore")
    private String  PaperScore;
}
