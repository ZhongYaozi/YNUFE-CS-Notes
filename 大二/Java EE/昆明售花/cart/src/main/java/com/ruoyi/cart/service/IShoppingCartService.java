package com.ruoyi.cart.service;

import java.util.List;
import com.ruoyi.cart.domain.ShoppingCart;

/**
 * 购物车Service接口
 * 
 * @author ruoyi
 * @date 2025-06-18
 */
public interface IShoppingCartService 
{
    /**
     * 查询购物车
     * 
     * @param cartId 购物车主键
     * @return 购物车
     */
    public ShoppingCart selectShoppingCartByCartId(Long cartId);

    /**
     * 查询购物车列表
     * 
     * @param shoppingCart 购物车
     * @return 购物车集合
     */
    public List<ShoppingCart> selectShoppingCartList(ShoppingCart shoppingCart);

    /**
     * 新增购物车
     * 
     * @param shoppingCart 购物车
     * @return 结果
     */
    public int insertShoppingCart(ShoppingCart shoppingCart);

    /**
     * 修改购物车
     * 
     * @param shoppingCart 购物车
     * @return 结果
     */
    public int updateShoppingCart(ShoppingCart shoppingCart);

    /**
     * 批量删除购物车
     * 
     * @param cartIds 需要删除的购物车主键集合
     * @return 结果
     */
    public int deleteShoppingCartByCartIds(Long[] cartIds);

    /**
     * 删除购物车信息
     * 
     * @param cartId 购物车主键
     * @return 结果
     */
    public int deleteShoppingCartByCartId(Long cartId);

    // 在 IShoppingCartService.java 中新增以下方法：

    /**
     * 根据用户ID和花卉ID查询购物车项
     */
    public ShoppingCart selectByUserAndFlower(Long userId, Long flowerId);

    /**
     * 根据用户ID查询购物车列表
     */
    public List<ShoppingCart> selectCartByUser(Long userId);

    /**
     * 根据用户ID删除购物车
     */
    public int deleteCartByUserId(Long userId);
}
