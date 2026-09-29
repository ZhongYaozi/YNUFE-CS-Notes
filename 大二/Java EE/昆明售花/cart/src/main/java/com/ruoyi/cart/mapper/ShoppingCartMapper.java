package com.ruoyi.cart.mapper;

import java.util.List;
import com.ruoyi.cart.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-18
 */
@Mapper
public interface ShoppingCartMapper 
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
     * 删除购物车
     * 
     * @param cartId 购物车主键
     * @return 结果
     */
    public int deleteShoppingCartByCartId(Long cartId);

    /**
     * 批量删除购物车
     * 
     * @param cartIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShoppingCartByCartIds(Long[] cartIds);

    // 在 ShoppingCartMapper.java 中新增以下方法：

    /**
     * 根据用户ID和花卉ID查询购物车项
     */
    public ShoppingCart selectByUserAndFlower(@Param("userId") Long userId, @Param("flowerId") Long flowerId);

    /**
     * 根据用户ID删除购物车
     */
    public int deleteCartByUserId(@Param("userId") Long userId);
}
