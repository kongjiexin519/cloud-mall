package com.cloud.mall.cartorder.service;

import com.cloud.mall.cartorder.model.request.CreateOrderReq;
import com.cloud.mall.cartorder.model.vo.OrderVO;
import com.github.pagehelper.PageInfo;

public interface OrderService {
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    String qrcode(String orderNo);

    void pay(String orderNo);

    void deliver(String orderNo);

    void finish(String orderNo);
}
