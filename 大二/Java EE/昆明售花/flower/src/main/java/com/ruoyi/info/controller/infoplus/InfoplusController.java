package com.ruoyi.info.controller.infoplus;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.info.domain.FlowerInfo;
import com.ruoyi.info.service.IFlowerInfoService;
import com.ruoyi.cart.domain.ShoppingCart;
import com.ruoyi.cart.service.IShoppingCartService;

/**
 * 购花中心Controller
 *
 * @author ruoyi
 * @date 2025-06-18
 */
@RestController
@RequestMapping("/infoplus/infoplus")
public class InfoplusController extends BaseController
{
    @Autowired
    private IFlowerInfoService flowerInfoService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    /**
     * 查询鲜花信息列表（购花中心）
     */
    @PreAuthorize("@ss.hasPermi('infoplus:infoplus:list')")
    @GetMapping("/list")
    public TableDataInfo list(FlowerInfo flowerInfo)
    {
        startPage();
        List<FlowerInfo> list = flowerInfoService.selectFlowerInfoList(flowerInfo);
        return getDataTable(list);
    }

    /**
     * 更新购物车数量
     */
    @PreAuthorize("@ss.hasPermi('infoplus:infoplus:edit')")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PutMapping("/updateQuantity")
    public AjaxResult updateQuantity(@RequestBody ShoppingCartUpdateDTO updateDTO)
    {
        Long userId = SecurityUtils.getUserId();

        // 检查是否已存在该商品
        ShoppingCart existingCart = shoppingCartService.selectByUserAndFlower(userId, updateDTO.getFlowerId());

        if (updateDTO.getQuantity() == null || updateDTO.getQuantity() <= 0) {
            // 数量为0或负数，删除该项
            if (existingCart != null) {
                return toAjax(shoppingCartService.deleteShoppingCartByCartId(existingCart.getCartId()));
            }
            return AjaxResult.success(); // 本来就不存在，返回成功
        } else {
            if (existingCart != null) {
                // 更新现有记录
                existingCart.setQuantity(Long.valueOf(updateDTO.getQuantity()));
                return toAjax(shoppingCartService.updateShoppingCart(existingCart));
            } else {
                // 新增记录
                ShoppingCart newCart = new ShoppingCart();
                newCart.setUserId(userId);
                newCart.setFlowerId(updateDTO.getFlowerId());
                newCart.setQuantity(Long.valueOf(updateDTO.getQuantity()));
                return toAjax(shoppingCartService.insertShoppingCart(newCart));
            }
        }
    }

    /**
     * 获取用户购物车详情
     */
    @PreAuthorize("@ss.hasPermi('infoplus:infoplus:list')")
    @GetMapping("/cart")
    public AjaxResult getCartDetails()
    {
        Long userId = SecurityUtils.getUserId();
        List<ShoppingCart> cartDetails = shoppingCartService.selectCartByUser(userId);
        return AjaxResult.success(cartDetails);
    }

    /**
     * 清空购物车
     */
    @PreAuthorize("@ss.hasPermi('infoplus:infoplus:remove')")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
    @DeleteMapping("/clear")
    public AjaxResult clearCart()
    {
        Long userId = SecurityUtils.getUserId();
        return toAjax(shoppingCartService.deleteCartByUserId(userId));
    }

    /**
     * 获取购物车统计
     */
    @PreAuthorize("@ss.hasPermi('infoplus:infoplus:list')")
    @GetMapping("/summary")
    public AjaxResult getCartSummary()
    {
        Long userId = SecurityUtils.getUserId();

        List<ShoppingCart> cartItems = shoppingCartService.selectCartByUser(userId);

        // 计算统计信息
        int totalItems = cartItems.size();
        int totalQuantity = (int) cartItems.stream().mapToLong(ShoppingCart::getQuantity).sum();

        // 需要关联鲜花表计算总价
        double totalAmount = 0.0;
        for (ShoppingCart item : cartItems) {
            FlowerInfo flower = flowerInfoService.selectFlowerInfoByFlowerId(item.getFlowerId());
            if (flower != null) {
                totalAmount += item.getQuantity() * flower.getFlowerPrice().doubleValue();
            }
        }

        CartSummaryVO summary = new CartSummaryVO();
        summary.setTotalItems(totalItems);
        summary.setTotalQuantity(totalQuantity);
        summary.setTotalAmount(String.format("%.2f", totalAmount));

        return AjaxResult.success(summary);
    }

    /**
     * 购物车更新DTO
     */
    public static class ShoppingCartUpdateDTO {
        private Long flowerId;
        private Integer quantity;

        public Long getFlowerId() { return flowerId; }
        public void setFlowerId(Long flowerId) { this.flowerId = flowerId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    /**
     * 购物车统计VO
     */
    public static class CartSummaryVO {
        private Integer totalItems;
        private Integer totalQuantity;
        private String totalAmount;

        public Integer getTotalItems() { return totalItems; }
        public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
        public Integer getTotalQuantity() { return totalQuantity; }
        public void setTotalQuantity(Integer totalQuantity) { this.totalQuantity = totalQuantity; }
        public String getTotalAmount() { return totalAmount; }
        public void setTotalAmount(String totalAmount) { this.totalAmount = totalAmount; }
    }
}