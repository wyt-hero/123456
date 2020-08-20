package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/9 13:35
 * 提交风险问卷答案(C509)
 */
@Data
public class ZX4009Result {
    /**
     * 返回错误码
     */
    private String code;
    /**
     *  投资人风险承受能力
     */
    private String invest_risk_tolerance;
    /**
     *  返回错误信息
     */
    private String message;
    /**
     * 问卷编号
     */
    private String paper_id  ;
    /**
     *  试卷得分
     */
    private String paper_score;
    /**
     * 问卷有效期
     */
    private String risk_end_date;
}
