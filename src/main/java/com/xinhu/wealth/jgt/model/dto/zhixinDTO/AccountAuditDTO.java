package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 14:59
 * 4.3账户结果回写-------C479柜台审核结果查询
 */
@Data
public class AccountAuditDTO {
    //private ResultDTO pageCodeEntity;
    private String Version;//接口版本号
    private String SendTime;//发送时间
    private String Result;//接收状态
    private String Message;//接收状态信息

    private Integer PageIndex;//页码编号
    private Integer PageSize;//页数
    private Integer RowSize;//每页数量
    private Integer TotalCount;//总条数
}
