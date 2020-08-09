package com.cloud.mall.cartorder.controller;

import com.cloud.mall.cartorder.service.OrderService;
import com.cloud.mall.common.common.ApiRestResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderAdminController {
    @Resource
    private OrderService orderService;

    @GetMapping("/admin/order/list")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }
}
