package com.ruoyi.info.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 鲜花信息对象 flower_info
 * 
 * @author gbr
 * @date 2025-06-18
 */
public class FlowerInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 鲜花ID */
    private Long flowerId;

    /** 鲜花名称 */
    @Excel(name = "鲜花名称")
    private String flowerName;

    /** 鲜花图片 */
    @Excel(name = "鲜花图片")
    private String flowerImageUrl;

    /** 鲜花单价(元) */
    @Excel(name = "鲜花单价(元)")
    private BigDecimal flowerPrice;

    public void setFlowerId(Long flowerId) 
    {
        this.flowerId = flowerId;
    }

    public Long getFlowerId() 
    {
        return flowerId;
    }

    public void setFlowerName(String flowerName) 
    {
        this.flowerName = flowerName;
    }

    public String getFlowerName() 
    {
        return flowerName;
    }

    public void setFlowerImageUrl(String flowerImageUrl) 
    {
        this.flowerImageUrl = flowerImageUrl;
    }

    public String getFlowerImageUrl() 
    {
        return flowerImageUrl;
    }

    public void setFlowerPrice(BigDecimal flowerPrice) 
    {
        this.flowerPrice = flowerPrice;
    }

    public BigDecimal getFlowerPrice() 
    {
        return flowerPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("flowerId", getFlowerId())
            .append("flowerName", getFlowerName())
            .append("flowerImageUrl", getFlowerImageUrl())
            .append("flowerPrice", getFlowerPrice())
            .append("remark", getRemark())
            .toString();
    }
}
