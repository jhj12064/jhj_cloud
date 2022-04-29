package com.jhj.user.controller;

import com.jhj.annotation.LoginLog;
import com.jhj.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
