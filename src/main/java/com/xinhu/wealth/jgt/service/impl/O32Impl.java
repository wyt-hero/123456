package com.xinhu.wealth.jgt.service.impl;

import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;
import com.xinhu.wealth.jgt.service.O32Service;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wyt
 * @data 2020/5/14 14:13
 */
@Service
public class O32Impl implements O32Service {

    @Override
    public ResultDTO o32Res(String body, HttpServletRequest request) {
        String singText = request.getHeader("SingText");
        String processCode = request.getHeader("ProcessCode");
        String version = request.getHeader("Version");
        String pageIndex = request.getHeader("PageIndex");
        String rowSize = request.getHeader("RowSize");
        String sendTime = request.getHeader("SendTime");
        String institutionCode = request.getHeader("InstitutionCode");
        String institutionMarking = request.getHeader("InstitutionMarking");
        //todo  验签之后截取processCode前三位，判断要调用哪个方法
        //todo 验签要在这里做
        //todo 返回 resultDTO
        return null;
    }
}
