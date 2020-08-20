package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/5 10:09
 * 可转换入基金列表查询(T414)
 */
@Data
public class RollonFundDTO {
    private CodeMessageDTO codeMessageEntity;
    private Integer rowcount;//记录数
    private Integer total_count;//总记录数
    private ConvertInListsDTO convertInListsEntity;
}
