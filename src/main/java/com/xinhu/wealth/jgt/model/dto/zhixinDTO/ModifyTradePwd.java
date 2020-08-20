package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wyt
 * @data 2020/5/7 15:19
 * 机构修改交易密码(C515)
 */
@Data
public class ModifyTradePwd {
        /**
         * 交易渠道
         */
        private String channel;
        /**
         * 签名信息
         */
        private String signmsg  ;
        /**
         * 交易委托方式
         */
        private String trust_way  ;
        /**
         * 商户类型
         */
        private String usertype  ;
        /**
         * 是否对接微服务
         */
        private String rpcSign  ;
        /**
         * 租户ID
         */
        private String tenantId  ;
        /**
         *  应用系统编号
         */
        private String sysId;
        /**
         *  应用系统入口
         */
        private String sysEntryId;
        /**
         *  证件类别
         */
        @SerializedName(value = "id_kind_gb", alternate = "certificateType")
        @JSONField(name = "id_kind_gb")
        private String id_kind_gb;
        /**
         * 证件号码
         */
        @SerializedName(value = "id_no", alternate = "certificateNo")
        @JSONField(name = "id_no")
        private String id_no  ;
        /**
         *  新密码
         */
        @SerializedName(value = "new_password", alternate = "newPassword")
        @JSONField(name = "new_password")
        private String new_password;
        /**
         *  密码
         */
        private String password;
        /**
         *  申请时间
         */
        private String apply_time;
        /**
         * 下单日期
         */
        private String order_date  ;
        /**
         *  TA账号
         */
        private String ta_acco;
        /**
         *  交易账号
         */
        @SerializedName(value = "trade_acco", alternate = "transactionAccountID")
        @JSONField(name = "trade_acco")
        private String trade_acco;

}
