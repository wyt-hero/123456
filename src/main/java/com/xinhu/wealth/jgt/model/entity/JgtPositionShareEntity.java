package com.xinhu.wealth.jgt.model.entity;

import io.swagger.annotations.*;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 表:  jgt_position_share
 * @author  dxt
 * @date  2020-04-16 19:18:04
 */ 
@Data
@ApiModel("jgt_position_share实体")
@Table(name="jgt_position_share")
public class JgtPositionShareEntity{


	@Id
	@Column(name = "id")
	@ApiModelProperty("")
	private Integer id;//

	@Column(name ="enable_shares")
	@ApiModelProperty("")
	private BigDecimal AvailableVol;//

	@Column(name ="current_share")
	@ApiModelProperty("")
	private BigDecimal TotalVolOfDistributorInTA;//

	@Column(name ="affirm_date")
	@ApiModelProperty("")
	private String TransactionCfmDate;//

	@Column(name ="fund_code")
	@ApiModelProperty("")
	private String FundCode;//

	@Column(name ="trade_acco")
	@ApiModelProperty("")
	private String TransactionAccountID;//

	@Column(name ="ta_acco")
	@ApiModelProperty("")
	private String TAAccountID;//

	@Column(name ="frozen_share")
	@ApiModelProperty("")
	private BigDecimal TotalFrozenVol;//

	@Column(name ="ta_serial_id")
	@ApiModelProperty("")
	private String TASerialNO;//

	@Column(name ="share_type")
	@ApiModelProperty("")
	private String ShareClass;//

	@Column(name ="unpaid_income")
	@ApiModelProperty("")
	private BigDecimal UndistributeMonetaryIncome;//

	@Column(name ="auto_buy")
	@ApiModelProperty("")
	private String DefDividendMethod;//

	@Column(name ="ta_no")
	@ApiModelProperty("")
	private String RegistrarCode;//


    @Column(name ="account_status")
    @ApiModelProperty("")
    private String AccountStatus;//

	@Column(name = "accum_income")
	private String AccIncome="0.00";//累计收入
	@Column(name = "income_by_hold")
	private String ByIncome="0.00";//持有收益

	@Column(name="holding_income")
	private String HoldingIncome="0.00";// 持仓收益
	@Column(name = "fund_curr_income")//接口中叫基金收益
	private String DayIncome="0.00";//昨日收益

 }

