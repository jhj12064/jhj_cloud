package com.jhj.oss.service;
import java.sql.Timestamp;
import com.jhj.common.enums.DeleteFlag;

import com.jhj.common.enums.Error;
import com.jhj.common.exception.GlobalException;
import com.jhj.oss.dao.FileDao;
import com.jhj.oss.model.entity.FileTest;
import com.jhj.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class FileServiceImpl implements FileService {

//    @Autowired
//    AlibabaOssInterface ossInterface;

    @Autowired
    FileDao fileDao;


    @Override
    public void test(long fileId, boolean preview, HttpServletResponse response) {
        FileTest fileTest = new FileTest();
        fileTest.setOriginalName("name");
        fileTest.setUrl("url");
        fileTest.setContentType("1");
        fileTest.setFileSize(0L);
        fileDao.save(fileTest);
    }
}
