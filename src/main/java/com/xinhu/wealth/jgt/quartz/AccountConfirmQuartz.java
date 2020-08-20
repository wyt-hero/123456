package com.xinhu.wealth.jgt.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wyt
 * @data 2020/4/26 10:36
 * 此类包含：
 * 1、交易类回写定时任务
 * 2、确认定时任务
 * 3、分红定时任务
 * 4、持仓定时任务
 */
@Component
@Slf4j
public class AccountConfirmQuartz {

    @Autowired
    OrderRebuild orderRebuild;
    @Autowired
    AccountRepordRebuild accountRepordRebuild;
    @Autowired
    AccountFileUploadRebuild accountFileUploadRebuild;
    @Autowired
    AccountSearchRebuild accountSearchRebuild;

    /**
     * 账户开户回写
     */
    @Scheduled(cron = "0 */1 * * * ? ")// 从9-23时开始，每一分钟执行一次
    public void queryAccountBack(){
        log.info("账户开户回写定时任务开始");
        accountRepordRebuild.accountResultBack();
        log.info("账户开户回写定时任务结束");
    }

    /**
     * 账户开户后，文件上传定时任务
     */
    @Scheduled(cron = "0 */1 * * * ? ")// 从9-23时，每一分钟执行一次
    public void queryAccFileBack(){
        log.info("账户开户后，文件上传定时任务开始");
        accountFileUploadRebuild.accountFileUpload();
        log.info("账户开户后，文件上传定时任务结束");
    }

    /**
     * 账户修改回写
     */
    @Scheduled(cron = "0 */1 * * * ? ") //从9-23时开始，每一分钟执行一次
    public void modifyAccountBack(){
        log.info("账户修改回写定时任务开始");
        accountRepordRebuild.ChangeAccWriteBack();
        log.info("账户修改回写定时任务结束");
    }

    /**
     * 基金账户销户
     */
    @Scheduled(cron = "0 */1 * * * ? ")// 从9-23时开始，每一分钟执行一次
    public void cancleAccountBack(){
        log.info("基金账户销户定时任务开始");
        accountRepordRebuild.cancelAcc();
        log.info("基金账户销户定时任务结束");

    }


    /**
     * 交易类回写
     */
    @Scheduled(cron = "0 */1 * * * ? ")// 从零时开始，每一分钟执行一次
    public void tradingSearchBack() {
        //交易类回写
        log.info("交易类回写定时任务开始");
        orderRebuild.tradingSearchBack();
        log.info("交易类回写定时任务结束");
    }


    /**
     * 交易确认查询定时任务
     */
    @Scheduled(cron = "0 */5 * * * ? ")// 每5分钟执行一次
    public void query2002() {
        log.info("2.2交易确认查询定时任务开始");
        orderRebuild.queryOrderRes();
        log.info("2.2交易确认查询定时任务结束");
    }

    /**
     * 分红查询定时任务
     */
    @Scheduled(cron = "0 */5 * * * ? ")// 每5分钟执行一次
    public void query2003() {
        log.info("2.3分红查询定时任务开始");
        orderRebuild.queryOrderBouns();
        log.info("2.3分红查询定时任务结束");
    }

    /**
     * 持仓
     */
    @Scheduled(cron = "0 */5 * * * ? ")// 每5分钟执行一次
    public void query2004() {
        log.info("2.4持仓查询定时任务开始");
        orderRebuild.queryOrderShare();
        log.info("2.4持仓查询定时任务结束");
    }

    /**
     * 从植信查询确认账户保存到数据库
     */
    @Scheduled(cron = "0 */5 * * * ? ")// 每5分钟执行一次
    public void queryAccountConfirm(){
        log.info("4.5确认账户定时任务开始");
        accountSearchRebuild.queryAccountConfirm();
        log.info("4.5确认账户定时任务结束");
    }

}
