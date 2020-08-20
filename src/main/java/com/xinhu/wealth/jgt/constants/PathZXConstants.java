package com.xinhu.wealth.jgt.constants;

/**
 * @author wyt
 * @data 2020/3/16 17:08
 */
public enum PathZXConstants {


    T501("T501", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/trade/orgsubscribeapply?merid="+JGTConstant.ZX_MERID,"","T501认购"),
    T502("T502", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/trade/orgpurchaseapply?merid="+JGTConstant.ZX_MERID, "", "T502申购"),
    T513("T513", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/trade/orgredeem?merid="+JGTConstant.ZX_MERID,"","T513赎回"),
    T518("T518", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/trade/orgfundconvert?merid="+JGTConstant.ZX_MERID,"","T518基金转换"),
    T514("T514", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/trade/orgmodifybonus?merid="+JGTConstant.ZX_MERID,"","T514修改分红方式"),
    T516("T516", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/trade/orgwithdrawapply?merid="+JGTConstant.ZX_MERID,"","T516撤单"),
    S416("S416", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/tradeconfirmquery?merid="+JGTConstant.ZX_MERID,"","S416 交易成交查询"),
    S420("S420", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/bonusquery?merid="+JGTConstant.ZX_MERID,"","S420-分红查询"),
    S403("S403", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/sharequery?merid="+JGTConstant.ZX_MERID,"","S403份额查询"),
    S407("S407", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/traderesultquery?merid="+JGTConstant.ZX_MERID,"","S407交易结果查询"),
    //3.1行情信息查询,K406基金行情信息查询\S428最新基金行情信息查询-需确认取直销行情还是聚源最新行情
    S428("S428", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/fundmarketinfoquery?merid="+JGTConstant.ZX_MERID,"","最新基金行情查询(S428)"),
    S429("S429", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/historyfundmarketinfoquery?merid="+JGTConstant.ZX_MERID,"","历史基金行情查询(S429)"),
    C503("C503", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/account/orgopenfundaccount?merid="+JGTConstant.ZX_MERID,"","C503机构开户"),
    P401("P401", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/et/pub/account/dicitems?merid="+JGTConstant.ZX_MERID,"","字典查询（银行编号查询银行名称）"),
    C513("C513", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/account/orgclosefundacco?merid="+JGTConstant.ZX_MERID,"","C513机构基金账户销户"),
    C479("C479", ConfigParam.zxBaseUrl + "/openapi/wmbusrestful/ds/pub/query/counterauditresultquery?merid="+JGTConstant.ZX_MERID,"","C479柜台审核结果查询"),
    C512("C512", ConfigParam.zxBaseUrl + "/openapi/wmbusrestful/ds/org/account/orgapplyfundacco?merid="+JGTConstant.ZX_MERID,"","C512机构增开基金账号"),
    //S486需申请接口授权
    S486("S483", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/accountconfirmquery?merid="+JGTConstant.ZX_MERID,"","账户确认查询(S486)"),
    C502("C502", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/account/orgmodifycustomerinfo?merid="+JGTConstant.ZX_MERID,"","C502修改客户信息"),
    //todo 基金账号查询(S405)
    S405("S405", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/fundaccountquery?merid="+JGTConstant.ZX_MERID,"","基金账号查询(S405)"),
    //todo TA信息查询(S443)：根据ta_code查询对应名称RegistrarName 3.1使用
    S443("S443", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/query/tainfoquery?merid="+JGTConstant.ZX_MERID,"","3.1行情信息查询使用"),
    //文件上传
    C4AB("C4AB", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/pub/account/fileuploadinfo?merid="+JGTConstant.ZX_MERID,"","文件上传(C4AB)"),
    //S003交易申请查询
    S501("S501", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/query/orgtradeapplyquery?merid="+JGTConstant.ZX_MERID,"","机构交易申请查询(S501)"),
    S414("S414", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/pub/query/tradealllimitquery?merid="+JGTConstant.ZX_MERID,"","交易累计限制查询(S414)"),
    S435("S435", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/pub/query/tradelimitquery?merid="+JGTConstant.ZX_MERID,"","交易限制查询(S435)"),
    K406("K406", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/et/pub/query/netvaluequery?merid="+JGTConstant.ZX_MERID,"","基金行情信息查询（三方）(K406)"),
    C504("C504", ConfigParam.zxBaseUrl +"/openapi/wmbusrestful/ds/org/account/orgopentradeaccount?merid="+JGTConstant.ZX_MERID,"","机构增开(C504)"),
    S445("S445", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/pub/query/accountapplyquery?merid="+JGTConstant.ZX_MERID,"","账户申请查询(S445)"),
    C515("C515", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/org/account/orgmodifytradepassword?merid="+JGTConstant.ZX_MERID,"","机构修改交易密码(C515)"),
    C422("C422", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/pub/account/custriskinfoquery?merid="+JGTConstant.ZX_MERID,"","客户风险信息查询(C422)"),
    C446("C446", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/pub/account/custinfoquerybyid?merid="+JGTConstant.ZX_MERID,"","客户资料查询(C446)"),
    C508("C508", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/org/account/orgpaperinfoquery?merid="+JGTConstant.ZX_MERID,"","获取风险问卷(题目和选项)(C508)"),
    C509("C509", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/org/account/orgsubmitpaperinfo?merid="+JGTConstant.ZX_MERID,"","提交风险问卷答案(C509)"),
    K426("K426", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/et/pub/query/moneyvalueperformquery?merid="+JGTConstant.ZX_MERID,"","货币基金收益表现(聚源)(K426)"),
    S476("S476", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/pub/query/fundincomedetilquery?merid="+JGTConstant.ZX_MERID,"","基金持仓收益查询(S476)"),
    C517("C517", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/org/account/orgcrsinfooperate?merid="+JGTConstant.ZX_MERID,"","机构操作CRS信息(C517)"),
    C518("C518", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/org/account/orgcrsinfodelete?merid="+JGTConstant.ZX_MERID,"","机构删除CRS信息(C518)"),
    C519("C519", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/org/account/orgcrsinfoquery?merid="+JGTConstant.ZX_MERID,"","机构读取CRS信息(C519)"),
    S410("S410", ConfigParam.zxBaseUrl+"/openapi/wmbusrestful/ds/pub/query/bankstationquery?merid="+JGTConstant.ZX_MERID,"","银行网点信息查询(S410)"),

    //云毅交易结果回写
    ZXJY("ZXJY","http://47.98.89.145:8088/fundistribt.standard/api/directTradeBackfill.json","","2.5交易结果回写"),
    SZ50_1("S50-1","http://47.98.89.145:8088/fundistribt.standard/api/directAccountBackfill.json","","账户类回写");
    //接口功能号
    private String code;

    //接口地址
    private String url;

    //描述
    private String desc;

    PathZXConstants(String code, String url, String methodName, String desc) {
        this.code = code;
        this.url = url;
        this.desc = desc;

    }

    public String getUrl() {
        return  url;
    }
    public String getCode() {
        return code;
    }
}
