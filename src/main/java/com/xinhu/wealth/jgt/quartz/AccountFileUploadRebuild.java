package com.xinhu.wealth.jgt.quartz;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinhu.wealth.jgt.constants.JGTConstant;
import com.xinhu.wealth.jgt.constants.PathZXConstants;
import com.xinhu.wealth.jgt.mapper.PollingMapper;
import com.xinhu.wealth.jgt.model.dto.yunyiDTO.InstitutionsAccountDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.FileuplloadZhiXinDTO;
import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.model.entity.PollingEntity;
import com.xinhu.wealth.jgt.util.RestemplateUtil;
import com.xinhu.wealth.jgt.util.SFTPFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 文件上传定时任务
 *
 * @author panjie
 * @date 2020/4/29 10:40
 */
@Slf4j
@Component
public class AccountFileUploadRebuild {

    /**
     * 交易账户处理mapper
     */
    @Autowired
    private PollingMapper pollingMapper;

    /**
     * 文件上传工具类
     */
    @Autowired
    private SFTPFileUtil sftpFileUtil;

    /**
     * 远程调用连接工具类
     */
    @Autowired
    private RestemplateUtil restTemplateUtil;

    //接收返回值
    ResultDTO resultDTO = new ResultDTO();

    /**
     * 定时任务 处理 开户的文件上传
     * 文件上传  将文件上传到植信
     */
    public void accountFileUpload() {
        log.info("文件上传begin：{}");
        Gson gson = new Gson();
        //1、查询，开户成功，未上传文件的数据
        PollingEntity pollingEntity = new PollingEntity();
        //上传文件类
        FileuplloadZhiXinDTO fileUploadZhiXinDTO = new FileuplloadZhiXinDTO();
        pollingEntity.setAccStatus(1);//1代表已开户成功
        pollingEntity.setIsFile(0);//0代表未执行
        List<PollingEntity> pollingEntityList = pollingMapper.select(pollingEntity);
        //查询到开户成功未执行文件上传的账户
        if (pollingEntityList != null && pollingEntityList.size() > 0) {
            //上传文件
            for (int i = 0; i < pollingEntityList.size(); i++) {
                pollingEntity = pollingEntityList.get(i);
                //获取fileNUB文件前缀
                String body = pollingEntity.getBody();
                String fileNumber = JSONObject.parseObject(body).getString("FileNumber");
                //赋值;档案类型
                fileUploadZhiXinDTO.setArchives_type("07");//账户类
                String accResult = pollingEntity.getAccResult();
                //获取植信data
                InstitutionsAccountDTO institutionsAccountDTO = gson.fromJson(accResult, InstitutionsAccountDTO.class);
                fileUploadZhiXinDTO.setRequest_no(institutionsAccountDTO.getAllot_no());//申请编号
                //赋值交易账号
                fileUploadZhiXinDTO.setTrade_acco(pollingEntity.getTransactionAccountID());
                //获取文件上传的网址
                String C4ABUrl = PathZXConstants.C4AB.getUrl();
                //获取文件
                File[] base641 = sftpFileUtil.downloadFile(JGTConstant.YY_FTP_PATH, fileNumber, JGTConstant.FTP_LOCAL_PATH);
                //将文件转成base64流
                Map<String, String> map = sftpFileUtil.changeBase642(base641);
                boolean isFile = true;
                //todo 判断map.keySet为空则修改数据库状态为成功，且存入文件上传的fileNumber
                if (map.keySet().size()>0){
                    for (String key : map.keySet()) {
                        //赋值文件流
                        fileUploadZhiXinDTO.setArchives_content(key);
                        //赋值 fileext_name   ;//文件扩展名
                        String encode = map.get(key);
                        fileUploadZhiXinDTO.setFileext_name(encode);
                        Map fileMap = JSONObject.parseObject(JSONObject.toJSONString(fileUploadZhiXinDTO), Map.class);
                        String fileStr = fileMap.toString().replaceAll(",", "&");
                        fileStr = fileStr.replaceAll(" ", "");
                        fileStr = fileStr.substring(1, fileStr.length() - 1);
                        //访问植信接口，得到返回值
                        String jsonString = JSONObject.toJSONString(fileUploadZhiXinDTO);
                        log.info("文件上传连接：{}", C4ABUrl + "&" + fileStr);
                        resultDTO = restTemplateUtil.postRest(jsonString = "", C4ABUrl + "&" + fileStr, null);
                        //转换为resultDTO
                        resultDTO = gson.fromJson(resultDTO.getData() + "", new TypeToken<ResultDTO>() {
                        }.getType());
                        //判断上传是否成功，失败则返回错误信息
                        if (resultDTO == null && !"ETS-5BP0000".equals(resultDTO.getCode())) {//上传失败
                            isFile = false;
                            //文件上传失败，插入数据库，修改上传状态，存入错误信息
                            pollingEntity.setIsFile(2);//上传文件失败
                            pollingEntity.setUpdateTime(LocalDateTime.now());
                            pollingEntity.setFileErrorMessage(resultDTO.getMessage());//文件上传失败原因
                            pollingMapper.updateByPrimaryKeySelective(pollingEntity);
                        } else {
                            //上传成功
                            pollingEntity.setIsFile(1);//上传文件成功
                            pollingEntity.setUpdateTime(LocalDateTime.now());
                            pollingMapper.updateByPrimaryKeySelective(pollingEntity);
                        }
                        //如果上传文件一次失败，则本次上传的一批文件都视为失败
                        if (!isFile) {
                            log.info("文件上传一个失败：{}");
                            break;
                        }
                    }
                }else {
                    //fileNumber为空
                    pollingEntity.setIsFile(1);//上传文件成功
                    pollingEntity.setUpdateTime(LocalDateTime.now());
                    pollingEntity.setFileErrorMessage("未找到文件，文件前缀名为："+fileNumber);//文件上传失败原因
                    pollingMapper.updateByPrimaryKeySelective(pollingEntity);
                }

            }
        }
    }
}
