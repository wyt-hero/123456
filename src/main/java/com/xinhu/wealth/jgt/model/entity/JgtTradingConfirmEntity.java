package com.xinhu.wealth.jgt.model.entity;

import io.swagger.annotations.*;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 表:  jgt_trading_confirm
 * @author  dxt
 * @date  2020-04-16 19:15:29
 */ 
@Data
@ApiModel("jgt_trading_confirm实体")
@Table(name="jgt_trading_confirm")
public class JgtTradingConfirmEntity{


	@Id
	@ApiModelProperty("")
	private Integer id;//

	 @Column(name ="original_appno")
	@ApiModelProperty("")
	private String Oserialno;//

	@Column(name ="allot_no")
	@ApiModelProperty("")
	private String Appserialno;//

	@Column(name ="affirm_date")
	@ApiModelProperty("")
	private String TransactionCfmDate;//

	/**
	 * //确认标志
	 * 0  确认失败
	 * 2  部分确认
	 * 5  行为确认
	 * 9  未处理
	 * 4  已撤销交易
	 * 1  确认成功
	 * 3  实时确认成功
	 */
	@Column(name = "confirm_flag")
	private String ReturnCode;

	@Column(name ="money_type")
	@ApiModelProperty("")
	private String CurrencyType;//

	@Column(name ="trade_confirm_balance")
	@ApiModelProperty("")
	private BigDecimal ConfirmedAmount;//

	@Column(name ="fund_code")
	@ApiModelProperty("")
	private String FundCode;//

	@Column(name ="cust_no")
	@ApiModelProperty("")
	private String InvestorCode;//

	@Column(name ="trade_acco")
	@ApiModelProperty("")
	private String TransactionAccountID;//

	@Column(name ="fund_busin_code")
	@ApiModelProperty("")
	private String BusinessCode;//

	@Column(name ="ta_acco")
	@ApiModelProperty("")
	private String TAAccountID;//

	@Column(name ="ta_serial_id")
	@ApiModelProperty("")
	private String TASerialNO;//

	@Column(name ="fare_sx")
	@ApiModelProperty("")
	private BigDecimal Charge;//

	@Column(name ="ta_fare")
	@ApiModelProperty("")
	private BigDecimal OtherFee1;//

	@Column(name ="stamptax")
	@ApiModelProperty("")
	private BigDecimal StampDuty;//

	@Column(name ="oppo_netno")
	@ApiModelProperty("")
	private String TargetBranchCode;//

	@Column(name ="auto_buy")
	@ApiModelProperty("")
	private String DefDividendMethod;//

	@Column(name ="net_value")
	@ApiModelProperty("")
	private String NAV;//

	@Column(name ="trade_confirm_type")
	@ApiModelProperty("")
	private BigDecimal ConfirmedVol;//

	@Column(name ="share_type")
	@ApiModelProperty("")
	private String ShareClass;//

	@Column(name ="target_fund_code")
	@ApiModelProperty("")
	private String CodeOfTargetFund;//

	@Column(name ="oppo_fund_account")
	@ApiModelProperty("")
	private String TargetTAAccountID;//

	@Column(name ="target_share_type")
	@ApiModelProperty("")
	private String TargetShareType;//

	@Column(name ="message")
	@ApiModelProperty("")
	private String ErrorDetail;//

	@Column(name ="application_amount")
	@ApiModelProperty("")
	private BigDecimal ApplicationAmount;//

	@Column(name ="application_vol")
	@ApiModelProperty("")
	private BigDecimal ApplicationVol;//

	@Column(name ="target_registrar_code")
	@ApiModelProperty("")
	private String TargetRegistrarCode;//

	//LargeRedemptionFlag巨额赎回标志
	@Column(name = "large_redemption_flag")
	@ApiModelProperty("")
	private String LargeRedemptionFlag;//巨额赎回标志

 }

