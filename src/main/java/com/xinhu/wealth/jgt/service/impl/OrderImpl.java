package com.xinhu.wealth.jgt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.PassWord;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.constants.ResultEntity;
import com.xinhu.wealth.jgt.mapper.JgtBonusMapper;
import com.xinhu.wealth.jgt.mapper.JgtPositionShareMapper;
import com.xinhu.wealth.jgt.mapper.JgtTradingConfirmMapper;
import com.xinhu.wealth.jgt.mapper.OrderMapper;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrderYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.ShareQuerysDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.TradingResDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.*;
import com.xinhu.wealth.jgt.model.entity.JgtBonusEntity;
import com.xinhu.wealth.jgt.model.entity.JgtPositionShareEntity;
import com.xinhu.wealth.jgt.model.entity.JgtTradingConfirmEntity;
import com.xinhu.wealth.jgt.model.entity.OrderEntity;
import com.xinhu.wealth.jgt.service.OrderService;
import com.xinhu.wealth.jgt.util.DateUtil;
import com.xinhu.wealth.jgt.util.HttpSend;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import com.xinhu.wealth.jgt.util.YYSignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wyt
 * @data 2020/3/5 15:38
 * 此类包含：
 * 1、2.1下单接口的所有，认购、申购、赎回、基金转换、分红方式修改、撤单操作
 * 2、2.2确认
 * 3、2.3分红查询
 * 4、2.4持仓查询
 */
@Service
@Slf4j
public class OrderImpl implements OrderService {
    @Autowired
    RestemplateUtil restemplateUtil;
    @Autowired
    OrderMapper orderMapper;
    @Resource
    JgtTradingConfirmMapper jgtTradingConfirmMapper;
    @Resource
    JgtBonusMapper jgtBonusMapper;
    @Resource
    JgtPositionShareMapper jgtPositionShareMapper;

