package com.jhj.oss.controller;

import com.alibaba.fastjson.JSON;
import com.jhj.common.model.Param;
import com.jhj.oss.config.ExclusionUrl;
import com.jhj.oss.config.TestPro;
import com.jhj.oss.feign.UserFeign;
import com.jhj.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "test")
@RestController
@RequestMapping("/oss_v1")
public class OssController {

    @Autowired
    FileService fileService;

    @Autowired
    private TestPro testPro;

    @Autowired
    UserFeign userFeign;



    @ApiOperation(value = "test")
    @PostMapping("/test")
    public void download(HttpServletResponse response) {
        fileService.test(1, true, response);
    }

    @ApiOperation(value = "test")
    @PostMapping("/test2")
    public String test(HttpServletResponse response) {
        System.out.println(testPro);
        return "test2";
    }


    /**
     * 测试配置类
     * @param response
     * @return
     */
    @ApiOperation(value = "test")
    @PostMapping("/test3")
    public String test3(HttpServletResponse response) {
        Object a = userFeign.getDingtalkInfoByEmpIds(new Param(1, "a"));
        log.info("getDingtalkInfoByEmpIds 结果:{}" + JSON.toJSONString(a));
        System.out.println(testPro);
        return "test2";
    }







}
