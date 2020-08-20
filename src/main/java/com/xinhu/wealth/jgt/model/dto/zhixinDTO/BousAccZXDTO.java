package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/4/2 20:44
 *
 */
@Data
public class BousAccZXDTO {
    private String channel;//交易渠道
    private String signmsg;//签名信息
    private String trust_way;//交易委托方式
    private String usertype;//商户类型
    private String rpcSign;//是否对接微服务
    private String tenantId;//租户ID
    private String sysId;//应用系统编号
    private String sysEntryId;//应用系统入口
    private String client_id  ;//客户编号
    private String cust_type    ;//客户类别
    private String ect_cid    ;//外部客户客户号
    private String ect_uid    ;//外部客户用户号

    @SerializedName(value = "qry_beginrownum", alternate = "PageIndex")
    @JSONField(name = "qry_beginrownum")
    private String qry_beginrownum    ;//页码

    @SerializedName(value = "request_num", alternate = "RowSize")
    @JSONField(name = "request_num")
    private String request_num    ;//每页记录数

    @SerializedName(value = "ta_acco", alternate = "TAAccountID")
    @JSONField(name = "ta_acco")
    private String ta_acco    ;//TA账号/基金账号
    @SerializedName(value = "trade_acco", alternate = "TransactionAccountID")
    @JSONField(name = "trade_acco")
    private String trade_acco    ;//交易账号
    private String trade_source    ;//交易来源
    private String other_user_id      ;//对方平台ID
}
