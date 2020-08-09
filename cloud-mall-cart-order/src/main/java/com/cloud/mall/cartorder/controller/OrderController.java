package com.cloud.mall.cartorder.controller;

import com.cloud.mall.cartorder.model.request.CreateOrderReq;
import com.cloud.mall.cartorder.model.vo.OrderVO;
import com.cloud.mall.cartorder.service.OrderService;
import com.cloud.mall.common.common.ApiRestResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    OrderService orderService;

    @PostMapping("/order/create")
    public ApiRestResponse create(@RequestBody CreateOrderReq createOrderReq) {
        String orderNo = orderService.create(createOrderReq);
        return ApiRestResponse.success(orderNo);
    }

    @GetMapping("/order/detail")
    public ApiRestResponse detail(@RequestParam String orderNo) {
        OrderVO detail = orderService.detail(orderNo);
        return ApiRestResponse.success(detail);
    }

    @PostMapping("/order/cancel")
    public ApiRestResponse cancel(@RequestParam String orderNo) {
        orderService.cancel(orderNo);
        return ApiRestResponse.success();
    }

    @GetMapping("/order/qrcode")
    public ApiRestResponse qrcode(@RequestParam String orderNo) {
        String qrcode = orderService.qrcode(orderNo);
        return ApiRestResponse.success(qrcode);
    }

    @GetMapping("/pay")
    public ApiRestResponse pay(@RequestParam String orderNo) {
        orderService.pay(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/order/delivered")
    public ApiRestResponse delivered(@RequestParam String orderNo) {
        orderService.deliver(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("/order/finish")
    public ApiRestResponse finish(@RequestParam String orderNo) {
        orderService.finish(orderNo);
        return ApiRestResponse.success();
    }

    @GetMapping("/order/list")
    public ApiRestResponse list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForCustomer(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }
}
