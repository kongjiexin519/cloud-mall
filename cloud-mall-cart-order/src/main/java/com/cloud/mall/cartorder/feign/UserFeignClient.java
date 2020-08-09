package com.cloud.mall.cartorder.feign;

import com.cloud.mall.user.model.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "cloud-mall-user")
public interface UserFeignClient {

    @GetMapping("/getUser")
    @ResponseBody
    User getUser();
}
