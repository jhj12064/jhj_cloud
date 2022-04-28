package com.jhj.common.model.test;

import com.jhj.common.annotation.TestClass;
import com.jhj.common.annotation.TestField;
import lombok.Data;

/**
 * @author Jeremy
 * @date 2022/4/28 16:37
 */
@TestClass(name = "TestZhuJie", flag = false)
@Data
public class TestZhuJie {

    @TestField(name="id",type=Long.class, comment = "主键")
    private Long id;

    @TestField(name="name",type=String.class, comment = "名字")
    private String name;
}
