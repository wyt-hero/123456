package com.xinhu.wealth.jgt.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wyt
 * @data 2020/4/17 11:30
 */
@Data
@Table(name = "user_jgt_change")
public class ChangeAccEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "body")
    private String body;
    @Column(name = "oserialno")
    private String oserialno;
    @Column(name = "busintype")
    private String businType;
    @Column(name = "yy_status")
    private String YYStatus;//1未执行，0已执行，2执行失败
    @Column(name = "zx_status")
    private String ZXStatus;
    @Column(name = "zx_result")
    private String ZXResult;
    @Column(name = "zz_error_msg")
    private String ZZErrorMsg;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private String updateTime;
    @Column(name = "change_error_msg")
    private String changeErrorMsg;
    @Column(name = "crs_status")
    private String CRSStatus;
}
