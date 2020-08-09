package com.cloud.mall.categoryproduct.model.dao;

import com.cloud.mall.categoryproduct.model.pojo.Product;
import com.cloud.mall.categoryproduct.model.query.ProductListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByName(String name);

    int batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    List<Product> selectListforAdmin();

    List<Product> selectList(@Param("query") ProductListQuery query);
}