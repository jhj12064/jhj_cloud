package com.jhj.oss.controller;

import com.jhj.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "文件处理")
@Controller
@RequestMapping("/oss/v1")
public class OssController {

    @Autowired
    FileService fileService;


    @ApiOperation(value = "test")
    @PostMapping("/test")
    public void download(HttpServletResponse response) {
        fileService.test(1, true, response);
    }


}
