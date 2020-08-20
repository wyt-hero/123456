package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/7 17:37
 */
@Data
public class QuestionOption {

    @SerializedName(value = "OptionNo",alternate = "option_no")
    @JSONField(name = "OptionNo")
    private String  OptionNo;//选项编号
    @SerializedName(value = "OptionContent",alternate = "option_content")
    @JSONField(name = "OptionContent")
    private String  OptionContent;//选项内容
    @SerializedName(value = "OptionOrder",alternate = "option_order")
    @JSONField(name = "OptionOrder")
    private String  OptionOrder;//选项顺序
    @SerializedName(value = "OptionScore",alternate = "option_score")
    @JSONField(name = "OptionScore")
    private String  OptionScore;//选项得分
    @SerializedName(value = "QuestionNo",alternate = "question_no")
    @JSONField(name = "QuestionNo")
    private String QuestionNo;//问题编号
}