    /**
     * 认购
     * @param participationDTO 存放入参
     * @return
     */
    @Override
    public ResultDTO querySubscriber(ParticipationDTO participationDTO) {
        log.info("认购用户入参：{}",participationDTO);
        Gson gson = new Gson();
        //返参
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        OrderYunYiDTO orderYunYiDTO = new OrderYunYiDTO();
        try {
            orderYunYiDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<OrderYunYiDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("认购的body转换异常：{}",e);
            resultDTO.setMessage("入参格式错误");
            return resultDTO;
        }
        //返回云毅实体类
        ConfirmDTO confirmDTO = new ConfirmDTO();
        //验证是否已存在
        OrderEntity checkOrder = new OrderEntity();
        checkOrder.setOSerialno(orderYunYiDTO.getOserialno());
        boolean boolCount = orderMapper.selectCount(checkOrder) == 0;
        if (boolCount) {
            RenGouZXDTO renGouZXDTO = new RenGouZXDTO();
            //接收密码
            String password = "";
            try {
                renGouZXDTO = gson.fromJson(JSONObject.toJSONString(orderYunYiDTO), new TypeToken<RenGouZXDTO>() {
                }.getType());
                //解密密码
                try {
                    password = PassWord.decrypt(renGouZXDTO.getPassword());
                    renGouZXDTO.setPassword(password);
                    log.info("认购解密后密码：{}",password);
                } catch (Exception e) {
                    log.info("密码解密异常");
                }
            } catch (JsonSyntaxException e) {
                log.info("认购的转换为植信入参异常：{}",e);
                resultDTO.setMessage("认购植信入参格式错误");
                return resultDTO;
            }
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.T501.getUrl();
            //调用接口
            resultDTO = HttpSend.doGetResult(url, renGouZXDTO);
            //获取植信data
            try {
                confirmDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<ConfirmDTO>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("认购返回结果转换异常：{}",e);
                resultDTO.setMessage("认购结果返回错误：{}");
                return resultDTO;
            }
            //如果成功
            if ("200".equals(resultDTO.getCode())) {
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                log.info("认购data结果：{}", resultDTO.getData() + "");
                orderEntity.setZXResult(resultDTO.getData() + "");
                String busintype = orderYunYiDTO.getBusintype();
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(busintype);
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("1");//成功
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                orderMapper.insert(orderEntity);
                log.info("认购入库结果:{}", orderEntity);
                resultDTO.setResult(0);
            } else {
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                log.info("认购data结果：{}", resultDTO.getData() + "");
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(participationDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行，2代表失败
                orderEntity.setZXStatus("0");//失败
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                orderMapper.insert(orderEntity);
                log.info("认购入库结果:{}", orderEntity);
                resultDTO.setResult(2);
            }
        } else {
            resultDTO.setResult(2);
            resultDTO.setMessage("请勿重复下单");
        }
        confirmDTO.setOserialno(orderYunYiDTO.getOserialno());
        resultDTO.setData(confirmDTO);
        resultDTO.setRowSize(null);
        resultDTO.setCode(null);
        resultDTO.setTotalCount(null);
        resultDTO.setPageIndex(null);
        resultDTO.setPageSize(null);
        resultDTO.setSendTime(DateUtil.getTime());
        resultDTO.setVersion(participationDTO.getVersion());
        String json = JSONObject.toJSONString(resultDTO);
        String now = DateUtil.getTime();
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("认购返回结果:{}", resultDTO);
        return resultDTO;
    }

    /**
     * 申购，（返回值与认购相同，使用一个entity）
     *
     * @param participationDTO 存放入参
     * @return
     */
    @Override
    public ResultDTO queryPurchase(ParticipationDTO participationDTO) {
        log.info("申购用户入参：{}",participationDTO);
        Gson gson = new Gson();
        //返参
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        OrderYunYiDTO orderYunYiDTO = new OrderYunYiDTO();
        try {
            orderYunYiDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<OrderYunYiDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("申购入参转换异常：{}",e);
            resultDTO.setMessage("入参格式不正确");
            return resultDTO;
        }
        //云毅返回值实体类
        ConfirmDTO confirmDTO = new ConfirmDTO();
        //验证是否已存在
        OrderEntity checkOrder = new OrderEntity();
        checkOrder.setOSerialno(orderYunYiDTO.getOserialno());
        boolean boolCount = orderMapper.selectCount(checkOrder) == 0;
        //没有相同数据
        if (boolCount) {
            //入参转换
            ShenGouZXDTO shenGouZXDTO = new ShenGouZXDTO();
            //接收密码
            String password="";
            try {
                shenGouZXDTO = gson.fromJson(JSONObject.toJSONString(orderYunYiDTO), new TypeToken<ShenGouZXDTO>() {
                }.getType());
                //解密密码
                try {
                    password = PassWord.decrypt(shenGouZXDTO.getPassword());
                    shenGouZXDTO.setPassword(password);
                    log.info("申购解密后密码：{}",password);
                } catch (Exception e) {
                    log.info("密码解密异常");
                }
            } catch (JsonSyntaxException e) {
                log.info("申购转换为植信接口入参异常：{}",e);
                resultDTO.setMessage("申购植信入参格式不正确");
                return resultDTO;
            }
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.T502.getUrl();
            //restemplate方式调用接口
            resultDTO = HttpSend.doGetResult(url, shenGouZXDTO);
            //ConfirmDTO
            //获取植信data
            try {
                confirmDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<ConfirmDTO>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("申购结果转换异常：{}",e);
                resultDTO.setMessage("申购结果返回错误");
                return resultDTO;
            }
            //如果成功
            if ("200".equals(resultDTO.getCode())) {
                //插入数据库实体类
                OrderEntity orderEntity = new OrderEntity();
                log.info("申购data结果：{}", resultDTO.getData() + "");
                //返回结果赋值
                orderEntity.setZXResult(resultDTO.getData() + "");
                //获取业务号
                String busintype = orderYunYiDTO.getBusintype();
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(busintype);
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("1");//成功
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());

                //插入数据库
                orderMapper.insertSelective(orderEntity);
                resultDTO.setResult(0);
            } else {
                //失败插入数据库，带有错误信息提示
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                log.info("申购data结果：{}", resultDTO.getData() + "");
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(participationDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("0");//失败
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                orderMapper.insert(orderEntity);
                log.info("申购入库结果:{}", orderEntity);
                resultDTO.setResult(2);
            }
            log.info("申购返回结果:{}", resultDTO);
        } else {
            resultDTO.setMessage("请勿重复下单");
            resultDTO.setResult(2);
        }
        confirmDTO.setOserialno(orderYunYiDTO.getOserialno());
        resultDTO.setData(confirmDTO);
        resultDTO.setRowSize(null);
        resultDTO.setCode(null);
        resultDTO.setTotalCount(null);
        resultDTO.setPageIndex(null);
        resultDTO.setPageSize(null);
        resultDTO.setSendTime(DateUtil.getTime());
        resultDTO.setVersion(participationDTO.getVersion());
        String json = JSONObject.toJSONString(resultDTO);
        String now = DateUtil.getTime();
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("申购返回结果:{}", resultDTO);
        return resultDTO;
    }

    /**
     * 赎回
     *
     * @param participationDTO 存放入参
     * @return
     */
    @Override
    public ResultDTO queryRedemption(ParticipationDTO participationDTO) {
        log.info("赎回用户入参：{}",participationDTO);
        Gson gson = new Gson();
        //返参
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        OrderYunYiDTO orderYunYiDTO = new OrderYunYiDTO();
        try {
            orderYunYiDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<OrderYunYiDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("赎回入参转换异常：{}",e);
            resultDTO.setMessage("赎回入参格式错误");
            return resultDTO;
        }
        //返回云毅实体类
        ConfirmDTO confirmDTO = new ConfirmDTO();
        //验证是否已存在
        OrderEntity checkOrder = new OrderEntity();
        checkOrder.setOSerialno(orderYunYiDTO.getOserialno());
        boolean boolCount = orderMapper.selectCount(checkOrder) == 0;
        if (boolCount) {
            //入参转换
            RedeemZXDTO redeemZXDTO = new RedeemZXDTO();
            //接收密码
            String password="";
            try {
                redeemZXDTO = gson.fromJson(JSONObject.toJSONString(orderYunYiDTO), new TypeToken<RedeemZXDTO>() {
                }.getType());
                //解密密码
                try {
                    password = PassWord.decrypt(redeemZXDTO.getPassword());
                    redeemZXDTO.setPassword(password);
                    log.info("解密后密码：{}",password);
                } catch (Exception e) {
                    log.info("密码解密异常");
                }
            } catch (JsonSyntaxException e) {
                log.info("赎回植信入参转换错误：{}",e);
                resultDTO.setMessage("赎回入参格式错误");
                return resultDTO;
            }
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.T513.getUrl();
            //restemplate方式调用接口
            resultDTO = HttpSend.doGetResult(url, redeemZXDTO);

            //获取植信data
            try {
                confirmDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<ConfirmDTO>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("赎回返回值转换异常：{}",e);
                resultDTO.setMessage("赎回结果转换异常：{}");
                return resultDTO;
            }
            if ("200".equals(resultDTO.getCode())) {
                OrderEntity orderEntity = new OrderEntity();
                log.info("赎回data结果：{}", resultDTO.getData() + "");
                //返回结果赋值
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(orderYunYiDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("1");//成功
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                //插入数据库
                orderMapper.insertSelective(orderEntity);
                resultDTO.setResult(0);
            } else {
                //下单失败
                //失败插入数据库，带有错误信息提示
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                log.info("赎回data结果：{}", resultDTO.getData() + "");
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(participationDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("0");//失败
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                orderMapper.insert(orderEntity);
                log.info("认购入库结果:{}", orderEntity);
                resultDTO.setResult(2);
            }

            log.info("赎回返回结果：{}", resultDTO.toString());
        } else {
            resultDTO.setMessage("请勿重复下单");
            resultDTO.setResult(2);
        }
        confirmDTO.setOserialno(orderYunYiDTO.getOserialno());
        resultDTO.setData(confirmDTO);
        resultDTO.setRowSize(null);
        resultDTO.setCode(null);
        resultDTO.setTotalCount(null);
        resultDTO.setPageIndex(null);
        resultDTO.setPageSize(null);
        resultDTO.setSendTime(DateUtil.getTime());
        resultDTO.setVersion(participationDTO.getVersion());
        String json = JSONObject.toJSONString(resultDTO);
        String now = DateUtil.getTime();
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("赎回返回结果:{}", resultDTO);
        return resultDTO;
    }

    /**
     * 基金转换
     *
     * @param participationDTO 存放入参
     * @return
     */
    @Override
    public ResultDTO modifyFunChange(ParticipationDTO participationDTO) {
        log.info("基金转换用户入参：{}",participationDTO);
        Gson gson = new Gson();
        //返参
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        OrderYunYiDTO orderYunYiDTO = new OrderYunYiDTO();
        try {
            orderYunYiDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<OrderYunYiDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("基金转换入参转换异常");
            resultDTO.setMessage("基金转换入参格式错误");
            return resultDTO;
        }
        //云毅返回结果实体类
        ConfirmDTO confirmDTO = new ConfirmDTO();
        //验证是否已存在
        OrderEntity checkOrder = new OrderEntity();
        checkOrder.setOSerialno(orderYunYiDTO.getOserialno());
        boolean boolCount = orderMapper.selectCount(checkOrder) == 0;
        if (boolCount) {
            //入参转换
            FunChangZXDTO funChangZXDTO = new FunChangZXDTO();
            //接收密码
            String password="";
            try {
                funChangZXDTO = gson.fromJson(JSONObject.toJSONString(orderYunYiDTO), new TypeToken<FunChangZXDTO>() {
                }.getType());
                //解密密码
                try {
                    password = PassWord.decrypt(funChangZXDTO.getPassword());
                    funChangZXDTO.setPassword(password);
                    log.info("基金转换解密后密码：{}",password);
                } catch (Exception e) {
                    log.info("密码解密异常");
                }
            } catch (JsonSyntaxException e) {
                log.info("基金转换植信入参转换错误：{}",e);
                resultDTO.setMessage("基金转换入参格式错误");
                return resultDTO;
            }
            //基金转换
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.T518.getUrl();
            //restemplate方式调用接口
            resultDTO = HttpSend.doGetResult(url, funChangZXDTO);
            //ConfirmDTO
            //获取植信data
            try {
                confirmDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<ConfirmDTO>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("基金转换结果转换异常：{}",e);
                resultDTO.setMessage("基金转换结果返回错误");
                return resultDTO;
            }
            if ("200".equals(resultDTO.getCode())) {
                log.info("基金转换data结果：{}", resultDTO.getData() + "");
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(orderYunYiDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("1");//成功
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                //插入数据库
                orderMapper.insertSelective(orderEntity);
                resultDTO.setResult(0);
            } else {
                //下单失败
                //失败插入数据库，带有错误信息提示
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                log.info("基金转换data结果：{}", resultDTO.getData() + "");
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(participationDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("0");//失败
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                orderMapper.insert(orderEntity);
                log.info("基金转换入库结果:{}", orderEntity);
                resultDTO.setResult(2);
            }
            log.info("基金转换返回结果:{}", resultDTO);
        } else {
            resultDTO.setMessage("请勿重复下单");
            resultDTO.setResult(2);
        }
        confirmDTO.setOserialno(orderYunYiDTO.getOserialno());
        resultDTO.setData(confirmDTO);
        resultDTO.setRowSize(null);
        resultDTO.setCode(null);
        resultDTO.setTotalCount(null);
        resultDTO.setPageIndex(null);
        resultDTO.setPageSize(null);
        resultDTO.setSendTime(DateUtil.getTime());
        resultDTO.setVersion(participationDTO.getVersion());
        String json = JSONObject.toJSONString(resultDTO);
        String now = DateUtil.getTime();
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("基金转换返回结果:{}", resultDTO);
        return resultDTO;
    }

    /**
     * 修改分红方式
     * @param participationDTO 存放入参
     * @return
     */
    @Override
    public ResultDTO modifyBonusType(ParticipationDTO participationDTO) {
        log.info("修改分红方式用户入参：{}",participationDTO);
        Gson gson = new Gson();
        //返参
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        OrderYunYiDTO orderYunYiDTO = new OrderYunYiDTO();
        try {
            orderYunYiDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<OrderYunYiDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("修改分红方式入参转换异常：{}",e);
            resultDTO.setMessage("修改分红方式入参格式错误");
            return resultDTO;
        }
        //返回云毅实体类
        ConfirmDTO confirmDTO = new ConfirmDTO();
        //验证是否已存在
        OrderEntity checkOrder = new OrderEntity();
        checkOrder.setOSerialno(orderYunYiDTO.getOserialno());
        boolean boolCount = orderMapper.selectCount(checkOrder) == 0;
        if (boolCount) {
            //入参转换
            BousChangeZXDTO bousChangeZXDTO = new BousChangeZXDTO();
            //接收密码
            String password="";
            try {
                bousChangeZXDTO = gson.fromJson(JSONObject.toJSONString(orderYunYiDTO), new TypeToken<BousChangeZXDTO>() {
                }.getType());
                //解密密码
                try {
                    password = PassWord.decrypt(bousChangeZXDTO.getPassword());
                    bousChangeZXDTO.setPassword(password);
                    log.info("修改分红方式解密后密码：{}",password);
                } catch (Exception e) {
                    log.info("密码解密异常");
                }
            } catch (JsonSyntaxException e) {
                log.info("修改分红方式植信入参转换异常：{}",e);
                resultDTO.setMessage("修改分红方式，入参格式错误");
                return resultDTO;
            }
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.T514.getUrl();
            //restemplate方式调用接口
            resultDTO = HttpSend.doGetResult(url, bousChangeZXDTO);
            //ConfirmDTO
            //获取植信data
            try {
                confirmDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<ConfirmDTO>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("修改分红方式返回结果转换异常：{}",e);
                resultDTO.setMessage("修改分红方式结果返回错误");
                return resultDTO;
            }
            if ("200".equals(resultDTO.getCode())) {
                OrderEntity orderEntity = new OrderEntity();
                log.info("分红data结果：{}", resultDTO.getData() + "");
                //返回结果赋值
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(orderYunYiDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("1");//成功
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                //插入数据库
                orderMapper.insertSelective(orderEntity);
                resultDTO.setResult(0);
            } else {
                //下单失败
                //失败插入数据库，带有错误信息提示
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                log.info("分红修改data结果：{}", resultDTO.getData() + "");
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(participationDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("0");//失败
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                orderMapper.insert(orderEntity);
                log.info("分红修改入库结果:{}", orderEntity);
                resultDTO.setResult(2);
            }
            log.info("分红修改返回结果:{}", resultDTO);
        } else {
            resultDTO.setMessage("请勿重复下单");
            resultDTO.setResult(2);
        }
        confirmDTO.setOserialno(orderYunYiDTO.getOserialno());
        resultDTO.setData(confirmDTO);
        resultDTO.setRowSize(null);
        resultDTO.setCode(null);
        resultDTO.setTotalCount(null);
        resultDTO.setPageIndex(null);
        resultDTO.setPageSize(null);
        resultDTO.setSendTime(DateUtil.getTime());
        resultDTO.setVersion(participationDTO.getVersion());
        String json = JSONObject.toJSONString(resultDTO);
        String now = DateUtil.getTime();
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("基金转换返回结果:{}", resultDTO);
        return resultDTO;
    }

    /**
     * 撤单
     *
     * @param  participationDTO 存放入参
     * @return
     */
    @Override
    public ResultDTO queryCancellations(ParticipationDTO participationDTO) {
        log.info("撤单用户入参：{}",participationDTO);
        Gson gson = new Gson();
        //返参
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        OrderYunYiDTO orderYunYiDTO = new OrderYunYiDTO();
        try {
            orderYunYiDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<OrderYunYiDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("撤单入参转换异常：{}",e);
            resultDTO.setMessage("撤单入参格式错误");
            return resultDTO;
        }
        //返回云毅实体类
        ConfirmDTO confirmDTO = new ConfirmDTO();
        //验证是否已存在
        OrderEntity checkOrder = new OrderEntity();
        checkOrder.setOSerialno(orderYunYiDTO.getOserialno());
        boolean boolCount = orderMapper.selectCount(checkOrder) == 0;
        if (boolCount) {
            //入参转换
            OrderCDZXDTO orderCDZXDTO = new OrderCDZXDTO();
            //接收密码
            String password="";
            try {
                orderCDZXDTO = gson.fromJson(JSONObject.toJSONString(orderYunYiDTO), new TypeToken<OrderCDZXDTO>() {
                }.getType());
                //解密密码
                try {
                    password = PassWord.decrypt(orderCDZXDTO.getPassword());
                    orderCDZXDTO.setPassword(password);
                    log.info("撤单解密后密码：{}",password);
                } catch (Exception e) {
                    log.info("密码解密异常");
                }
            } catch (JsonSyntaxException e) {
                log.info("撤单植信接口入参转换异常：{}",e);
                resultDTO.setMessage("撤单入参格式错误");
                return resultDTO;
            }
            //拼接链接(每个接口不一样)
            String url = PathZXConstants.T516.getUrl();
            //restemplate方式调用接口
            resultDTO = HttpSend.doGetResult(url, orderCDZXDTO);
            //ConfirmDTO
            //获取植信data
            try {
                confirmDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<ConfirmDTO>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                log.info("撤单返回结果转换异常：{}",e);
                resultDTO.setMessage("撤单返回结果错误");
                return resultDTO;
            }

            if ("200".equals(resultDTO.getCode())) {
                OrderEntity orderEntity = new OrderEntity();
                log.info("撤单data结果：{}", resultDTO.getData() + "");
                //返回结果赋值
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(orderYunYiDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("1");//成功
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                //插入数据库
                orderMapper.insertSelective(orderEntity);
                resultDTO.setResult(0);
            } else {
                //下单失败
                //失败插入数据库，带有错误信息提示
                OrderEntity orderEntity = new OrderEntity();
                //返回结果赋值
                log.info("撤单data结果：{}", resultDTO.getData() + "");
                orderEntity.setZXResult(resultDTO.getData() + "");
                //业务号，很重要，唯一标识
                orderEntity.setBunissCode(participationDTO.getBusintype());
                //插入数据库
                orderEntity.setAllotNo(confirmDTO.getAppserialno());
                orderEntity.setPassWord(orderYunYiDTO.getPassword());
                //插入数据库数据
                orderEntity.setBody(participationDTO.getBody());
                //如果申报编号一样不允许插入数据库
                orderEntity.setSendTime(participationDTO.getSendTime());
                orderEntity.setStatus(0);//0代表未执行
                orderEntity.setZXStatus("0");//失败
                orderEntity.setVersion(participationDTO.getVersion());
                orderEntity.setProssCode(participationDTO.getProcessCode());
                orderEntity.setPageIndex(participationDTO.getPageIndex());
                orderEntity.setCreateTime(LocalDateTime.now());
                orderEntity.setOSerialno(orderYunYiDTO.getOserialno());
                orderMapper.insert(orderEntity);
                log.info("撤单入库结果:{}", orderEntity);
                resultDTO.setResult(2);
            }
        } else {
            resultDTO.setMessage("请勿重复下单");
            resultDTO.setResult(2);
        }
        confirmDTO.setOserialno(orderYunYiDTO.getOserialno());
        resultDTO.setData(confirmDTO);
        resultDTO.setRowSize(null);
        resultDTO.setCode(null);
        resultDTO.setTotalCount(null);
        resultDTO.setPageIndex(null);
        resultDTO.setPageSize(null);
        resultDTO.setSendTime(DateUtil.getTime());
        resultDTO.setVersion(participationDTO.getVersion());
        String json = JSONObject.toJSONString(resultDTO);
        String now = DateUtil.getTime();
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("撤单返回结果:{}", resultDTO);
        return resultDTO;
    }

    /**
     * 2.1下单接口
     *
     * @param participationDTO 入参实体类
     * @return
     */
    @Override
    public ResultDTO queryOrder(ParticipationDTO participationDTO) {
        log.info("2.1下单用户入参：{}",participationDTO);
        ResultDTO resultDTO = new ResultDTO();
        log.info("下单接口的业务号：{}", participationDTO.getProcessCode());
        log.info("下单接口的功能号：{}", participationDTO.getBusintype());
        if ("2001".equals(participationDTO.getProcessCode())) {
            //认购
            if ("020".equals(participationDTO.getBusintype())) {
                //认购
                log.info("认购开始");
                resultDTO = querySubscriber(participationDTO);
                log.info("认购结束，结果：{}", resultDTO);
            } else if ("022".equals(participationDTO.getBusintype())) {
                //申购
                log.info("申购开始");
                resultDTO = queryPurchase(participationDTO);
                log.info("申购结束，结果：{}", resultDTO);
            } else if ("024".equals(participationDTO.getBusintype())) {
                //赎回
                log.info("赎回开始");
                resultDTO = queryRedemption(participationDTO);
                log.info("赎回结束，结果：{}", resultDTO);
            } else if ("029".equals(participationDTO.getBusintype())) {
                //分红修改
                log.info("分红修改开始");
                resultDTO = modifyBonusType(participationDTO);
                log.info("分红修改结束，结果：{}", resultDTO);
            } else if ("036".equals(participationDTO.getBusintype())) {
                //基金转换
                log.info("基金转换开始");
                resultDTO = modifyFunChange(participationDTO);
                log.info("基金转换结束，结果：{}", resultDTO);
            } else if ("052".equals(participationDTO.getBusintype())) {
                //撤单
                log.info("撤单开始");
                resultDTO = queryCancellations(participationDTO);
                log.info("撤单结束，结果：{}", resultDTO);
            }
        }
        return resultDTO;
    }

    /**
     * 2.2确认------S416 交易成交查询
     *
     * @param participationDTO 入参实体类
     * @return
     */
    @Override
    public ResultDTO queryTradeConfirm(ParticipationDTO participationDTO) {
        log.info("2.2确认查询入参：{}",participationDTO);
        ResultDTO resultDTO = new ResultDTO();
        TradingResDTO tradingResDTO = null;
        try {
            //入参转换
            Gson gson = new Gson();
            tradingResDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<TradingResDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("2.2确认入参转换异常：{}", e);
            resultDTO.setMessage("入参格式错误");
            return resultDTO;
        }
        //2.2确认
        JgtTradingConfirmEntity jgtTradingConfirmEntity = new JgtTradingConfirmEntity();
        //根据日期查询
        jgtTradingConfirmEntity.setTransactionCfmDate(tradingResDTO.getFdate());
        //分页入参
        int pageIndex=Integer.parseInt(participationDTO.getPageIndex());
        int rowSize=participationDTO.getRowSize();
        Page page = PageHelper.startPage(pageIndex, rowSize);
        //根据日期分页查询数据库中，交易类确认结果
        List<JgtTradingConfirmEntity> jgtTradingConfirmEntityList = jgtTradingConfirmMapper.select(jgtTradingConfirmEntity);
        log.info("2.2确认数据库查询结果：{}", jgtTradingConfirmEntityList);
        List<TradingConfirmDTO> tradingConfirmDTOList = new ArrayList<>();
        for (JgtTradingConfirmEntity jgtTradingConfirmEntityResult : jgtTradingConfirmEntityList) {
            TradingConfirmDTO tradingConfirmDTO = new TradingConfirmDTO();
            //将查询出来的结果赋值给返回云毅的实体类
            BeanUtils.copyProperties(jgtTradingConfirmEntityResult, tradingConfirmDTO);
            log.info("2.2数据转换后结果：{}",tradingConfirmDTO);
            //通过交易账号获取S403接口的分红方式，
            //1、获取S403接口的链接
            String K406Url= PathZXConstants.S403.getUrl();
            ResultDTO resultS403 = restemplateUtil.postRest("", K406Url +"&trade_acco="+tradingConfirmDTO.getTransactionAccountID()+ "&fund_code=" + tradingConfirmDTO.getFundCode(), null);
            String data=resultS403.getData()+"";
            boolean isTrue="null".equals(data);
            if (StringUtils.isNotEmpty(data) && !isTrue){
                String shareQuerys = JSONObject.parseObject(data).getString("shareQuerys");
                List<ShareQuerysDTO> shareQuerysDTOList = JSONObject.parseArray(shareQuerys, ShareQuerysDTO.class);
                if (shareQuerysDTOList!=null&&shareQuerysDTOList.size()>0){
                    String auto_buy = shareQuerysDTOList.get(0).getAuto_buy();
                    //赋值默认分红方式
                    tradingConfirmDTO.setDefDividendMethod(auto_buy);
                }
            }
            if (!"0000".equals(tradingConfirmDTO.getErrorDetail())){
                tradingConfirmDTO.setReturnCode("9999");
            }
            if (!"120".equals(jgtTradingConfirmEntity.getBusinessCode())){
                tradingConfirmDTO.setBusinessFinishFlag("1");
            }
            if (tradingResDTO!=null){
                tradingConfirmDTO.setOserialno(tradingResDTO.getOserialno());
            }
            tradingConfirmDTOList.add(tradingConfirmDTO);
        }
        resultDTO.setTotalCount((int) page.getTotal());
        resultDTO.setRowSize(rowSize);
        //返回给云毅
        resultDTO.setData(JSONObject.toJSON(tradingConfirmDTOList));
        resultDTO.setVersion(participationDTO.getVersion());
        resultDTO.setPageIndex(pageIndex);
        String now = DateUtil.getTime();
        resultDTO.setSendTime(now);
        resultDTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
        resultDTO.setMessage(ResultEntity.YUNYISUCCESS.getMsg());
        String json = JSONObject.toJSONString(resultDTO);
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("2.2确认返回结果：{}", resultDTO.toString());
        return resultDTO;
    }

    /**
     * 2.3分红-----S420-分红查询
     *
     * @param participationDTO 入参实体类
     * @return
     */
    @Override
    public ResultDTO queryBonusSearch(ParticipationDTO participationDTO) {
        log.info("2.3分红查询入参：{}",participationDTO);
        //返回参数接受
        ResultDTO resultDTO = new ResultDTO();
        //入参转换
        TradingResDTO tradingResDTO = null;
        try {
            Gson gson = new Gson();
            tradingResDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<TradingResDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("2.3分红入参转换异常：{}", e);
            resultDTO.setMessage("入参格式错误");
            return resultDTO;
        }
        // 2.3分红
        JgtBonusEntity jgtBonusEntity = new JgtBonusEntity();
        jgtBonusEntity.setTransactionCfmDate(tradingResDTO.getFdate());
        //赋值分页
        int pageIndex=Integer.parseInt(participationDTO.getPageIndex());
        int rowSize=participationDTO.getRowSize();
        Page page = PageHelper.startPage(pageIndex, rowSize);
        //根据日期分页查询，数据库中，分红的数据
        List<JgtBonusEntity> jgtBonusEntityList = jgtBonusMapper.select(jgtBonusEntity);
        log.info("2.3分红数据库查询结果：{}", jgtBonusEntityList);
        List<BonusDTO> bonusDTOList = new ArrayList<>();
        for (JgtBonusEntity jgtBonusEntityResult : jgtBonusEntityList) {
            BonusDTO bonusDTO = new BonusDTO();
            //将数据库数据，赋值给返回云毅的实体类
            BeanUtils.copyProperties(jgtBonusEntityResult, bonusDTO);
            bonusDTOList.add(bonusDTO);
        }
        resultDTO.setTotalCount((int) page.getTotal());
        resultDTO.setRowSize(rowSize);
        //返回给云毅
        resultDTO.setData(JSONObject.toJSON(bonusDTOList));
        resultDTO.setVersion(participationDTO.getVersion());
        resultDTO.setPageIndex(pageIndex);
        String now = DateUtil.getTime();
        resultDTO.setSendTime(now);
        resultDTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
        resultDTO.setMessage(ResultEntity.YUNYISUCCESS.getMsg());
        String json = JSONObject.toJSONString(resultDTO);
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("2.3分红返回结果：{}", resultDTO.toString());
        return resultDTO;
    }

    /**
     * 2.4持仓-----S403份额查询
     *
     * @param participationDTO 入参实体类
     * @return
     */
    @Override
    public ResultDTO queryShare(ParticipationDTO participationDTO) {
        log.info("2.4持仓查询入参：{}",participationDTO);
        //返回参数接受
        ResultDTO resultDTO = new ResultDTO();
        TradingResDTO tradingResDTO = null;
        try {
            Gson gson = new Gson();
            tradingResDTO = gson.fromJson(participationDTO.getBody(), new TypeToken<TradingResDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.info("2.4持仓入参转换异常：{}", e);
            resultDTO.setMessage("入参格式错误");
            return resultDTO;
        }
        //2.4持仓
        JgtPositionShareEntity jgtPositionShareEntity = new JgtPositionShareEntity();
        jgtPositionShareEntity.setTransactionCfmDate(tradingResDTO.getFdate());
        jgtPositionShareEntity.setAccIncome(null);
        jgtPositionShareEntity.setByIncome(null);
        jgtPositionShareEntity.setDayIncome(null);
        jgtPositionShareEntity.setHoldingIncome(null);
        //赋值分页
        int pageIndex=Integer.parseInt(participationDTO.getPageIndex());
        int rowSize=participationDTO.getRowSize();
        Page page = PageHelper.startPage(pageIndex, rowSize);
        //根据日期分页查询数据库持仓数据
        log.info("2.4持仓查询数据库入参：{}",jgtPositionShareEntity);
        List<JgtPositionShareEntity> jgtPositionShareEntityList = jgtPositionShareMapper.select(jgtPositionShareEntity);
        log.info("2.4持仓数据库查询结果：{}", jgtPositionShareEntityList);
        List<PositionShareDTO> positionShareDTOList = new ArrayList<>();
        for (JgtPositionShareEntity jgtPositionShareEntityResult : jgtPositionShareEntityList) {
            PositionShareDTO positionShareDTO = new PositionShareDTO();
            String accountStatus = jgtPositionShareEntityResult.getAccountStatus();
            jgtPositionShareEntityResult.setAccountStatus(accountStatus);
            //将已确认的持仓数据库，赋值给返回云毅的实体类
            BeanUtils.copyProperties(jgtPositionShareEntityResult, positionShareDTO);
            positionShareDTOList.add(positionShareDTO);
        }
        resultDTO.setTotalCount((int) page.getTotal());
        resultDTO.setRowSize(rowSize);
        //返回给云毅
        resultDTO.setData(JSONObject.toJSON(positionShareDTOList));
        resultDTO.setVersion(participationDTO.getVersion());
        resultDTO.setPageIndex(pageIndex);
        String now = DateUtil.getTime();
        resultDTO.setSendTime(now);
        resultDTO.setResult(ResultEntity.YUNYISUCCESS.getCode());
        resultDTO.setMessage(ResultEntity.YUNYISUCCESS.getMsg());
        String json = JSONObject.toJSONString(resultDTO);
        String sign = YYSignUtil.sign(now + "&" + json, JGTConstant.YY_PRI_KEY);
        resultDTO.setYYSign(sign);
        log.info("2.4持仓返回结果：{}", resultDTO.toString());
        return resultDTO;
    }

    /**
     * 交易类查询接口
     *
     * @param participationDTO 入参实体类
     * @return
     */
    @Override
    public ResultDTO queryOrderSearch(ParticipationDTO participationDTO) {
        //接收返回值
        ResultDTO resultDTO = new ResultDTO();
        log.info("交易类查询接口的业务号：{}", participationDTO.getProcessCode());
        if ("2002".equals(participationDTO.getProcessCode())) {
            //2.2确认
            log.info("2.2确认");
            resultDTO = queryTradeConfirm(participationDTO);
            log.info("2.2确认结束，结果：{}", resultDTO);
        } else if ("2003".equals(participationDTO.getProcessCode())) {
            // 2.3分红
            log.info("2.3分红");
            resultDTO = queryBonusSearch(participationDTO);
            log.info("2.3分红结束，结果：{}", resultDTO);
        } else if ("2004".equals(participationDTO.getProcessCode())) {
            //2.4持仓
            log.info("2.4持仓");
            resultDTO = queryShare(participationDTO);
            log.info("2.4持仓结束，结果：{}", resultDTO);
        }
        return resultDTO;
    }
}
