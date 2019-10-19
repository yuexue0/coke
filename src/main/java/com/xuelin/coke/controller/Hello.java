package com.xuelin.coke.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuelin.coke.common.constants.Constants;
import com.xuelin.coke.common.dto.ResponseDTO;
import com.xuelin.coke.common.exception.InvalidParamEcxeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(value = "/hello",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@ResponseBody
public class Hello {
    private static final Logger logger = LoggerFactory.getLogger(Constants.ACCESS_LOGGER_NAME);

    @RequestMapping(value = "/index")
    public Object index() {

        String result = UUID.randomUUID().toString();
        String msg = "成功登录纳斯达克！！！";
        int code =200;

        JSONObject jsobjcet = new JSONObject();
        jsobjcet.put("code", code);
        jsobjcet.put("result", result);
        jsobjcet.put("msg", msg);

        String s = jsobjcet.toString();

        System.out.println(s);
        logger.info(s);
        return s;
    }


    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.index();
    }
}
