package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/7 16:53
 * 4.8 风险问卷查询
 */
@Data
public class YYDangerSearch {
    /**
     * 机构标识
     */
    private String InstitutionMarking;
    /**
     * 个人/机构标识
     */
    private String IndividualOrInstitution;
}
