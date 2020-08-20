package com.xinhu.wealth.jgt.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wyt
 * @data 2020/3/20 14:34
 */
@Slf4j
public class YYSignUtil {
    public static boolean isSign(String sendTime,String param,String sing){
        //验签
        boolean verify = false;
        try {
            verify = StandardSecurityUtil.verify((sendTime+"&"+param),sing,"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiNamJqC1j47HWDqUxjmuK4TDdiUiOlaBAUeaB+vGO1qOMvTToWEMFWNu8bVlJzxqlYAgFHESydvSCPtjUcHR6DyqyL+QYDrQ9jPXoLZtSwuGLAa5ajDJzUUgjsrwCulD1sVFTuhcHMRjhJfssX5VrrDMdg2cbePlJgHzAXIDLVYmgDGb/lUksORqaYw1xiPKQVSHL8T3EhoPbiXjR5ZOUsqcsk1f97jbkmu/0kgUEVH2LMt1UzW16sYBHZHcMLKChkls2LSdtsdkSPMpXid395Ha8ZtQkq5ckAuqlCQMeG/79GigqJj2CxZcoSBrD+gld0poYEF3sOuB9r1KINTXywIDAQAB");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("验签结果："+verify);
        return verify;
    }
    public static String sign(String in,String privateKey){
        String signs = "";
        try {
            signs = StandardSecurityUtil.sign(in, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signs;
    }
}
