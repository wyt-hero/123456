package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 17:07
 * 份额查询(S403)
 */
@Data
public class ShareDTO {
    private CodeMessageDTO codeMessageEntity;
    private Integer rowcount;//记录数
    private Integer total_count;//总记录数
    private ShareQuerysDTO shareQuerysEntity;
}
