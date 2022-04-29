package com.jhj.aspect;

import com.alibaba.fastjson.JSON;
import com.jhj.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Aspect
@Component
@Slf4j
public class LoginLogAspect {


    @Pointcut("@annotation(com.jhj.annotation.LoginLog)")
    public void loginLogPointCut() {
    }

    @Transactional
    @Before("loginLogPointCut()")
    public void before(JoinPoint point) {
        log.info("登录前记录");

    }

    @Transactional(rollbackFor = Exception.class)
    @AfterReturning("loginLogPointCut()")
    public void after(JoinPoint point) {
        log.info("登录成功后记录");
        log.info(JSON.toJSONString(new BigDecimal(2)));
        Object[] args = point.getArgs();
        if (args != null && args.length >= 1) {
            Object arg = args[0];
            if (arg instanceof UserDTO) {
                UserDTO userDTO = (UserDTO) arg;
                log.info(JSON.toJSONString(userDTO));

            }
        }
    }


    @AfterThrowing(pointcut = "loginLogPointCut()", throwing = "e")
    public void after(JoinPoint point, Throwable e) {

    }

}
