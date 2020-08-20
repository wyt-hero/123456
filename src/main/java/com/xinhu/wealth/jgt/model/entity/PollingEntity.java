package com.xinhu.wealth.jgt.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author wyt
 * @data 2020/3/25 15:41
 */
@Data
@Table(name = "user_jgt_polling")
public class PollingEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "pwd")
    private String passWord;
    /*
    修改前body，只有当修改数据，此字段才会有值
     */
    @Column(name = "before_body")
    private String beforeBody;
    @Column(name = "body")
    private String body;
    @Column(name = "sing")
    private String sing;
    @Column(name = "processCode")
    private String processCode;
    @Column(name = "version")
    private String version;
    @Column(name = "pageIndex")
    private Integer pageIndex;
    @Column(name = "sendTime")
    private String sendTime;
    @Column(name = "type")
    private Integer type;
    @Column(name = "statu")
    private Integer statu;
    @Column(name = "acc_result")
    private String accResult;
    @Column(name = "is_file")
    private Integer isFile;
    @Column(name = "t_a_id")
    private String transactionAccountID;
    @Column(name = "ta_code")
    private String taCode;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @Column(name = "acc_status")
    private Integer accStatus;
    @Column(name = "file_error_message")
    private String fileErrorMessage;
    @Column(name = "acc_error_message")
    private String accErrorMessage;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "oserialno")
    private String OSerialno;
    @Column(name = "yy_err_mes")
    private String YYErrMes;

    /**
     * 1代表可回写
     */
    @Column(name = "crs_status")
    private String CRSStatus;
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
