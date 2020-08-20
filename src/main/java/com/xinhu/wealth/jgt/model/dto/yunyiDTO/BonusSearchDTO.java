package com.xinhu.wealth.jgt.model.dto.yunyiDTO;

import lombok.Data;

/**
 * @author wyt
 * @data 2020/3/4 16:51
 * 分红查询(S420)
 */
@Data
public class BonusSearchDTO {
    //private CodeMessageDTO codeMessageEntity;
    private String code;//返回错误码
    private String message;//返回错误信息
    private Integer rowcount;//记录数
    private Integer total_count;//总记录数
    private BonusQuerysDTO bonusQuerysEntity;
}
