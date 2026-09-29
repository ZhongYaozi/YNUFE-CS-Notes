package com.ruoyi.info.service;

import java.util.List;
import com.ruoyi.info.domain.FlowerInfo;

/**
 * 鲜花信息Service接口
 * 
 * @author gbr
 * @date 2025-06-18
 */
public interface IFlowerInfoService 
{
    /**
     * 查询鲜花信息
     * 
     * @param flowerId 鲜花信息主键
     * @return 鲜花信息
     */
    public FlowerInfo selectFlowerInfoByFlowerId(Long flowerId);

    /**
     * 查询鲜花信息列表
     * 
     * @param flowerInfo 鲜花信息
     * @return 鲜花信息集合
     */
    public List<FlowerInfo> selectFlowerInfoList(FlowerInfo flowerInfo);

    /**
     * 新增鲜花信息
     * 
     * @param flowerInfo 鲜花信息
     * @return 结果
     */
    public int insertFlowerInfo(FlowerInfo flowerInfo);

    /**
     * 修改鲜花信息
     * 
     * @param flowerInfo 鲜花信息
     * @return 结果
     */
    public int updateFlowerInfo(FlowerInfo flowerInfo);

    /**
     * 批量删除鲜花信息
     * 
     * @param flowerIds 需要删除的鲜花信息主键集合
     * @return 结果
     */
    public int deleteFlowerInfoByFlowerIds(Long[] flowerIds);

    /**
     * 删除鲜花信息信息
     * 
     * @param flowerId 鲜花信息主键
     * @return 结果
     */
    public int deleteFlowerInfoByFlowerId(Long flowerId);
}
