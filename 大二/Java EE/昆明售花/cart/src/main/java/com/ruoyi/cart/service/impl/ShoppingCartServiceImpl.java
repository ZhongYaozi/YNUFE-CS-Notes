package com.ruoyi.cart.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cart.mapper.ShoppingCartMapper;
import com.ruoyi.cart.domain.ShoppingCart;
import com.ruoyi.cart.service.IShoppingCartService;

/**
 * 购物车Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-18
 */
@Service
public class ShoppingCartServiceImpl implements IShoppingCartService 
{
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 查询购物车
     * 
     * @param cartId 购物车主键
     * @return 购物车
     */
    @Override
    public ShoppingCart selectShoppingCartByCartId(Long cartId)
    {
        return shoppingCartMapper.selectShoppingCartByCartId(cartId);
    }

    /**
     * 查询购物车列表
     * 
     * @param shoppingCart 购物车
     * @return 购物车
     */
    @Override
    public List<ShoppingCart> selectShoppingCartList(ShoppingCart shoppingCart)
    {
        return shoppingCartMapper.selectShoppingCartList(shoppingCart);
    }

    /**
     * 新增购物车
     * 
     * @param shoppingCart 购物车
     * @return 结果
     */
    @Override
    public int insertShoppingCart(ShoppingCart shoppingCart)
    {
        shoppingCart.setCreateTime(DateUtils.getNowDate());
        return shoppingCartMapper.insertShoppingCart(shoppingCart);
    }

    /**
     * 修改购物车
     * 
     * @param shoppingCart 购物车
     * @return 结果
     */
    @Override
    public int updateShoppingCart(ShoppingCart shoppingCart)
    {
        shoppingCart.setUpdateTime(DateUtils.getNowDate());
        return shoppingCartMapper.updateShoppingCart(shoppingCart);
    }

    /**
     * 批量删除购物车
     * 
     * @param cartIds 需要删除的购物车主键
     * @return 结果
     */
    @Override
    public int deleteShoppingCartByCartIds(Long[] cartIds)
    {
        return shoppingCartMapper.deleteShoppingCartByCartIds(cartIds);
    }

    /**
     * 删除购物车信息
     * 
     * @param cartId 购物车主键
     * @return 结果
     */
    @Override
    public int deleteShoppingCartByCartId(Long cartId)
    {
        return shoppingCartMapper.deleteShoppingCartByCartId(cartId);
    }
    // 在 ShoppingCartServiceImpl.java 中新增以下方法实现：

    /**
     * 根据用户ID和花卉ID查询购物车项
     */
    @Override
    public ShoppingCart selectByUserAndFlower(Long userId, Long flowerId) {
        return shoppingCartMapper.selectByUserAndFlower(userId, flowerId);
    }

    /**
     * 根据用户ID查询购物车列表
     */
    @Override
    public List<ShoppingCart> selectCartByUser(Long userId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        return shoppingCartMapper.selectShoppingCartList(shoppingCart);
    }

    /**
     * 根据用户ID删除购物车
     */
    @Override
    public int deleteCartByUserId(Long userId) {
        return shoppingCartMapper.deleteCartByUserId(userId);
    }

}
