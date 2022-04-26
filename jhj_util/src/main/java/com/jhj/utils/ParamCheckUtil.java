package com.jhj.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 校验参数
 *
 * @author Jeremy
 * @date 2021/12/1 14:00
 */
public class ParamCheckUtil {

    public static String checkParam(Object object, String... params){
        if(object == null){
            return "实体为空";
        }
        BeanWrapper bw = new BeanWrapperImpl(object);
        for(String param: params){
            Object propertyValue = bw.getPropertyValue(param);
            if(propertyValue == null){
                return "缺少参数：" + param;
            }
            if(StringUtils.isBlank(propertyValue.toString())){
                return "缺少参数：" + param;
            }
        }
        return null;
    }

}
