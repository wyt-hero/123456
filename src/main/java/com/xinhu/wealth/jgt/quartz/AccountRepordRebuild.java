package com.xinhu.wealth.jgt.quartz;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.mapper.CancleAccMapper;
import com.xinhu.wealth.jgt.mapper.ChangeAccMapper;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.AccResYunYiDTO;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.OrgnLogoutDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.OrgnInZXDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.model.entity.CancleAccEntity;
import com.xinhu.wealth.jgt.model.entity.ChangeAccEntity;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.service.SearchFundResultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 账户类申报定时任务
 *
 * @author panjie
 * @date 2020/4/29 09:40
 */
@Slf4j
@Component
public class AccountRepordRebuild {

    /**
     * 交易账户处理mapper
     */
    @Autowired
    private PollingMapper pollingMapper;

    /**
     * 查询开户结果service
     */
    @Autowired
    private SearchFundResultService searchFundResultService;

    /**
     * 修改账户mapper
     */
    @Autowired
    private ChangeAccMapper changeAccMapper;

    /**
     * 取消账户mapper
     */
    @Autowired
    private CancleAccMapper cancleAccMapper;

    //接收返回值
    ResultDTO resultDTO = new ResultDTO();

    //获取时间
    String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

    /**
     * 账户结果回写
     */
    public void accountResultBack() {
        log.info("账户结果回写begin：{}");
        Gson gson = new Gson();
        AccResYunYiDTO accResYunYiDTO = new AccResYunYiDTO();
        //获取body数据
        OrgnInZXDTO orgnInZXDTO = new OrgnInZXDTO();
        //查数据库数据中未回写给云毅的记录
        PollingEntity pollingEntity = new PollingEntity();
        //账户结果回写
        pollingEntity.setType(2);
        //未执行0代表未执行，1代表已执行
        pollingEntity.setStatu(0);
        pollingEntity.setCRSStatus("1");
        //获取数据库里的账户结果回写传参
        List<PollingEntity> pollingEntityList = pollingMapper.select(pollingEntity);
        if (pollingEntityList != null && pollingEntityList.size() > 0) {
            //循环取出交易类查询传参
            for (int i = 0; i < pollingEntityList.size(); i++) {
                PollingEntity entity = pollingEntityList.get(i);
                resultDTO.setData(entity.getAccResult());
                //获取body数据
                orgnInZXDTO = JSONObject.parseObject(entity.getBody(), OrgnInZXDTO.class);
                if (entity.getAccStatus() == 1) {//开户成功
                    //调用链接SZ50_1 回写开户成功结果给云毅
                    ResultDTO rest = searchFundResultService.queryAccResult(resultDTO, accResYunYiDTO, orgnInZXDTO, time);
                    //获取回写给云毅的开户结果
                    String errorNo = "";
                    if (StringUtils.isNotEmpty(rest.getData() + "")) {
                        errorNo = JSONObject.parseObject(rest.getData() + "").getString("errorNo");
                    }
                    //如果回写结果不为空
                    if ("0".equals(errorNo)) {
                        //entity.setIsFile(0);//未上传文件
                        entity.setAccStatus(1);//开户成功
                        entity.setStatu(1);//回写成功，1代表已执行，回写成功，0代表未执行回写
                        entity.setUpdateTime(LocalDateTime.now());
                        //更新数据库数据
                        pollingMapper.updateByPrimaryKeySelective(entity);
                    } else if (errorNo == null || "null".equals(errorNo)) {
                        log.info("账户回写返回值为空：{}", errorNo);
                    } else if ("1".equals(errorNo)) {
                        //回写失败，将status改为2
                        String errorInfo = JSONObject.parseObject(rest.getData() + "").getString("errorInfo");
                        //entity.setIsFile(0);//未上传文件
                        entity.setStatu(2);//回写失败
                        entity.setUpdateTime(LocalDateTime.now());
                        entity.setYYErrMes(errorInfo);
                        //更新数据库数据
                        pollingMapper.updateByPrimaryKeySelective(entity);
                    }
                }
            }
        }
    }


