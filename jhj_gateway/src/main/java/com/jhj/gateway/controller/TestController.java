package com.jhj.gateway.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
@RequestMapping
public class TestController {


    @GetMapping("/actuator/health")
    public String Health() {
        return "OK";
    }

}