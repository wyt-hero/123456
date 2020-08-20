package com.xinhu.wealth.jgt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wyt
 * @data 2020/5/14 14:02
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class O32Controller {
    @PostMapping(value = "/v1/o32", produces = "application/json;charset=utf-8")
    public String o32controller(@RequestBody String body, HttpServletRequest request){

        //todo  返回具体的ResultDTO转json

        return "";
    }
}
