package com.cloud.mall.categoryproduct.service;

import com.cloud.mall.categoryproduct.model.pojo.Category;
import com.cloud.mall.categoryproduct.model.request.AddCategoryReq;
import com.cloud.mall.categoryproduct.model.vo.CategoryVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
