package com.jhj.common.enums;

/**
 * swagger配置枚举
 *
 * @author Jeremy
 * @date 2021/12/14 6:00 PM
 */
public enum SwaggerType {

    OAA("oss","/oss/v2/api-docs", "2.0"),

    ;

    /**
     * 文档名称
     */
    String name;

    /**
     * 访问地址
     */
    String location;

    /**
     * 版本
     */
    String swaggerVersion;

    SwaggerType(String name, String location, String swaggerVersion) {
        this.name = name;
        this.location = location;
        this.swaggerVersion = swaggerVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSwaggerVersion() {
        return swaggerVersion;
    }

    public void setSwaggerVersion(String swaggerVersion) {
        this.swaggerVersion = swaggerVersion;
    }


}
