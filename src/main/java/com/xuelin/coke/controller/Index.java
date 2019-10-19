package com.xuelin.coke.controller;

import com.xuelin.coke.common.constants.Constants;
import com.xuelin.coke.common.dto.ResponseDTO;
import com.xuelin.coke.common.exception.InvalidParamEcxeption;
import com.xuelin.coke.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@ResponseBody
public class Index {
    private static final Logger logger = LoggerFactory.getLogger(Constants.ACCESS_LOGGER_NAME);

    @Autowired
    DbService dbService;

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public ResponseEntity error() {

        ResponseDTO responseDTO = new ResponseDTO();
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/ex", method = RequestMethod.GET)
    public ResponseEntity throwEx(HttpServletRequest request) {
        System.out.println("====here====");
        String id = request.getParameter("id");

        try {

            if(id.equals("1")) {
                logger.info("XXX参数异常");
                throw new InvalidParamEcxeption("XXX参数异常");
            }

            ResponseDTO responseDTO = new ResponseDTO();
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseDTO(500, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/resp/yaml",method = RequestMethod.GET)
    public String getYml() {
        return  dbService.getYamlConf();
    }
}
