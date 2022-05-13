package com.jhj.oss.service;

import javax.servlet.http.HttpServletResponse;
/**
 * @author Jeremy
 */
public interface FileService {


    void test(long fileId, boolean preview, HttpServletResponse response);
}
