package com.jhj.oss.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author Jeremy
 * @date 2022/5/11 17:22
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "testpro")
public class TestPro {
    private Integer id;

    private String name;

    private Boolean flag;

    private Map<String, Object> maps;

    private List<String> lists;

    private Dog dog;


}
