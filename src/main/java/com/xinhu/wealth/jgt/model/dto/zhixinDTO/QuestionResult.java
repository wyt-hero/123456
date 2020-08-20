package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.QuestionOption;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.QuestionQuery;
import lombok.Data;

import java.util.List;

/**
 * @author wyt
 * @data 2020/5/9 17:25
 */
@Data
public class QuestionResult {
    @JSONField(name = "QuestionQuery")
    private List<QuestionQuery> questionQuery;
    @JSONField(name = "QuestionOption")
    private List<QuestionOption> questionOption;
}
