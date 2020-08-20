package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/29 09:13
 */
@Data
public class ParticipationDTO {
    /**
     * 业务代码
     */
    private String processCode;
    /**
     * 功能号
     */
    private String busintype;
    /**
     * 入参json
     */
    private String body;
    /**
     * 验签的时间
     */
    private String sendTime;
    /**
     * 版本号
     */
    private String version;
    /**
     * 当前页
     */
    private String pageIndex;

    /**
     * 每页行数
     */
    private Integer rowSize;
    /**
     * 机构标识
     */
    private String InstitutionMarking;
}
