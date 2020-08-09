package com.cloud.mall.cartorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan(basePackages = "com.cloud.mall.cartorder.model.dao")
@EnableRedisHttpSession
@EnableFeignClients
@ComponentScan({"com.cloud.mall.cartorder","com.cloud.mall.common"})
public class CartOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartOrderApplication.class, args);
    }
}
