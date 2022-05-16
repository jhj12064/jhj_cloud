package com.jhj.gateway.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeremy
 * @date 2022/5/16 9:54
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallbackA")
    public String fallbackA(){
       return "服务不可用";
    }
}
