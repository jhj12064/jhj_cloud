package com.jhj;

import com.jhj.oss.OssApplication;
import com.jhj.oss.config.TestPro;
import com.jhj.oss.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jeremy
 * @date 2022/5/12 10:17
 */
@SpringBootTest(classes = OssApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    FileService fileService;

    @Autowired
    private TestPro testPro;

    @Test
    public void test1(){
        System.out.println(testPro);
        fileService.test(1, true, null);
    }
}
