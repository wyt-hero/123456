package com.xinhu.wealth.jgt.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 机构通项目常量
 * 普通常量在此处配置
 */
@Component
@Getter
@Setter
public class JGTConstant {

    /**
     * 植信接口商户号  常量参数
     */
    public static final String ZX_MERID="ZXJJ40";

    /**
     * 云毅开通植信账号默认密码
     */
    public static final String ZX_PWD="147258";

    /**
     * 云毅签名 私钥
     */
    public static String YY_PRI_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCI1qYmoLWPjsdYOpTGOa4rhMN2JSI6VoEBR5oH68Y7Wo4y9NOhYQwVY27xtWUnPGqVgCAUcRLJ29II+2NRwdHoPKrIv5BgOtD2M9egtm1LC4YsBrlqMMnNRSCOyvAK6UPWxUVO6FwcxGOEl+yxflWusMx2DZxt4+UmAfMBcgMtViaAMZv+VSSw5GppjDXGI8pBVIcvxPcSGg9uJeNHlk5SypyyTV/3uNuSa7/SSBQRUfYsy3VTNbXqxgEdkdwwsoKGSWzYtJ22x2RI8yleJ3f3kdrxm1CSrlyQC6qUJAx4b/v0aKComPYLFlyhIGsP6CV3SmhgQXew64H2vUog1NfLAgMBAAECggEAS/oCXsg0hvTbsh00pas5DWi1rk+H5aRKFI/Q8jzy39mzwo81QhFESEvhOcth0ps1ArUVGsR9jh7DS8For+hRyASHPtc+Lm/5vfmi69TeciB/6soBAPj90z9iiGYdSbGivQjjJqDVJBPWvbQs85AVutznaVS9WjaC8Mt8RX0sTmsIfkoJb+nN2ALcVAHAR533Ekj9h1SZQkliOlnbnKj3muJfBSit/TBCjhxkQ/f94D/dY4woefI5TBgSU8lICILgb0qbfgSF+wUrNuvUKwapE0UHccG8ZDF1GmzRiNwB9b00eHuAV/gs8Jx5SPLanpJ826abq8oq/95PlNYbGr3voQKBgQDb+qQ/195Gq2K/8JJDykr6PaUOYyZE4wHO6L+4Zf3hUpIqGJxBPBGSJY+C6vYS0N/LXVKf6ZoXoVcuXUTkAmaXS+Zw/YHf6DjIkDjiGANuAzi+d69WPpPRDOHvNnyS1Y8GIyHFvRl90nLa7eJlBPftN82Q8Ii6l56lqso2jPq7HQKBgQCfPtDQ808enu1U4Arww5rpSIvf5/x8f524zFeiA1xXsvWK8h0MHltV7m321GEo9Qsou2y0Y16kSvFhkYhnoqPE1AtGw3mhBY0EQ+uk9sNJ9kZoCzTKwaTn5otE9tf/sDk5NCMGkg/MlTaPFdkOY/9OsIjOahSZkgQ7a3Sqt7mCBwKBgQCovbsAvjZjP1Sgp4mLNsIM8vyJ7MSOUxQwA5oNeS8miJBWekpiQBG6adWMN3BLPq5s8UlQIYFyXvDhP+p4fN71b+N1CQF3IEtujjMUPBdlbFM6x9RX8+ivk5I8TS+B8w+jRY/CfyvuuGAiUBg6JXWJIiUt6VT4zRWFP12eOsaagQKBgGebR+LamlxyzDjPd27D5PLoZ1DrL2OU5/oGwPFAvIC2h3i6I1nyGjyjXtobvYcXPwZmQshuanH77GYteJDsyR5TU27b2TOBWAkxY4w1nENvvskMmXFdYbwbS/s3mrrZXbGxESTCPRM+9XtMnKb3OnFo++lILVTpImXCeOpllIVbAoGBAJwk0Km946HzZNFZ9weSVvjRdGldRRLHG2jqsgbG88jbR+fUtpQxSRnNQKkrLnxaiZW5U/Y7FB1zxWZSy2P24dXvUZYsk5h24SvvTsiN5rteBMgJ144Dt4EC0qYuPsAbcpZh3De4atInU0nK7gZS2TX6rTWj3ivoisIxELcvDSMI";//云逸秘钥

    /**
     * 云毅签名 公钥
     */
    public static String  YY_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiNamJqC1j47HWDqUxjmuK4TDdiUiOlaBAUeaB+vGO1qOMvTToWEMFWNu8bVlJzxqlYAgFHESydvSCPtjUcHR6DyqyL+QYDrQ9jPXoLZtSwuGLAa5ajDJzUUgjsrwCulD1sVFTuhcHMRjhJfssX5VrrDMdg2cbePlJgHzAXIDLVYmgDGb/lUksORqaYw1xiPKQVSHL8T3EhoPbiXjR5ZOUsqcsk1f97jbkmu/0kgUEVH2LMt1UzW16sYBHZHcMLKChkls2LSdtsdkSPMpXid395Ha8ZtQkq5ckAuqlCQMeG/79GigqJj2CxZcoSBrD+gld0poYEF3sOuB9r1KINTXywIDAQAB";

    /**
     * 植信接口成功的返回成功的code
     * 成功，系统级返回码，所有调用接口成功都以该返回码为标志
     */
    public static String ZX_SUC = "ETS-5BP0000";


    /**
     * FTP 文件目录
     **/
    public static String YY_FTP_PATH = "/home/fis10/upload";
    /**
     * ftp 获取文件到本地的地址  此处需要写到
     * /usr/local/xinhu/jgt/file/(发布服务器地址)
     * E:\testfile\(本地测试)
     */
    public static String FTP_LOCAL_PATH = "/usr/local/xinhu/jgt/file/";
}
