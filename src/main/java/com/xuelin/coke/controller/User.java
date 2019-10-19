package com.xuelin.coke.controller;

import com.xuelin.coke.common.constants.Constants;
import com.xuelin.coke.common.dto.CreateUserProfileInfo;
import com.xuelin.coke.common.dto.ResponseDTO;
import com.xuelin.coke.domain.UserProfileInfo;
import com.xuelin.coke.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class User {
    private static final Logger logger = LoggerFactory.getLogger(Constants.ACCESS_LOGGER_NAME);

    @Autowired
    private DbService dbService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) {

        String s = "login";
        return s;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable Integer id) {
        UserProfileInfo user = dbService.findByID(id);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity findName(@PathVariable String name) {
        UserProfileInfo userProfileInfo = dbService.findByname(name);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userProfileInfo);
        responseDTO.setDesc(name);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/lists", method = RequestMethod.GET)
    public ResponseEntity getUserLists() {
        List<UserProfileInfo> userProfileInfos = dbService.userList();

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userProfileInfos);
        responseDTO.setDesc("列表");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@ModelAttribute CreateUserProfileInfo createUserProfileInfo) {

        try {

            dbService.create(CreateUserProfileInfo.convert2UserProfileInfo(createUserProfileInfo));
            return new ResponseEntity<>(new ResponseDTO(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("", ex);
            return new ResponseEntity<>(new ResponseDTO(500, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public static void main(String[] args) {
        User user = new User();
        ResponseEntity log = user.getUser(1);
        System.out.println(log);
    }

}
