package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @data 2020/4/14 13:25
 */
@Data
public class Result4005DTO<T>{

    /**
     * 接口版本号
     */
    @JSONField(name = "Version")
    private String version;

    /**
     * 发送时间
     */
    @JSONField(name = "SendTime")
    private String sendTime;

    /**
     * 接收状态
     */
    @JSONField(name = "Result")
    private Integer result;

    @JSONField(name = "Message")
    private String message;


    /**
     * 页码编号
     */
    @JSONField(name = "PageIndex")
    private Integer pageIndex;

    /**
     * 页数
     */
    @JSONField(name = "PageSize")
    private Integer pageSize;

    /**
     * 每页数量
     */
    @JSONField(name = "RowSize")
    private Integer rowSize;

    /**
     * 总条数
     */
    @JSONField(name = "TotalCount")
    private Integer totalCount;


    @JSONField(name = "YYSign")
    private String yySign;


    @JSONField(name = "Data")
    private List<Detailed4005DTO> data;

    public Result4005DTO() {
    }

    public Result4005DTO(String version, String sendTime, Integer pageIndex, Integer pageSize, Integer rowSize, Integer totalCount) {
        this.version = version;
        this.sendTime = sendTime;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.rowSize = rowSize;
        this.totalCount = totalCount;
    }
}
