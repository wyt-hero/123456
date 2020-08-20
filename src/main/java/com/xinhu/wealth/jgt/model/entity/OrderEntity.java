package com.xinhu.wealth.jgt.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author wyt
 * @data 2020/4/9 17:55
 */
@Data
@Table(name = "user_jgt_order")
public class OrderEntity {
    @Column(name = "id")
    @Id
    private Integer id;
    @Column(name = "pwd")
    private String passWord;
    @Column(name = "body")
    private String body;
    @Column(name = "sing")
    private String sing;
    @Column(name = "prosscode")
    private String prossCode;
    @Column(name = "version")
    private String version;
    @Column(name = "pageindex")
    private String pageIndex;
    @Column(name = "sendtime")
    private String sendTime;
    @Column(name = "status")
    private Integer status;
    @Column(name = "bunisscode")
    private String bunissCode;
    @Column(name = "zx_result")
    private String ZXResult;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @Column(name = "yy_err_mes")
    private String YYErrMes;
    @Column(name = "oserialno")
    private String OSerialno;//流水号
    @Column(name = "zx_search_result")
    private String ZXSearchResult;
    @Column(name = "allot_no")
    private String allotNo;
    @Column(name = "zx_status")
    private String ZXStatus;
}
