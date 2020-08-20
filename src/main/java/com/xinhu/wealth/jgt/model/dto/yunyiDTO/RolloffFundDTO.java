package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 09:10
 * 可转换出基金列表查询(T413)
 */
@Data
public class RolloffFundDTO {
    private CodeMessageDTO codeMessageEntity;
    private Integer rowcount;//记录数
    private Integer total_count;//总记录数
    private ConvertOutListsDTO convertOutListsEntity;
}
