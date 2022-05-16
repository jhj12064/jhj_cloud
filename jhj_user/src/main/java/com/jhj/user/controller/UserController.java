package com.jhj.user.controller;

import com.alibaba.fastjson.JSON;
import com.jhj.annotation.LoginLog;
import com.jhj.common.model.Param;
import com.jhj.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Jeremy
 * @date 2022/4/29 11:12
 */
@RestController
@Slf4j
public class UserController {

    @RequestMapping("/user/login/{userId}")
    @LoginLog
    public void login(@RequestBody UserDTO t){
        log.info(t.getUserId() + ":登录成功");
    }

    @RequestMapping("/user/v1/getUser")
    public Object getUser(@RequestBody Param t){
        log.info("param:{}", JSON.toJSON(t));
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("userId", 1);
        objectObjectHashMap.put("userName", "张三");
        return objectObjectHashMap;
    }
}
