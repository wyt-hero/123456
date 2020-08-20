package com.xinhu.wealth.jgt.model.entity;

import io.swagger.annotations.*;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 表:  jgt_bonus
 * @author  dxt
 * @date  2020-04-16 19:16:03
 */ 
@Data
@ApiModel("jgt_bonus实体")
@Table(name="jgt_bonus")
public class JgtBonusEntity{


	@Id
	@Column(name ="id")
	@ApiModelProperty("")
	private Integer id;//

	@Column(name ="affirm_date")
	@ApiModelProperty("")
	private String TransactionCfmDate;//

	@Column(name ="bill_money_type")
	@ApiModelProperty("")
	private String CurrencyType;//

	@Column(name ="dividend_date")
	@ApiModelProperty("")
	private String DividentDate;//

	@Column(name ="dividend_totalbala")
	@ApiModelProperty("")
	private BigDecimal DividendAmount;//

	@Column(name ="ex_dividend_date")
	@ApiModelProperty("")
	private String XRDate;//

	@Column(name ="unit_bala")
	@ApiModelProperty("")
	private BigDecimal ConfirmedAmount;//

	@Column(name ="fund_code")
	@ApiModelProperty("")
	private String FundCode;//

	@Column(name ="equity_reg_date")
	@ApiModelProperty("")
	private String RegistrationDate;//

	@Column(name ="trade_acco")
	@ApiModelProperty("")
	private String TransactionAccountID;//

	@Column(name ="ta_acco")
	@ApiModelProperty("")
	private String TAAccountID;//

	@Column(name ="auto_buy")
	@ApiModelProperty("")
	private String DefDividendMethod;//

	@Column(name = "volof_dividenfor_reinvestement")
	private String VolOfDividendforReinvestment;//基金账户红利再投资基金份数

	@Column(name ="fare_sx")
	@ApiModelProperty("")
	private BigDecimal Charge;//

	@Column(name ="net_value")
	@ApiModelProperty("")
	private BigDecimal NAV;//

	@Column(name ="ta_serial_id")
	@ApiModelProperty("")
	private String TASerialNO;//

	@Column(name ="capital_mode")
	@ApiModelProperty("")
	private String ShareClass;//

	@Column(name ="bonus_unit")
	@ApiModelProperty("")
	private String DrawBonusUnit;//

	@Column(name ="frozen_shares")
	@ApiModelProperty("")
	private BigDecimal FrozenSharesforReinvest;//

	@Column(name ="original_appno")
	@ApiModelProperty("")
	private String OriginalAppSheetNo;//

	@Column(name ="ta_code")
	@ApiModelProperty("")
	private String RegistrarCode;//

 }

