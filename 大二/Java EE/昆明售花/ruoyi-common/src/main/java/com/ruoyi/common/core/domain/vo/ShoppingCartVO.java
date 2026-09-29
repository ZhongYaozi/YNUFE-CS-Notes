package com.ruoyi.common.core.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车详情VO
 */
public class ShoppingCartVO {

    /** 购物车ID */
    private Long cartId;

    /** 用户ID */
    private Long userId;

    /** 鲜花ID */
    private Long flowerId;

    /** 鲜花名称 */
    private String flowerName;

    /** 鲜花图片URL */
    private String flowerImageUrl;

    /** 鲜花单价 */
    private BigDecimal flowerPrice;

    /** 购买数量 */
    private Integer quantity;

    /** 小计金额 */
    private BigDecimal subtotal;

    /** 备注信息 */
    private String remark;

    /** 创建时间 */
    private Date createTime;

    // Getters and Setters
    public Long getCartId() { return cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getFlowerId() { return flowerId; }
    public void setFlowerId(Long flowerId) { this.flowerId = flowerId; }

    public String getFlowerName() { return flowerName; }
    public void setFlowerName(String flowerName) { this.flowerName = flowerName; }

    public String getFlowerImageUrl() { return flowerImageUrl; }
    public void setFlowerImageUrl(String flowerImageUrl) { this.flowerImageUrl = flowerImageUrl; }

    public BigDecimal getFlowerPrice() { return flowerPrice; }
    public void setFlowerPrice(BigDecimal flowerPrice) { this.flowerPrice = flowerPrice; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}

/**
 * 购物车统计VO
 */
class CartSummaryVO {

    /** 商品种类数 */
    private Integer totalItems;

    /** 商品总数量 */
    private Integer totalQuantity;

    /** 总金额 */
    private String totalAmount;

    // Getters and Setters
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }

    public Integer getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(Integer totalQuantity) { this.totalQuantity = totalQuantity; }

    public String getTotalAmount() { return totalAmount; }
    public void setTotalAmount(String totalAmount) { this.totalAmount = totalAmount; }
}