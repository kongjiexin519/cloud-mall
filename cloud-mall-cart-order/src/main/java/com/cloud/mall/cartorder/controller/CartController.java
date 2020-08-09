package com.cloud.mall.cartorder.controller;

import com.cloud.mall.cartorder.feign.UserFeignClient;
import com.cloud.mall.cartorder.model.vo.CartVO;
import com.cloud.mall.cartorder.service.CartService;
import com.cloud.mall.common.common.ApiRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    CartService cartService;
    @Autowired
    UserFeignClient userFeignClient;

    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOS = cartService.add(userFeignClient.getUser().getId(), productId, count);
        return ApiRestResponse.success(cartVOS);
    }

    @GetMapping("/list")
    public ApiRestResponse list() {
        List<CartVO> cartVOList = cartService.list(userFeignClient.getUser().getId());
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService.update(userFeignClient.getUser().getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        List<CartVO> cartVOList = cartService.delete(userFeignClient.getUser().getId(), productId);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/select")
    public ApiRestResponse select(@RequestParam Integer productId, @RequestParam Integer selected) {
        List<CartVO> cartVOList = cartService.selectOrNot(userFeignClient.getUser().getId(), productId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(@RequestParam Integer selected) {
        List<CartVO> cartVOList = cartService.selectAllOrNot(userFeignClient.getUser().getId(), selected);
        return ApiRestResponse.success(cartVOList);
    }
}
