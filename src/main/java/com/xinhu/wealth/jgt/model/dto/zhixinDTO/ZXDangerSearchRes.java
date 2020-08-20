package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/7 17:25
 * 客户风险信息查询(C422)返回值
 */
@Data
public class ZXDangerSearchRes {
    private String code;// 返回错误码
    private String client_id  ;// 客户编号
    private String elig_content  ;// 客户答题内容
    private String full_name  ;// 账户全称
    private String id_kind_gb  ;// 证件类别
    private String id_no  ;// 证件号码
    private String invest_risk_tolerance  ;// 投资人风险承受能力
    private String lowest_risk_question_content  ;// 最低风险判定问题内容
    private String lowest_risk_tolerance  ;// 最低风险承受能力标志
    private String message  ;// 返回错误信息
    private String paper_id  ;// 问卷编号
    private String paper_score  ;// 试卷得分
    private String risk_validate  ;// 问卷到期日
    private String riskexpired  ;// 是否过期标志
    private String ta_acco  ;// TA账号/基金账号
    private String top_risk_level  ;// 最高产品风险等级
    private String trade_acco  ;// 交易账号
    private String update_date  ;// 记录更新日期
}
