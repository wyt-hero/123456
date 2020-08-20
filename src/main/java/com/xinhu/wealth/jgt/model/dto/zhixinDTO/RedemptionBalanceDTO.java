package com.xinhu.wealth.jgt.model.dto.zhixinDTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wyt
 * @data 2020/3/5 14:49
 * 3.6查询赎回款垫资余额----------S429历史基金行情查询
 */
@Data
public class RedemptionBalanceDTO {
    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    @JSONField(name = "Fdate")
    private String Fdate= df.format(new Date());//日期
    @SerializedName(value = "AdvanceBalanceDay", alternate = "today_income")
    @JSONField(name = "today_income")
    private String AdvanceBalanceDay;//当日赎回垫资余额

}
