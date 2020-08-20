package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/3 10:56
 * 根据银行编号查询银行名称
 */
@Data
public class BankNameP401DTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口
    private String caption;//字典名字

}
