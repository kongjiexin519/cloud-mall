package com.cloud.mall.categoryproduct.controller;

import com.cloud.mall.categoryproduct.model.pojo.Product;
import com.cloud.mall.categoryproduct.model.request.ProductListReq;
import com.cloud.mall.categoryproduct.service.ProductService;
import com.cloud.mall.common.common.ApiRestResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProductController {
    @Resource
    ProductService productService;

    @PostMapping("/product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @PostMapping("/product/list")
    public ApiRestResponse list(ProductListReq productListReq) {
        PageInfo pageInfo = productService.list(productListReq);
        return ApiRestResponse.success(pageInfo);
    }

    @GetMapping("/product/detailForFeign")
    public Product detailForFeign(@RequestParam Integer id) {
        return productService.detail(id);
    }

    @PostMapping("/product/updateStock")
    public void updateStock(@RequestParam Integer productId, @RequestParam Integer stock) {
        productService.updateStock(productId, stock);
    }
}
