package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:18
 * 文件上传(C4AB)
 */
@Data
public class FileuploadDTO {
    //private CodeMessageDTO codeMessageEntity;
    private String code;//返回错误码
    private String message;//返回错误信息
}
