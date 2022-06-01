package com.jhj.oss.feign;

import com.jhj.common.model.Param;
import org.springframework.stereotype.Component;

@Component
public class UserProviderBack implements UserFeign {
    @Override
    public Object getDingtalkInfoByEmpIds(Param p) {
        return "降级了！！！！！！！！！！！！！";
    }

}