package com.xinhu.wealth.jgt.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wyt
 * @data 2020/4/16 10:19
 */
@Data
@Table(name = "user_jgt_trad")
public class TradeIncreaseEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 入参
     */
    @Column(name = "body")
    private String body;

    /**
     * 流水号
     */
    @Column(name = "oserialno")
    private String oserialno;//

    /**
     * 版本号
     */
    @Column(name = "version")
    private String version;

    /**
     * 业务代码
     */
    @Column(name = "process_code")
    private String processCode;

    /**
     * 植信返回结果是否成功，1成功，0失败
     */
    @Column(name = "zx_status")
    private String zxStatus;

    /**
     * 回写成功状态，1已执行，0未执行，2执行失败（失败错误信息有yyMsg）
     */
    @Column(name = "yy_status")
    private String yyStatus;

    /**
     * 云逸回调信息
     */
    @Column(name = "yy_msg")
    private String yyMsg;


    /**
     * 植信接口返回值
     */
    @Column(name = "zx_result")
    private String zxResult;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;
}