    /**
     * 修改账户回写
     */
    public void ChangeAccWriteBack() {
        log.info("修改账户回写begin：{}");
        //获取body数据
        OrgnInZXDTO orgnInZXDTO = new OrgnInZXDTO();
        //修改账户入参
        AccResYunYiDTO accResYunYiDTO = new AccResYunYiDTO();
        //1、查询数据库中未回写的数据
        ChangeAccEntity changeAccEntity = new ChangeAccEntity();
        changeAccEntity.setYYStatus("0");//0代表未执行回写的数据
        changeAccEntity.setCRSStatus("1");
        List<ChangeAccEntity> changeAccEntityList = changeAccMapper.select(changeAccEntity);
        log.info("修改账户未执行回写数据：{}", changeAccEntityList);
        if (changeAccEntityList != null && changeAccEntityList.size() > 0) {
            for (int i = 0; i < changeAccEntityList.size(); i++) {
                changeAccEntity = changeAccEntityList.get(i);
                //获取body数据
                orgnInZXDTO = JSONObject.parseObject(changeAccEntity.getBody(), OrgnInZXDTO.class);
                //赋值回写入参
                String accResult = changeAccEntity.getZXResult();
                if (StringUtils.isNotEmpty(accResult)) {
                    String allotNo = JSONObject.parseObject(accResult).getString("allot_no");
                    // 根据交易账号调用植信S445接口做账户申请查询
                    log.info("根据allotNo查询修改结果S445：{}", allotNo);
                    //调用链接SZ50_1 回写开户成功结果给云毅
                    ResultDTO rest = searchFundResultService.queryErrorNo(accResYunYiDTO, orgnInZXDTO, allotNo, time);
                    //获取修改结果中的errorNo
                    String errorNo = "";
                    if (StringUtils.isNotEmpty(rest.getData() + "")) {
                        errorNo = JSONObject.parseObject(rest.getData() + "").getString("errorNo");
                        if ("0".equals(errorNo)) {
                            changeAccEntity.setYYStatus("1");//回写成功
                            changeAccEntity.setUpdateTime(LocalDateTime.now().toString());
                            changeAccMapper.updateByPrimaryKeySelective(changeAccEntity);
                        } else if (errorNo == null || "null".equals(errorNo)) {
                            log.info("修改账户信息回写返回值为空：{}", errorNo);
                        } else if ("1".equals(errorNo)) {
                            //回写失败，将status改为2
                            String errorInfo = JSONObject.parseObject(rest.getData() + "").getString("errorInfo");
                            changeAccEntity.setYYStatus("2");//回写失败
                            changeAccEntity.setUpdateTime(LocalDateTime.now().toString());
                            changeAccEntity.setZZErrorMsg(errorInfo);
                            changeAccMapper.updateByPrimaryKeySelective(changeAccEntity);
                        }
                    }
                }
            }
        }
    }


    /**
     * 基金账户销户
     */
    public void cancelAcc() {
        log.info("机构销户begin：{}");
        Gson gson = new Gson();
        CancleAccEntity cancelAccEntity = new CancleAccEntity();
        cancelAccEntity.setStatus("0");//0代表未执行回写
        List<CancleAccEntity> cancelAccEntityList = cancleAccMapper.select(cancelAccEntity);
        log.info("机构销户未回写数据：{}", cancelAccEntityList);
        if (cancelAccEntityList != null && cancelAccEntityList.size() > 0) {
            for (int i = 0; i < cancelAccEntityList.size(); i++) {
                //取出植信返回值，查询销户结果
                cancelAccEntity = cancelAccEntityList.get(i);
                String zxResult = cancelAccEntity.getBody();
                OrgnLogoutDTO orgnLogoutDTO = new OrgnLogoutDTO();
                orgnLogoutDTO = gson.fromJson(zxResult, new TypeToken<OrgnLogoutDTO>() {
                }.getType());
                //获取申请单编号
                String allotNo = "";
                if (StringUtils.isNotEmpty(cancelAccEntity.getZXResult())) {
                    allotNo = JSONObject.parseObject(cancelAccEntity.getZXResult()).getString("allot_no");
                    //调用链接SZ50_1 回写开户成功结果给云毅
                    ResultDTO rest = searchFundResultService.queryAllotNo(orgnLogoutDTO, allotNo, time);
                    String errorNo = "";
                    if (StringUtils.isNotEmpty(rest.getData() + "")) {
                        errorNo = JSONObject.parseObject(rest.getData() + "").getString("errorNo");
                    }
                    log.info("销户结果：{}", rest);
                    //如果，查询成功，并且回写成功，修改状态
                    if (StringUtils.isNotEmpty(errorNo)) {
                        if ("0".equals(errorNo)) {
                            //修改状态为已执行
                            cancelAccEntity.setStatus("1");//1代表回写成功
                            cancelAccEntity.setUpdate_time(LocalDateTime.now());//修改时间
                            log.info("销户成功修改数据库入参：{}", cancelAccEntity);
                            cancleAccMapper.updateByPrimaryKeySelective(cancelAccEntity);
                        } else if (errorNo == null || "null".equals(errorNo)) {
                            log.info("修改账户信息回写返回值为空：{}", errorNo);
                        } else if ("1".equals(errorNo)) {
                            //存储错误信息
                            String errorInfo = JSONObject.parseObject(rest.getData() + "").getString("errorInfo");
                            cancelAccEntity.setStatus("2");//回写失败
                            cancelAccEntity.setUpdate_time(LocalDateTime.now());//修改时间
                            cancelAccEntity.setYYErrMes(errorInfo);
                            log.info("销户失败修改数据库入参：{}", cancelAccEntity);
                            cancleAccMapper.updateByPrimaryKeySelective(cancelAccEntity);
                        }
                    }
                }
            }
        }
    }
}
