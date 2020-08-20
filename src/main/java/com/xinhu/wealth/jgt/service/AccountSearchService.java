package com.xinhu.wealth.jgt.service;

public interface AccountSearchService {

    /**
     * 账户查询类
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
    String accountSearch(String body, String version, String processCode, String sing, String sendTime, String institutionMarking, Integer pageIndex, Integer rowSize);

    /**
     * 4.7修改交易密码（植信专用）
     * @param body 入参
     * @param version 版本号
     * @return
     */
    String modify4007PWD(String body,String version);


    /**
     * 4.8风险问卷查询
     * @return
     */
    String query4008(String body,String version);

    /**
     * 4.94.9 风险问卷测评提交
     * @param body
     * @param version
     * @return
     */
    String query4009(String body,String version);
}
