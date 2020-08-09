package com.cloud.mall.categoryproduct.service;

import com.cloud.mall.categoryproduct.model.pojo.Product;
import com.cloud.mall.categoryproduct.model.request.AddProductReq;
import com.cloud.mall.categoryproduct.model.request.ProductListReq;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    Product detail(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    PageInfo list(ProductListReq productListReq);

    void updateStock(Integer productId, Integer stock);
}
