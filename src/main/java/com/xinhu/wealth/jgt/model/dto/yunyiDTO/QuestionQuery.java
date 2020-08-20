package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/7 17:33
 * 风险问卷查询返回值结果
 */
@Data
public class QuestionQuery {
    @SerializedName(value = "QuestionNo",alternate = "question_no")
    @JSONField(name = "QuestionNo")
    private String QuestionNo;//问题编号
    @SerializedName(value = "QuestionContent",alternate = "question_content")
    @JSONField(name = "QuestionContent")
    private String QuestionContent;//问题内容
    @SerializedName(value = "QuestionOrder",alternate = "question_order")
    @JSONField(name = "QuestionOrder")
    private String QuestionOrder;//顺序号
}
