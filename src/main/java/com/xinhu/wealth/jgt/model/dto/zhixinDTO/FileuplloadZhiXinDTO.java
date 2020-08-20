package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 14:37
 * 3.4文件上传-------C4AB文件上传
 */
@Data
public class FileuplloadZhiXinDTO {
    private String archives_content ;//上传文件内容（base64格式）
    private String archives_type   ;//档案类型
    private String fileext_name   ;//文件扩展名
    private String request_no ;//申请编号
    private String trade_acco;//交易账号
    private String icon_Type="2";// 水印类型
    private String net_auditflag="1";// 网上复核标志
}
