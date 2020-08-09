package com.cloud.mall.cartorder.service.impl;

import com.cloud.mall.cartorder.feign.ProductFeignClient;
import com.cloud.mall.cartorder.model.dao.CartMapper;
import com.cloud.mall.cartorder.model.pojo.Cart;
import com.cloud.mall.cartorder.model.vo.CartVO;
import com.cloud.mall.cartorder.service.CartService;
import com.cloud.mall.categoryproduct.model.pojo.Product;
import com.cloud.mall.common.common.Constant;
import com.cloud.mall.common.exception.MallException;
import com.cloud.mall.common.exception.MallExceptionEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    ProductFeignClient productFeignClient;
    @Resource
    CartMapper cartMapper;

    @Override
    public List<CartVO> add(Integer userId, Integer productId, Integer count) {
        validProduct(productId, count);

        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setQuantity(count);
            cart.setSelected(Constant.Cart.CHECKED);
            cartMapper.insertSelective(cart);
        } else {
            count = cart.getQuantity() + count;
            Cart cartNew = new Cart();
            BeanUtils.copyProperties(cart, cartNew);
            cartNew.setQuantity(count);
            cartNew.setSelected(Constant.Cart.CHECKED);
            cartMapper.updateByPrimaryKeySelective(cartNew);
        }
        return this.list(userId);
    }

    private void validProduct(Integer productId, Integer count) {
        Product product = productFeignClient.detailForFeign(productId);
        if (product == null || product.getStatus().equals(Constant.SaleStatus.NOT_SALE)) {
            throw new MallException(MallExceptionEnum.NOT_SELL);
        }
        if (count > product.getStock()) {
            throw new MallException(MallExceptionEnum.NOT_ENOUGH);
        }
    }

    @Override
    public List<CartVO> list(Integer userId) {
        List<CartVO> cartVOS = cartMapper.selectList(userId);
        for (int i = 0; i < cartVOS.size(); i++) {
            CartVO cartVO = cartVOS.get(i);
            cartVO.setTotalPrice(cartVO.getPrice() * cartVO.getQuantity());
        }
        return cartVOS;
    }

    @Override
    public List<CartVO> update(Integer userId, Integer productId, Integer count) {
        validProduct(productId, count);
        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        } else {
            Cart cartNew = new Cart();
            BeanUtils.copyProperties(cart, cartNew);
            cartNew.setQuantity(count);
            cartNew.setSelected(Constant.Cart.CHECKED);
            cartMapper.updateByPrimaryKeySelective(cartNew);
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> delete(Integer userId, Integer productId) {
        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        } else {
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected) {
        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        } else {
            cartMapper.selectOrNot(userId, productId, selected);
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> selectAllOrNot(Integer userId, Integer selected) {
        cartMapper.selectOrNot(userId, null, selected);
        return this.list(userId);
    }
}
