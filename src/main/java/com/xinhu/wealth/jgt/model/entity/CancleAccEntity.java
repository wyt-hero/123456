package com.xinhu.wealth.jgt.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author wyt
 * @data 2020/4/10 09:24
 */
@Data
@Table(name = "user_jgt_cancle_account")
public class CancleAccEntity {
    @Column(name = "id")
    @Id
    private Integer Id;
    @Column(name = "body")
    private String body;
    @Column(name = "t_a_code")
    private String TAACount;
    @Column(name = "sing")
    private String sing;
    @Column(name = "procss_code")
    private String prossCode;
    @Column(name = "version")
    private String version;
    @Column(name = "send_time")
    private String sendTime;
    @Column(name = "status")
    private String status;//0代表已执行，1代表未执行
    @Column(name = "zx_result")
    private String ZXResult;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime update_time;
    @Column(name = "error_message")
    private String error_message;//植信错误信息
    @Column(name = "yy_err_mes")
    private String YYErrMes;
    @Column(name = "oserialno")
    private String OSerialno;//流水号

    /**
     * 确认结果
     */
    @Column(name = "confirm_result")
    private String confirmResult;

    /**
     * 确认日期
     */
    @Column(name = "affirm_date")
    private String affirmDate;
}
