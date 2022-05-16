package com.jhj.oss.feign;


import com.jhj.common.model.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "jhj-user-service")
public interface UserFeign {

    @PostMapping("/user/v1/getUser")
    Object getDingtalkInfoByEmpIds(@RequestBody Param p);


}
