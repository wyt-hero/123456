package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.PassWord;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.mapper.CancleAccMapper;
import com.xinhu.wealth.jgt.mapper.ChangeAccMapper;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.*;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.*;
import com.xinhu.wealth.jgt.model.entity.CancleAccEntity;
import com.xinhu.wealth.jgt.model.entity.ChangeAccEntity;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.service.AccountRepordService;
import com.xinhu.wealth.jgt.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 账户类申报serviceImpl
 *
 * @author panjie
 * @date 2020/4/28 18:40
 */
@Service
@Slf4j
public class AccountRepordServiceImpl implements AccountRepordService {

    /**
     * 远程调用连接工具类
     */
    @Autowired
    private RestemplateUtil restTemplateUtil;


    /**
     * 修改账户mapper
     */
    @Resource
    private ChangeAccMapper changeAccMapper;

    /**
     * 交易账户处理mapper
     */
    @Autowired
    private PollingMapper pollingMapper;

    /**
     * 取消账户mapper
     */
    @Autowired
    private CancleAccMapper cancleAccMapper;


    /**
     * @param body               云毅body
     * @param version            版本
     * @param processCode        功能代码
     * @param sing               签名
     * @param sendTime           发送时间
     * @param institutionCode    销售渠道
     * @param institutionMarking 机构标识
     * @return 返回给云毅结果
     * <p>
     * 账户类申报接口
     */
    @Override
    public String accountRepord(String body, String version, String processCode, String sing, String sendTime, String institutionCode, String institutionMarking) {
        log.info("账户类申报接口body={},Version={}, ProcessCode={},SingText={},SendTime={}, InstitutionCode={}, InstitutionMarking={}", body, version, processCode, sing, sendTime, institutionCode, institutionMarking);
        //验证签名
        boolean bool = YYSignUtil.isSign(sendTime, body, sing);
        //TODO 验签成功
        bool = true;
        //接收返回值
        ResultDTO resultDTO = new ResultDTO();
        OrgnInDTO orgnInDTO = new OrgnInDTO();

        if (bool) {
            Gson gson = new Gson();
            //4001 交易账户处理
            if ("4001".equals(processCode)) {
                String businType = "";
                if (StringUtils.isNotEmpty(body) && !"{}".equals(body) && !"null".equals(body)) {
                    log.info("***********通过非空验证body值为：{}", body);
                    //body不为空
                    businType = JSONObject.parseObject(body).getString("Busintype");
                } else {
                    //body为空
                    resultDTO.setMessage("body为空，请输入开户参数");
                    resultDTO.setResult(2);
                }
                //todo json_id 如何获取？
                //todo CRS信息处理
                CRSZXInfoDTO crszxInfoDTO = CRSInfo(body);
                //交易账户开立
                if ("001".equals(businType)) {
                    log.info("进入开户：{}", body);
                    //入参数据转换
                    OrgnInZXDTO orgnInZXDTO = new OrgnInZXDTO();
                    orgnInZXDTO = JSONObject.parseObject(body, OrgnInZXDTO.class);
                    //返回给云毅data数据
                    TradingInstitutionsOpenDTO tradingInstitutionsOpenDTO = new TradingInstitutionsOpenDTO();
                    //查询本地数据库是否有相同的流水号
                    PollingEntity checkPolling = new PollingEntity();
                    checkPolling.setOSerialno(orgnInZXDTO.getOSerialno());
                    log.info("开户流水号：{}", orgnInZXDTO.getOSerialno());
                    int countOSerialno = pollingMapper.selectCount(checkPolling);
                    log.info("开户流水号是否查到：{}", countOSerialno);
                    //如果count == 0 说明本地数据库不存在相同流水号，则可以保存到数据库
                    if (countOSerialno == 0) {
                        if (!"1".equals(orgnInZXDTO.getSuitablyInvestorsFlg())) {
                            orgnInZXDTO.setSuitablyInvestorsType("");
                            orgnInZXDTO.setAccessQualificationFlg("");
                            orgnInZXDTO.setSuitablyInvestorsValidDate("");
                        }
                        //开户数据转换
                        String json = JSONObject.toJSONString(orgnInZXDTO);
                        orgnInDTO = gson.fromJson(json, new TypeToken<OrgnInDTO>() {
                        }.getType());
                        //接收银行名
                        String bankName = "";
                        if (StringUtils.isEmpty(orgnInDTO.getBank_name())) {
                            //获取银行编号查询银行名称url
                            String P401 = PathZXConstants.P401.getUrl() + "&caption=银行编号";
                            BankNameP401DTO bankNameP401DTO = new BankNameP401DTO();
                            //查询出所有的银行编号及所对应的银行名称
                            resultDTO = restTemplateUtil.postRest(JSONObject.toJSONString(bankNameP401DTO), P401, null);
                            BankNameResDTO bankNameResDTO = new BankNameResDTO();
                            String results = JSONObject.parseObject(resultDTO.getData().toString()).getString("results");
                            //获取所有银行编号和银行名称
                            List<BankNameResDTO> bankNameResDTOList = gson.fromJson(results, new TypeToken<List<BankNameResDTO>>() {
                            }.getType());
                            //根据body中传入的银行编号找出所对应的银行名称
                            for (int i = 0; i < bankNameResDTOList.size(); i++) {
                                //判断body中的银行编号和集合中的银行编号是否相等
                                String bankCode = bankNameResDTOList.get(i).getKeyvalue();
                                if (bankCode.equals(orgnInZXDTO.getBankCode())) {
                                    bankNameResDTO.setCaption(bankNameResDTOList.get(i).getCaption());
                                    break;
                                }
                            }
                            //获取银行名称
                            bankName = bankNameResDTO.getCaption();
                            //赋值银行名称
                            orgnInDTO.setBank_name(bankName);
                        } else {
                            bankName = orgnInDTO.getBank_name();
                        }
                        //执行开户逻辑
                        //解密密码
                        String password = "";
                        try {
                            password = PassWord.decrypt(orgnInDTO.getPassword());
                            orgnInDTO.setPassword(password);
                        } catch (Exception e) {
                            log.info("密码解密异常");
                        }
                        //赋值经办人联系电话OperatorTel
                        if (StringUtils.isNotEmpty(orgnInZXDTO.getOperatorTel())) {
                            orgnInDTO.setContact_tel(orgnInZXDTO.getOperatorTel());
                        }
                        log.info("开户密码为：{}", orgnInDTO.getPassword());
                        //拼接链接(每个接口不一样)
                        String url = PathZXConstants.C503.getUrl();
                        log.info("开户入参为：{}", orgnInDTO);
                        //将lianAddress拼接
                        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(orgnInDTO));
                        String lianAddress = jsonObject.getString("lianAddress");
                        jsonObject.remove("lianAddress");
                        jsonObject.put("address", lianAddress);
                        resultDTO = HttpSend.doGetResult(url, jsonObject);
                        //获取植信返回结果
                        String code = JSONObject.parseObject(resultDTO.getData() + "").getString("code");
                        //判断用户开户是否成功
                        if (!"ETS-5BP0000".equals(code)) {
                            //植信开户失败  保存到数据库
                            PollingEntity pollingEntity = new PollingEntity();
                            pollingEntity.setStatu(3);//开户失败，状态为3
                            log.info("开户密文密码：{}", orgnInZXDTO.getPassword());
                            pollingEntity.setPassWord(orgnInZXDTO.getPassword());
                            Map<String, String> map = new HashMap<>();
                            map.put("入参为：" + body, resultDTO.getMessage());
                            //errorMessage返回植信开户的报错
                            pollingEntity.setAccStatus(2);
                            pollingEntity.setCreateTime(LocalDateTime.now());
                            pollingEntity.setBody(body);
                            pollingEntity.setOSerialno(orgnInZXDTO.getOSerialno());
                            pollingEntity.setAccErrorMessage(map.toString());
                            //保存到本地数据库
                            pollingMapper.insertSelective(pollingEntity);
                            resultDTO.setResult(2);//接收失败
                        } else {//植信开户成功  保存到本地数据库 再返回给云毅结果
                            //获取植信data
                            InstitutionsAccountDTO institutionsAccountDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<InstitutionsAccountDTO>() {
                            }.getType());
                            //植信返回值接收，返回给云毅的参数
                            String allotNo = institutionsAccountDTO.getAllot_no();//申请编号
                            String oserialno = orgnInZXDTO.getOSerialno();//O3 流水号

                            tradingInstitutionsOpenDTO.setOserialno(oserialno);
                            tradingInstitutionsOpenDTO.setAppserialno(allotNo);
                            String accResult = JSONObject.toJSONString(institutionsAccountDTO);
                            String tradeAcco = institutionsAccountDTO.getTrade_acco();//交易账号
                            //将开户成功记录保存到本地数据库
                            log.info("开户密文密码：{}", orgnInZXDTO.getPassword());
                            accountResultPolling(orgnInZXDTO.getPassword(), oserialno, allotNo, tradeAcco, accResult, 1, bankName, body, sing, processCode, version, sendTime);
                            resultDTO.setData(tradingInstitutionsOpenDTO);
                            //resultDTO.setMessage("开户中");
                            resultDTO.setResult(0);//接收成功
                            //todo 获取client_id
                            String clinet_id = "";
                            if (institutionsAccountDTO != null) {
                                clinet_id = institutionsAccountDTO.getClient_id();
                            }
                            //todo CRS赋值客户号
                            crszxInfoDTO.setClient_id(clinet_id);
                            //获取操作crs结果
                            ResultDTO resultCRS = queryCrsRes(crszxInfoDTO);
                            if (resultCRS != null && resultCRS.getResult() == 2) {
                                resultCRS.setResult(0);
                                resultCRS.setData(tradingInstitutionsOpenDTO);
                                resultCRS.setMessage("开户成功！" + resultCRS.getMessage());
                                log.info("CRS报错，最终返回结果：{}", resultCRS);
                                //1、根据流水号查询id，
                                String oSerialno = orgnInZXDTO.getOSerialno();
                                modifyPolling(oSerialno);
                                return JSONObject.toJSONString(resultCRS);
                            }
                            //1、根据流水号查询id，
                            String oSerialno = orgnInZXDTO.getOSerialno();
                            modifyPolling(oSerialno);
                        }
                    } else {//数据库存在相同流水号
                        //根据流水号查询开户结果
                        PollingEntity pollingEntity = new PollingEntity();
                        pollingEntity.setOSerialno(orgnInZXDTO.getOSerialno());
                        //pollingEntity.setStatu(0);//0代表未执行回写操作
                        List<PollingEntity> pollingEntityList = pollingMapper.select(pollingEntity);
                        for (int i = 0; i < pollingEntityList.size(); i++) {
                            pollingEntity = pollingEntityList.get(i);
                            int accStatus = pollingEntity.getAccStatus();
                            if (accStatus == 1) {//此流水号已经开户成功
                                //获取植信返回结果
                                String accResult = pollingEntity.getAccResult();
                                //获取申请编号
                                String allotNo = JSONObject.parseObject(accResult).getString("allot_no");
                                //赋值申请编号
                                tradingInstitutionsOpenDTO.setAppserialno(allotNo);//申请编号
                                tradingInstitutionsOpenDTO.setOserialno(orgnInZXDTO.getOSerialno());
                                //resultDTO.setMessage("开户成功");
                                resultDTO.setResult(0);
                                //todo 获取client_id
                                String client_id = JSONObject.parseObject(pollingEntity.getAccResult()).getString("client_id");
                                //todo 赋值crs信息
                                crszxInfoDTO.setClient_id(client_id);
                                //获取操作crs结果
                                ResultDTO resultCRS = queryCrsRes(crszxInfoDTO);
                                if (resultCRS != null && resultCRS.getResult() == 2) {
                                    //修改入参body
                                    resultCRS.setResult(0);
                                    log.info("CRS报错，最终返回结果：{}", resultCRS);
                                    //1、根据流水号查询id，
                                    String oSerialno = orgnInZXDTO.getOSerialno();
                                    modifyPolling(oSerialno);
                                    return JSONObject.toJSONString(resultCRS);
                                }
                            } else {
                                resultDTO.setMessage(pollingEntity.getAccErrorMessage());
                                resultDTO.setResult(2);
                            }
                        }
                    }
                    resultDTO.setData(tradingInstitutionsOpenDTO);
                } else if ("003".equals(businType)) {//修改交易账户
                    log.info("进入修改账户：{}");
                    //入参数据转换
                    OrgnInZXDTO orgnInZXDTO = new OrgnInZXDTO();
                    orgnInZXDTO = JSONObject.parseObject(body, OrgnInZXDTO.class);
                    //根据流水号判断数据库是否存在
                    ChangeAccEntity check = new ChangeAccEntity();
                    check.setOserialno(orgnInZXDTO.getOSerialno());
                    int selectCount = changeAccMapper.selectCount(check);
                    if (selectCount == 0) {
                        //修改用户数据转换
                        String json = JSONObject.toJSONString(orgnInZXDTO);
                        orgnInDTO = gson.fromJson(json, new TypeToken<OrgnInDTO>() {
                        }.getType());
                        //解密密码
                        String password = "";
                        try {
                            password = PassWord.decrypt(orgnInDTO.getPassword());
                            orgnInDTO.setPassword(password);
                            log.info("修改账户入参密码：{}", password);
                        } catch (Exception e) {
                            log.info("密码解密异常");
                            ResultDTO resultDTO1 = new ResultDTO();
                            resultDTO1.setResult(2);
                            resultDTO1.setMessage("请核证入参密码后重试！");
                            return resultDTO1.toString();
                        }
                        JSONObject jsonObject = new JSONObject();
                        JSONObject jsonObjectDTO = JSONObject.parseObject(JSONObject.toJSONString(orgnInDTO));
                        //处理空值（暂时不用）
                        //for (String key : jsonObjectDTO.keySet()) {
                            //key值不等于check_send_way和recipients_name
                            //boolean isTrue="recipients_name".equals(key);
                            //if (StringUtils.isNotEmpty(jsonObjectDTO.getString(key))) {
                                //jsonObject.put(key, jsonObjectDTO.getString(key));
                            //} else {
                               // jsonObject.put(key, "<NULL>");
                            //}
                       // }
                        //特殊处理
                        //jsonObject.put("pub_professional_investor_valid_date","<NULL>");
                        log.info("修改客户信息，入参：{}", jsonObjectDTO);
                        String lianAddress = jsonObject.getString("lianAddress");
                        jsonObject.remove("lianAddress");
                        jsonObject.put("address", lianAddress);
                        String url = PathZXConstants.C502.getUrl();
                        resultDTO = HttpSend.doGetResult(url, jsonObjectDTO);
                        TradingInstitutionsOpenDTO tradingInstitutionsOpenDTO = new TradingInstitutionsOpenDTO();
                        tradingInstitutionsOpenDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<TradingInstitutionsOpenDTO>() {
                        }.getType());
                        //获取植信返回结果code
                        String code = JSONObject.parseObject(resultDTO.getData() + "").getString("code");
                        if ("ETS-5BP0000".equals(code)) {//植信修改成功
                            tradingInstitutionsOpenDTO.setOserialno(orgnInZXDTO.getOSerialno());//O3 流水号
                            String transactionAccountID = orgnInZXDTO.getTransactionAccountID();
                            if (StringUtils.isNotEmpty(transactionAccountID)) {
                                //将修改客户信息的入参修改数据库
                                PollingEntity pollingEntity = new PollingEntity();
                                //接收修改数据
                                PollingEntity updatePolling = new PollingEntity();
                                //根据交易账号查询
                                pollingEntity.setTransactionAccountID(transactionAccountID);
                                updatePolling = pollingMapper.selectOne(pollingEntity);
                                if (updatePolling != null) {
                                    log.info("修改账户，修改前body数据：{}", updatePolling.getBody());
                                    updatePolling.setBeforeBody(updatePolling.getBody());
                                    //更新body
                                    updatePolling.setBody(JSONObject.toJSONString(orgnInZXDTO));
                                    updatePolling.setUpdateTime(LocalDateTime.now());
                                    updatePolling.setPassWord(orgnInZXDTO.getPassword());
                                    //todo 置空确认查询字段
                                    log.info("修改用户信息，密码密文：{}", orgnInZXDTO.getPassword());
                                    log.info("修改用户信息成功后，更新body的数据：{}", updatePolling);
                                    pollingMapper.updateByPrimaryKeySelective(updatePolling);
                                }
                                //插入修改账户数据库，
                                ChangeAccEntity changeAccEntity = new ChangeAccEntity();
                                changeAccEntity.setOserialno(orgnInZXDTO.getOSerialno());
                                changeAccEntity.setBusinType(businType);
                                changeAccEntity.setBody(body);
                                changeAccEntity.setYYStatus("0");//0代表未执行
                                changeAccEntity.setZXStatus("1");//1修改成功
                                changeAccEntity.setZXResult(resultDTO.getData() + "");
                                changeAccEntity.setCreateTime(LocalDateTime.now().toString());
                                changeAccEntity.setUpdateTime(LocalDateTime.now().toString());
                                log.info("修改账户信息，插入数据库入参：{}", changeAccEntity.toString());
                                changeAccMapper.insertSelective(changeAccEntity);
                                resultDTO.setResult(0);
                                resultDTO.setData(tradingInstitutionsOpenDTO);
                                log.info("修改账户返回结果:{}", resultDTO);
                                //todo 获取client_id
                                //1、获取交易账号
                                String transactionAccountIDCRS = orgnInZXDTO.getTransactionAccountID();
                                //2、根据交易账号获取client_id
                                PollingEntity getClinetId = new PollingEntity();
                                getClinetId.setTransactionAccountID(transactionAccountIDCRS);
                                getClinetId = pollingMapper.selectOne(getClinetId);
                                String client_id = "";
                                if (getClinetId != null && StringUtils.isNotEmpty(getClinetId.getAccResult())) {
                                    client_id = JSONObject.parseObject(getClinetId.getAccResult()).getString("client_id");
                                }
                                //todo 修改CRS信息
                                crszxInfoDTO = queryCRSEditInfo(orgnInZXDTO.getTransactionAccountID(), orgnInZXDTO.getTAAccountID(), crszxInfoDTO);
                                crszxInfoDTO.setClient_id(client_id);
                                //todo 转换为jsonstring
                                log.info("修改CRS信息实体类：{}", crszxInfoDTO);
                                String crsInfoJsonEdit = changDTOForString(crszxInfoDTO);
                                //todo CRS操作
                                String c517UrlEdit = PathZXConstants.C517.getUrl() + "&usertype=0";
                                CRSDTO crsdto = new CRSDTO();
                                crsdto.setCrsinfo_json(crsInfoJsonEdit);
                                resultDTO = HttpSend.doGetResult(c517UrlEdit, crsdto);
                                //resultDTO = restTemplateUtil.postRest("", c517UrlEdit, null);
                                if (resultDTO != null) {
                                    String codeCRS = JSONObject.parseObject(resultDTO.getData() + "").getString("code");
                                    if (!"ETS-5BP0000".equals(codeCRS)) {
                                        String message = JSONObject.parseObject(resultDTO.getData() + "").getString("message");
                                        resultDTO.setMessage("修改账户信息成功！" + message);
                                        resultDTO.setData(tradingInstitutionsOpenDTO);
                                        resultDTO.setResult(0);
                                        log.info("修改账户返回结果:{}", resultDTO);
                                        modifyChange(orgnInZXDTO.getOSerialno());
                                        return JSONObject.toJSONString(resultDTO);
                                    }
                                }
                            }
                            modifyChange(orgnInZXDTO.getOSerialno());
                            resultDTO.setResult(0);
                            resultDTO.setData(tradingInstitutionsOpenDTO);
                        } else {//植信修改失败
                            //插入修改账户数据库，
                            ChangeAccEntity changeAccEntity = new ChangeAccEntity();
                            changeAccEntity.setOserialno(orgnInZXDTO.getOSerialno());
                            changeAccEntity.setBusinType(businType);
                            changeAccEntity.setBody(body);
                            changeAccEntity.setYYStatus("2");//0代表未执行
                            changeAccEntity.setZXStatus("0");//0修改失败
                            changeAccEntity.setChangeErrorMsg(resultDTO.getMessage());
                            changeAccEntity.setCreateTime(LocalDateTime.now().toString());
                            log.info("修改账户信息失败，插入数据库入参：{}", changeAccEntity.toString());
                            changeAccMapper.insertSelective(changeAccEntity);
                            resultDTO.setResult(2);
                            resultDTO.setMessage(resultDTO.getMessage());
                            resultDTO.setData(new HashMap<>());
                        }
                    } else {
                        resultDTO.setResult(2);
                        resultDTO.setMessage("请勿重复修改");
                    }
                }
                //返回给云毅
                resultDTO.setRowSize(null);
                resultDTO.setCode(null);
                resultDTO.setTotalCount(null);
                resultDTO.setPageIndex(null);
                resultDTO.setPageSize(null);
                resultDTO.setSendTime(DateUtil.getTime());
                String sign = YYSignUtil.sign(sendTime + "&" + resultDTO.getData(), JGTConstant.YY_PRI_KEY);
                resultDTO.setYYSign(sign);
                resultDTO.setVersion(version);
                log.info("修改账户返回结果:{}", resultDTO);
            } else if ("4002".equals(processCode)) {
                //4.2基金账户销户
                return yunYi4002(body, version, sendTime, processCode, sing);
            }
            return JSONObject.toJSONString(resultDTO);
        } else {
            ResultDTO4002 dto4002 = new ResultDTO4002();
            dto4002.setVersion(version);
            dto4002.setSendTime(DateUtil.getTime());
            dto4002.setResult(ResultEntity.YUNYISINGERROR.getCode());
            dto4002.setMessage(ResultEntity.YUNYISINGERROR.getMsg());
            TradingInstitutionsCloseDTO tradingInstitutionsCloseDTO = new TradingInstitutionsCloseDTO();
            JSONObject jsonObject = JSON.parseObject(body);
            tradingInstitutionsCloseDTO.setOserialno(jsonObject.get("OSerialno").toString());
            tradingInstitutionsCloseDTO.setAppserialno("");
            dto4002.setData(tradingInstitutionsCloseDTO);
            dto4002.setYYSign("");
            return JSONObject.toJSONString(dto4002);
        }

    }

    public void modifyPolling(String oSerialno) {
        //1、根据流水号查询id，
        PollingEntity pollingEntity = new PollingEntity();
        pollingEntity.setOSerialno(oSerialno);
        pollingEntity = pollingMapper.selectOne(pollingEntity);
        pollingEntity.setCRSStatus("1");
        //2、根据id修改CRSSTatus状态
        pollingMapper.updateByPrimaryKeySelective(pollingEntity);
    }

    public void modifyChange(String oSerialno) {
        ChangeAccEntity changeAccEntity = new ChangeAccEntity();
        changeAccEntity.setOserialno(oSerialno);
        changeAccEntity = changeAccMapper.selectOne(changeAccEntity);
        changeAccEntity.setCRSStatus("1");
        changeAccMapper.updateByPrimaryKeySelective(changeAccEntity);
    }

    //public ResultDTO yunYi001(){}
    /**
     * 将开户成功记录保存到本地数据库
     *
     * @param body        主参数
     * @param sing        加签参数
     * @param processCode 业务代码
     * @param version     版本号
     * @param sendTime    发送时间
     * @return
     */
    private ResultDTO accountResultPolling(String pwd, String oserialno, String allotNo, String tradeAcco, String accResult, int accStatus, String bankName, String body, String sing, String processCode, String version, String sendTime) {
        PollingEntity pollingEntity = new PollingEntity();
        pollingEntity.setBankName(bankName);
        pollingEntity.setBody(body);
        pollingEntity.setSing(sing);
        pollingEntity.setPassWord(pwd);
        pollingEntity.setOSerialno(oserialno);
        pollingEntity.setTaCode(allotNo);
        pollingEntity.setTransactionAccountID(tradeAcco);
        pollingEntity.setAccStatus(accStatus);
        pollingEntity.setAccResult(accResult);
        pollingEntity.setProcessCode(processCode);
        pollingEntity.setVersion(version);
        pollingEntity.setSendTime(sendTime);
        pollingEntity.setCreateTime(LocalDateTime.now());
        pollingEntity.setIsFile(0);//未执行文件上传
        pollingEntity.setType(2);//账户类查询
        pollingEntity.setStatu(0);//0未执行回写，1已执行
        log.info("添加入参：{}", pollingEntity.toString());
        int count = pollingMapper.insertSelective(pollingEntity);
        ResultDTO resultDTO = new ResultDTO();
        if (count > 0) {
            resultDTO.setCode("200");
            resultDTO.setMessage(ResultEntity.SUCCESS.getMsg());
        }
        return resultDTO;
    }

    /**
     * 对应云逸4002
     * 4.2基金账户销户
     *
     * @param body        云毅入参
     * @param version     版本号
     * @param sendTime    发送时间
     * @param processCode 功能代码
     * @param sing        签名
     * @return
     */
    private String yunYi4002(String body, String version, String sendTime, String processCode, String sing) {

        //todo CRS信息处理
        CRSZXInfoDTO crszxInfoDTO = CRSInfo(body);
        String time = DateUtil.getTime();
        Gson gson = new Gson();
        ResultDTO resultDTO = new ResultDTO();
        // 调用C513需要密码
        ResultDTO4002 dto4002 = new ResultDTO4002();
        //数据转换
        OrgnLogoutDTO orgnLogoutDTO = new OrgnLogoutDTO();
        //存储基金帐号
        String TAACount = "";
        String pwd = "";
        String oserialno = "";//流水号
        try {
            orgnLogoutDTO = gson.fromJson(body, new TypeToken<OrgnLogoutDTO>() {
            }.getType());
            TAACount = orgnLogoutDTO.getTAAccountID();
            pwd = orgnLogoutDTO.getPassword();
            //解密密码
            String password = "";
            try {
                pwd = PassWord.decrypt(pwd);
            } catch (Exception e) {
                log.info("密码解密异常");
            }
            oserialno = orgnLogoutDTO.getOSerialno();
        } catch (Exception e) {
            log.error("4002转换异常", e);
        }
        //根据流水号查重
        CancleAccEntity checkOserialno = new CancleAccEntity();
        checkOserialno.setOSerialno(oserialno);
        int count = cancleAccMapper.selectCount(checkOserialno);
        if (count == 0) {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("ta_acco", TAACount);
            map.put("password", pwd);
            //4.2基金账户销户  C513机构基金账户销户
            resultDTO = restTemplateUtil.getRestV2(map, PathZXConstants.C513.getUrl());
            dto4002.setVersion(version);
            dto4002.setSendTime(time);
            if (resultDTO.getResult() == ResultEntity.YUNYISUCCESS.getCode()) {//植信销户成功
                //获取植信data
                dto4002.setResult(resultDTO.getResult());
                dto4002.setMessage(ResultEntity.YUNYISUCCESS.getMsg());
            } else {//植信销户失败
                dto4002.setResult(ResultEntity.YUNYIERROR.getCode());
                dto4002.setMessage(resultDTO.getMessage());
            }
            TradingInstitutionsCloseDTO tradingInstitutionsCloseDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<TradingInstitutionsCloseDTO>() {
            }.getType());
            tradingInstitutionsCloseDTO.setOserialno(orgnLogoutDTO.getOSerialno());
            dto4002.setData(tradingInstitutionsCloseDTO);
            try {
                //判断是否有申请编号
                if (StringUtils.isNotEmpty(tradingInstitutionsCloseDTO.getAppserialno())) {//根据是否返回申请编号判断是否销户成功
                    //赋值 插入数据库
                    CancleAccEntity cancelAccEntity = new CancleAccEntity();
                    cancelAccEntity.setBody(body);
                    cancelAccEntity.setProssCode(processCode);
                    cancelAccEntity.setSendTime(sendTime);
                    cancelAccEntity.setSing(sing);
                    cancelAccEntity.setOSerialno(orgnLogoutDTO.getOSerialno());
                    cancelAccEntity.setVersion(version);
                    cancelAccEntity.setStatus("0");//0代表未执行回写，1已执行
                    cancelAccEntity.setCreateTime(LocalDateTime.now());
                    cancelAccEntity.setZXResult(resultDTO.getData() + "");
                    if (StringUtils.isNotEmpty(TAACount)) {
                        cancelAccEntity.setTAACount(TAACount);
                    }
                    //验证是否可以添加
                    CancleAccEntity check = new CancleAccEntity();
                    check.setStatus("1");//1代表已执行回写
                    check.setTAACount(TAACount);
                    List<CancleAccEntity> cancelAccEntityList = cancleAccMapper.select(check);
                    //如果基金帐号相等，且销户状态为1，则说明销户成功，不可重复销户
                    if (CollectionUtils.isEmpty(cancelAccEntityList)) {
                        cancleAccMapper.insertSelective(cancelAccEntity);
                    }
                    //销户成功，CRS删除开始
                    //todo 删除CRS信息
                    //todo 1、获取client_id
                    crszxInfoDTO = queryCRSEditInfo("", TAACount, crszxInfoDTO);
                    //todo 转换为jsonstring
                    String crsInfoJson = changDTOForString(crszxInfoDTO);
                    //todo 获取删除CRSurl
                    String c518Url = PathZXConstants.C518.getUrl() + "&usertype=0&crsinfo_json=" + crsInfoJson;
                    resultDTO = restTemplateUtil.postRest("", c518Url, null);
                    if (resultDTO != null) {
                        String code = JSONObject.parseObject(resultDTO.getData() + "").getString("code");
                        if (!"ETS-5BP0000".equals(code)) {
                            String message = JSONObject.parseObject(resultDTO.getData() + "").getString("message");
                            resultDTO.setMessage("销户成功！" + message);
                            resultDTO.setResult(0);
                            resultDTO.setData(tradingInstitutionsCloseDTO);
                            return JSONObject.toJSONString(resultDTO);
                        }
                    }
                    //销户成功，CRS删除结束
                } else {//植信销户失败
                    CancleAccEntity error = new CancleAccEntity();
                    error.setError_message(resultDTO.getMessage());
                    error.setBody(body);
                    error.setOSerialno(orgnLogoutDTO.getOSerialno());
                    error.setProssCode(processCode);
                    error.setSendTime(sendTime);
                    error.setSing(sing);
                    error.setVersion(version);
                    error.setStatus("0");//0未执行，1已执行 2 表示销户失败
                    error.setCreateTime(LocalDateTime.now());
                    error.setZXResult(resultDTO.getData() + "");
                    if (StringUtils.isNotEmpty(TAACount)) {
                        error.setTAACount(TAACount);
                    }
                    CancleAccEntity check = new CancleAccEntity();
                    check.setTAACount(TAACount);
                    check.setStatus("2");
                    List<CancleAccEntity> select = cancleAccMapper.select(check);
                    //如果基金账户不为空，且状态为2，则说明销户失败，且数据库有相同字段，则不添加数据库
                    if (select.size() == 0) {
                        cancleAccMapper.insertSelective(error);
                    } else {
                        //根据基金帐号和销户状态查询出数据库有同样数据，则更新错误信息，更新修改时间
                        CancleAccEntity cancelAccEntity = new CancleAccEntity();
                        cancelAccEntity.setId(select.get(0).getId());
                        cancelAccEntity.setUpdate_time(LocalDateTime.now());
                        cancelAccEntity.setError_message(resultDTO.getMessage());
                        cancleAccMapper.updateByPrimaryKeySelective(cancelAccEntity);
                    }
                    log.info("销户错误信息：{}", resultDTO.getMessage());
                }
            } catch (Exception e) {
                log.error("4006入库异常", e);
            }
        } else {
            //流水号重复
            dto4002.setResult(2);
            dto4002.setMessage("请勿重复提交");
        }
        //转成string，准备加签
        String json = JSONObject.toJSONString(dto4002);
        //加签
        String sign = YYSignUtil.sign(time + "&" + json, JGTConstant.YY_PRI_KEY);
        dto4002.setYYSign(sign);
        log.info("4002返回云毅结果:{}", dto4002);
        return JSONObject.toJSONString(dto4002);
    }

    /**
     * 入参CRS转换
     *
     * @param body
     * @return
     */
    private CRSZXInfoDTO CRSInfo(String body) {
        Gson gson = new Gson();
        String crsInfo = JSONObject.parseObject(body).getString("CRSInfo");
        //数据转换
        CRSZXInfoDTO crszxInfoDTO = gson.fromJson(crsInfo, CRSZXInfoDTO.class);
        log.info("CRS信息转换实体类结果：{}", crszxInfoDTO);
        return crszxInfoDTO;
    }

    /**
     * 转换CRS为JSOn
     *
     * @param crszxInfoDTO
     * @return
     */
    public String changDTOForString(CRSZXInfoDTO crszxInfoDTO) {
        //将对象转换为json
        Gson gson = new Gson();
        String jsonstr = JSONObject.toJSONString(crszxInfoDTO);
        log.info("CRS转换为json结果：{}", jsonstr);
        try {
            byte[] bytes = Base64.encodeBase64(jsonstr.getBytes("UTF-8"));
            log.info("CRS转换为bytes结果:{}", bytes);
            String crsinfoJson = new String(bytes);
            log.info("4.10将对象base64加密转换结果：{}", crsinfoJson);
            return crsinfoJson;
        } catch (UnsupportedEncodingException e) {
            log.error("4.10将对象base64加密转换异常：{}", e);
            return "9999";
        }
    }

    /**
     * 获取json_id
     *
     * @param transactionAccountID
     * @param taAccountID
     * @return
     */
    public String queryEditCRS(String transactionAccountID, String taAccountID) {
        //todo 获取交易账号或ta账号
        boolean tranNotEmpty = StringUtils.isNotEmpty(transactionAccountID);
        boolean taNotEmpty = StringUtils.isNotEmpty(taAccountID);
        //查询数据库
        //todo 根据交易账号或ta账号获取client_id，
        String json_id = "";
        PollingEntity queryClientId = new PollingEntity();
        if (tranNotEmpty) {
            queryClientId.setTransactionAccountID(transactionAccountID);
        } else if (taNotEmpty) {
            queryClientId.setTaCode(taAccountID);
        }
        queryClientId = pollingMapper.selectOne(queryClientId);
        if (queryClientId != null) {
            String accResult = queryClientId.getAccResult();
            String client_id = JSONObject.parseObject(accResult).getString("client_id");
            //todo 根据client_id获取json_id，json_belong_id JSON对象所属编号,json_id JSON对象自身编号
            String c519Url = PathZXConstants.C519.getUrl() + "&client_id=" + client_id;
            log.info("C519URL:{}", c519Url);
            ResultDTO resultDTO = restTemplateUtil.postRest("", c519Url, null);
            log.info("C519接口返回结果：{}", resultDTO);
            if (resultDTO != null) {
                //crs信息
                String crslist = JSONObject.parseObject(resultDTO.getData() + "").getString("crslist");
                if (JSONArray.parseArray(crslist).size() > 0) {
                    //获取json_id JSON对象自身编号，
                    String crsOne = JSONArray.parseArray(crslist).get(0) + "";
                    json_id = JSONObject.parseObject(crsOne).getString("json_id");
                }
            }

        } else {
            //表示获取client_id 失败
            return "9998";
        }
        return json_id;
    }

    /**
     * 赋值CRSZXInfoDTO中的json_id
     *
     * @param transactionAccountID 交易账号
     * @param taAccountID          ta账号
     * @param crszxInfoDTO         CRS信息实体类
     * @return
     */
    public CRSZXInfoDTO queryCRSEditInfo(String transactionAccountID, String taAccountID, CRSZXInfoDTO crszxInfoDTO) {
        String crsJsonId = queryEditCRS(transactionAccountID, taAccountID);
        String json_id = "";
        log.info("crsJsonId为：{}", crsJsonId);
        json_id = crsJsonId;
        if (crszxInfoDTO != null) {
            crszxInfoDTO.setJson_id(json_id);
        }
        return crszxInfoDTO;
    }

    public ResultDTO queryCrsRes(CRSZXInfoDTO crszxInfoDTO) {
        //todo 转换为jsonstring
        String crsInfoJson = changDTOForString(crszxInfoDTO);
        //todo CRS操作
        //String c517Url = PathZXConstants.C517.getUrl() + "&usertype=0&crsinfo_json=" + crsInfoJson;
        String c517Url = PathZXConstants.C517.getUrl() + "&usertype=0";
        log.info("CRS操作URL：{}", c517Url);
        //todo 实体类
        CRSDTO crsdto = new CRSDTO();
        crsdto.setCrsinfo_json(crsInfoJson);
        String crsJson = JSONObject.toJSONString(crsdto);
        //resultDTO = restTemplateUtil.postRest(crsJson, c517Url, null);
        ResultDTO resultDTO = HttpSend.doGetResult(c517Url, crsdto);
        if (resultDTO != null) {
            String crsCode = JSONObject.parseObject(resultDTO.getData() + "").getString("code");
            if (!"ETS-5BP0000".equals(crsCode)) {
                String message = JSONObject.parseObject(resultDTO.getData() + "").getString("message");
                resultDTO.setData(null);
                resultDTO.setCode("9999");
                if (StringUtils.isNotEmpty(message)) {
                    resultDTO.setMessage("CRS信息错误：" + message);
                } else {
                    resultDTO.setMessage("CRS信息错误：" + crsCode);
                }

                resultDTO.setResult(2);
            }
        }
        log.info("CRS错误信息返回结果：{}", resultDTO);
        return resultDTO;
    }
}
