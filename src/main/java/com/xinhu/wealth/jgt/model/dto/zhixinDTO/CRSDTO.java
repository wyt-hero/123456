package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @date 2020-08-04-13-13-18
 * 此类用于CRS入参
 */
@Data
public class CRSDTO {
    private String channel;// 交易渠道
    private String signmsg  ;// 签名信息
    private String trust_way  ;// 交易委托方式
    private String usertyp="0"  ;// 商户类型
    private String rpcSign  ;// 是否对接微服务
    private String tenantId  ;// 租户ID
    private String sysId  ;// 应用系统编号
    private String sysEntryId  ;// 应用系统入口
    private String crsinfo_json  ;// CRS信息
}
