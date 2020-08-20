package com.xinhu.wealth.jgt.service;

import com.xinhu.wealth.jgt.model.dto.zhixinDTO.ResultDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wyt
 * @data 2020/5/14 14:12
 */
public interface O32Service {
    ResultDTO o32Res(String body, HttpServletRequest request);
}
