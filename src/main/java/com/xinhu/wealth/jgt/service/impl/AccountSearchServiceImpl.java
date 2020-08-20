package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.PassWord;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.mapper.CancleAccMapper;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.*;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.*;
import com.xinhu.wealth.jgt.model.entity.CancleAccEntity;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.service.AccountSearchService;
import com.xinhu.wealth.jgt.service.CancleAccService;
import com.xinhu.wealth.jgt.service.PollingService;
import com.xinhu.wealth.jgt.util.DateUtil;
import com.xinhu.wealth.jgt.util.HttpSend;
import com.xinhu.wealth.jgt.util.PageInfo;
import com.xinhu.wealth.jgt.util.YYSignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountSearchServiceImpl implements AccountSearchService {

    /**
     * 账户service
     */
    @Autowired
    private PollingService pollingService;

    @Autowired
    private PollingMapper pollingMapper;
    @Autowired
    private CancleAccMapper cancleAccMapper;
    @Autowired
    private CancleAccService cancleAccService;


    /**
     * 账户查询类
     * 从本地数据库查询数据返回给云毅
     *
     * @param body               入参body
     * @param version            版本号
     * @param processCode        功能代码
     * @param sing               签名
     * @param sendTime           发送时间
     * @param institutionMarking 机构标识
     * @param pageIndex          页码编号
     * @param rowSize            每页数量
     * @return
     */
    @Override
    public String accountSearch(String body, String version, String processCode, String sing, String sendTime, String institutionMarking, Integer pageIndex, Integer rowSize) {
        log.info("******账号查询类接口V2 body={},Version={},ProcessCode={},SingText={},SendTime={},InstitutionMarking={},PageIndex={},RowSize={}", body, version, processCode, sing, sendTime, institutionMarking, pageIndex, rowSize);
        //验签
        boolean bool = YYSignUtil.isSign(sendTime, body, sing);
        //todo
        bool = true;
        String time = DateUtil.getTime();
        if (bool) {
            //4.5基金账户确认结果查询
            if ("4005".equals(processCode)) {
                JSONObject jsonObject = JSON.parseObject(body);
                String transactionCfmDate = jsonObject.get("Fdate").toString();//账户确认日期
                if (rowSize == null) {
                    rowSize = 10;
                } else if (pageIndex == null) {
                    pageIndex = 1;
                }
                //根据交易确认日期从本地数据库中查询
                PageInfo pageInfo = pollingService.accountSearch(pageIndex, rowSize, transactionCfmDate);
                PageInfo canclePageInfo = cancleAccService.canclePageSearch(pageIndex, rowSize, transactionCfmDate);
                //根据确认日期查询到账户记录
                if (pageInfo != null && pageInfo.getTotal() > 0) {
                    List<PollingEntity> pollingList = (List<PollingEntity>) pageInfo.getContent();
                    int pageSize = 0;
                    if (pageInfo.getTotal() % rowSize == 0) {
                        pageSize = (int) pageInfo.getTotal() / rowSize;
                    } else if (pageInfo.getTotal() % rowSize != 0) {
                        pageSize = (int) pageInfo.getTotal() / rowSize + 1;
                    }
                    Result4005DTO result4005DTO = new Result4005DTO(version, time, pageIndex, pageSize, rowSize, (int) pageInfo.getTotal());
                    List<Detailed4005DTO> detailedList = new ArrayList<Detailed4005DTO>();
                    for (int i = 0; i < pollingList.size(); i++) {
                        PollingEntity polling = pollingList.get(i);
                        Detailed4005DTO detailed = new Detailed4005DTO();
                        Gson gson=new Gson();
                        detailed =gson.fromJson(polling.getConfirmResult(), Detailed4005DTO.class);
                        detailedList.add(detailed);
                    }
                    result4005DTO.setData(detailedList);
                    result4005DTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
                    result4005DTO.setMessage(ResultEntity.YUNYISUCCESS.getMsg());
                    //转成string，准备加签
                    String json = JSONObject.toJSONString(result4005DTO);
                    //加签
                    String sign = YYSignUtil.sign(time + "&" + json, JGTConstant.YY_PRI_KEY);
                    result4005DTO.setYySign(sign);
                    return JSONObject.toJSONString(result4005DTO);
                } else if (canclePageInfo != null && canclePageInfo.getTotal() > 0) {
                    List<CancleAccEntity> cancleAccEntityList = (List<CancleAccEntity>) canclePageInfo.getContent();
                    int pageSize = 0;
                    if (pageInfo.getTotal() % rowSize == 0) {
                        pageSize = (int) pageInfo.getTotal() / rowSize;
                    } else if (pageInfo.getTotal() % rowSize != 0) {
                        pageSize = (int) pageInfo.getTotal() / rowSize + 1;
                    }
                    Result4005DTO result4005DTO = new Result4005DTO(version, time, pageIndex, pageSize, rowSize, (int) pageInfo.getTotal());
                    List<Detailed4005DTO> detailedList = new ArrayList<Detailed4005DTO>();
                    //销户确认查询
                    //todo  查询销户确认数据，根据transactionCfmDate确认时间
                    for (CancleAccEntity cancle : cancleAccEntityList) {
                        Detailed4005DTO detailed4005DTO = JSON.parseObject((cancle.getConfirmResult()), Detailed4005DTO.class);
                        detailedList.add(detailed4005DTO);
                    }
                    result4005DTO.setData(detailedList);
                    result4005DTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
                    result4005DTO.setMessage(ResultEntity.YUNYISUCCESS.getMsg());
                    //转成string，准备加签
                    String json = JSONObject.toJSONString(result4005DTO);
                    //加签
                    String sign = YYSignUtil.sign(time + "&" + json, JGTConstant.YY_PRI_KEY);
                    result4005DTO.setYySign(sign);
                    return JSONObject.toJSONString(result4005DTO);
                } else {//根据确认日期没有查询到账户记录
                    Result4005DTO result4005DTO = new Result4005DTO(version, time, pageIndex, 0, rowSize, 0);
                    result4005DTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
                    result4005DTO.setMessage(ResultEntity.YUNYISUCCESS.getMsg());
                    List<Detailed4005DTO> list = new ArrayList<Detailed4005DTO>();
                    result4005DTO.setData(list);
                    //转成string，准备加签
                    String json = JSONObject.toJSONString(result4005DTO);
                    //加签
                    String sign = YYSignUtil.sign(time + "&" + json, JGTConstant.YY_PRI_KEY);
                    result4005DTO.setYySign(sign);
                    return JSONObject.toJSONString(result4005DTO);
                }
            } else if ("4007".equals(processCode)) {
                return modify4007PWD(body, version);
            } else if ("4008".equals(processCode)) {
                return query4008(body, version);
            } else if ("4009".equals(processCode)) {
                return query4009(body, version);
            } else {
                return "未找到对应的processCode，当前processCode为：" + processCode;
            }
        } else {//验签失败
            Result4005DTO dto = new Result4005DTO();
            dto.setVersion(version);
            dto.setSendTime(time);
            dto.setResult(ResultEntity.YUNYISINGERROR.getCode());
            dto.setMessage(ResultEntity.YUNYISINGERROR.getMsg());
            List<Detailed4005DTO> list = new ArrayList<Detailed4005DTO>();
            dto.setData(list);
            //转成string，准备加签
            String json = JSONObject.toJSONString(dto);
            //加签
            String sign = YYSignUtil.sign(time + "&" + json, JGTConstant.YY_PRI_KEY);
            dto.setYySign(sign);
            return JSONObject.toJSONString(dto);
        }
    }

    /**
     * 4.7修改交易密码（植信专用）
     *
     * @param body 入参
     * @return
     */
    public String modify4007PWD(String body, String version) {
        log.info("机构修改交易密码(C515)入参：{}", body);
        //接收返回值
        ResultDTO resultDTO = new ResultDTO();
        //云毅入参转换
        Gson gson = new Gson();
        ModifyZXTradePwd modifyTradePwd = new ModifyZXTradePwd();
        try {
            modifyTradePwd = gson.fromJson(body, new TypeToken<ModifyZXTradePwd>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("云毅入参转换异常：{}", e);
            resultDTO.setMessage("修改交易账号入参格式错误");
            return JSONObject.toJSONString(resultDTO);
        }
        //植信入参转换
        ModifyTradePwd modifyZXTradePwd = new ModifyTradePwd();
        try {
            modifyZXTradePwd = gson.fromJson(JSONObject.toJSONString(modifyTradePwd), new TypeToken<ModifyTradePwd>() {
            }.getType());
            try {
                String password = PassWord.decrypt(modifyZXTradePwd.getPassword());
                String newPwd = PassWord.decrypt(modifyZXTradePwd.getNew_password());
                modifyZXTradePwd.setPassword(password);
                modifyZXTradePwd.setNew_password(newPwd);
                log.info("修改密码用户入参，旧密码：{}", password);
                log.info("修改密码用户入参，新密码：{}", newPwd);
            } catch (Exception e) {
                log.info("密码解密异常");
            }
        } catch (JsonSyntaxException e) {
            log.info("云毅入参转换异常：{}", e);
            resultDTO.setMessage("修改交易账号入参格式错误");
            return JSONObject.toJSONString(resultDTO);
        }
        //获取C515的URL链接
        String url = PathZXConstants.C515.getUrl();
        log.info("C515植信链接：{}", url);
        //调用植信链接
        log.info("C515植信请求入参：{}", modifyZXTradePwd);
        resultDTO = HttpSend.doGetResult(url, modifyZXTradePwd);
        if ("200".equals(resultDTO.getCode())) {
            resultDTO.setMessage("修改交易密码成功");
            resultDTO.setResult(0);
            //修改密码成功，更新数据库
            PollingEntity pollingEntity = new PollingEntity();
            pollingEntity = pollingService.queryOneByAccountID(modifyZXTradePwd.getTrade_acco());
            if (pollingEntity != null) {
                pollingEntity.setPassWord(modifyZXTradePwd.getNew_password());
                pollingService.modifyPwdByTradAccount(pollingEntity);
            }
        } else {
            resultDTO.setResult(2);
        }
        resultDTO.setVersion(version);
        resultDTO.setSendTime(DateUtil.getTime());
        resultDTO.setData(null);
        log.info("4.7修改密码返回结果：{}", resultDTO);
        return JSONObject.toJSONString(resultDTO);
    }


    /**
     * 查询代销端风险问卷数据
     *
     * @param body
     * @param version
     * @return
     */
    public String query4008(String body, String version) {
        log.info("4.8查询代销端风险问卷数据入参：{}", body);
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        Gson gson = new Gson();
        YYDangerSearch yyDangerSearch = new YYDangerSearch();
        try {
            yyDangerSearch = gson.fromJson(body, YYDangerSearch.class);
        } catch (JsonSyntaxException e) {
            log.info("风险问卷查询转换异常：{}", e);
            resultDTO.setMessage("风险问卷查询入参格式错误");
            return JSONObject.toJSONString(resultDTO);
        }
        ZXDangerSearch zxDangerSearch = new ZXDangerSearch();
        try {
            zxDangerSearch = gson.fromJson(JSONObject.toJSONString(yyDangerSearch), ZXDangerSearch.class);
        } catch (JsonSyntaxException e) {
            log.info("风险问卷查询转换异常：{}", e);
            resultDTO.setMessage("风险问卷查询入参格式错误");
            return JSONObject.toJSONString(resultDTO);
        }
        //获取C422接口连接
        String url = PathZXConstants.C508.getUrl();
        log.info("客户风险信息查询(C422)链接：{}", url);
        log.info("客户风险信息查询(C422)入参：{}", zxDangerSearch);
        resultDTO = HttpSend.doGetResult(url, zxDangerSearch);

        //返回值data转换
        if (resultDTO != null && StringUtils.isNotEmpty(resultDTO.getData() + "")) {
            String data = resultDTO.getData() + "";
            log.info("4.8风险测评问卷获取植信返回结果：{}", data);
            List<QuestionOption> questionOptionList = new ArrayList<>();
            QuestionOption questionOption = new QuestionOption();
            List<QuestionQuery> questionQueryList = new ArrayList<>();
            QuestionQuery questionQuery = new QuestionQuery();
            //接收植信返回值
            String orgPaperInfoQuerys = "";
            String questionOptions = "";
            try {
                //获取data中orgPaperInfoQuerys的json
                orgPaperInfoQuerys = JSONObject.parseObject(data).getString("questions");
                //获取orgPaperInfoQuerys中questionOptions的json
                JSONArray arrayOrgPaperInfoQuerys = JSONArray.parseArray(orgPaperInfoQuerys);
                if (arrayOrgPaperInfoQuerys != null) {
                    for (Object json : arrayOrgPaperInfoQuerys) {
                        String jsonstr = JSONObject.toJSONString(json);
                        questionOptions = JSONObject.parseObject(jsonstr).getString("questionOptions");
                        JSONArray arrayQuestionOptions = JSONArray.parseArray(questionOptions);
                        for (Object question : arrayQuestionOptions) {
                            String jsonQuestion = JSONObject.toJSONString(question);
                            //返回值转换
                            questionOption = gson.fromJson(jsonQuestion, QuestionOption.class);
                            questionOptionList.add(questionOption);
                        }
                        questionQuery = gson.fromJson(jsonstr, QuestionQuery.class);
                        questionQueryList.add(questionQuery);
                    }
                }
            } catch (JsonSyntaxException e) {
                log.info("客户风险信息查询返回值转换异常：{}", e);
                resultDTO.setMessage("返回值错误");
                return JSONObject.toJSONString(resultDTO);
            }
            //接收返回值
            QuestionResult questionResult = new QuestionResult();
            questionResult.setQuestionOption(questionOptionList);
            questionResult.setQuestionQuery(questionQueryList);
            resultDTO.setData(questionResult);
            if ("200".equals(resultDTO.getCode())) {
                resultDTO.setResult(0);
                //todo 测试
                resultDTO.setTotalCount(questionQueryList.size());
            } else {
                resultDTO.setResult(2);
                resultDTO.setTotalCount(0);
                resultDTO.setData(null);
            }
        }
        resultDTO.setVersion(version);
        resultDTO.setSendTime(DateUtil.getTime());
        log.info("4.8查询代销端风险问卷数据结果：{}", resultDTO);
        return JSONObject.toJSONString(resultDTO);
    }

    /**
     * 提交风险测评结果
     *
     * @param body    入参
     * @param version 版本号
     * @return
     */
    public String query4009(String body, String version) {
        log.info("4.9入参转换前参数：{}", body);
        //返回云毅
        ResultDTO resultDTO = new ResultDTO();
        //1、云毅入参转换
        Gson gson = new Gson();
        //接收入参
        YYDangeAssess yyDangeAssess = new YYDangeAssess();
        ZXDangeAssess zxDangeAssess = new ZXDangeAssess();
        try {
            yyDangeAssess = gson.fromJson(body, YYDangeAssess.class);
            log.info("4.9入参转换为云毅实体类结果：{}", yyDangeAssess);
            //根据交易账户获取poling表的client_id
            PollingEntity queryClientID = new PollingEntity();
            queryClientID.setTransactionAccountID(yyDangeAssess.getTransactionAccountID());
            queryClientID = pollingMapper.selectOne(queryClientID);
            //2、云毅入参转换
            zxDangeAssess = gson.fromJson(JSONObject.toJSONString(yyDangeAssess), ZXDangeAssess.class);
            log.info("4.9入参转换为植信实体类结果：{}", zxDangeAssess);

        } catch (JsonSyntaxException e) {
            log.info("4.9入参转换异常：{}", e);
            resultDTO.setMessage("4.9入参格式错误");
            return JSONObject.toJSONString(resultDTO);
        }
        boolean bool = StringUtils.isNotEmpty(zxDangeAssess.getId_no()) && StringUtils.isNotEmpty(zxDangeAssess.getId_kind_gb()) && StringUtils.isNotEmpty(zxDangeAssess.getFull_name());
        if (bool) {
            //获取C509接口链接
            String url509 = PathZXConstants.C509.getUrl();
            log.info("4.9风险测评链接：{}", url509);
            log.info("4.9风险测评入参参数：{}", zxDangeAssess);
            resultDTO = HttpSend.doGetResult(url509, zxDangeAssess);
            //获取data中连接超时的错误信息
            String errMess = "";
            try {
                errMess = JSONObject.parseObject(resultDTO.getData() + "").getString("40404");
                YY4009Result yy4009Result = gson.fromJson(resultDTO.getData() + "", YY4009Result.class);
                log.info("4.9植信接口返参：{}", resultDTO.getData());
                //赋值返回值
                resultDTO.setData(yy4009Result);
            } catch (JsonSyntaxException e) {
                log.info("4.9返回值转换异常：{}", e);
                resultDTO.setMessage("4.9返回值错误");
            }
            if ("200".equals(resultDTO.getCode())) {
                //成功
                resultDTO.setResult(0);
            } else {
                //失败
                resultDTO.setResult(2);
                //如果连接超时的错误信息不为空，则返回错误提示
                if (StringUtils.isNotEmpty(errMess))
                    resultDTO.setMessage(errMess);
            }
        } else {
            resultDTO.setMessage("证件号、证件类型、账户全名都不能为空");
        }
        resultDTO.setVersion(version);
        resultDTO.setSendTime(DateUtil.getTime());
        log.info("4.9风险测评返回结果：{}", resultDTO);
        return JSONObject.toJSONString(resultDTO);
    }
}

