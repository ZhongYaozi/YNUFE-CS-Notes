package com.ruoyi.info.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.info.mapper.FlowerInfoMapper;
import com.ruoyi.info.domain.FlowerInfo;
import com.ruoyi.info.service.IFlowerInfoService;

/**
 * 鲜花信息Service业务层处理
 * 
 * @author gbr
 * @date 2025-06-18
 */
@Service
public class FlowerInfoServiceImpl implements IFlowerInfoService 
{
    @Autowired
    private FlowerInfoMapper flowerInfoMapper;

    /**
     * 查询鲜花信息
     * 
     * @param flowerId 鲜花信息主键
     * @return 鲜花信息
     */
    @Override
    public FlowerInfo selectFlowerInfoByFlowerId(Long flowerId)
    {
        return flowerInfoMapper.selectFlowerInfoByFlowerId(flowerId);
    }

    /**
     * 查询鲜花信息列表
     * 
     * @param flowerInfo 鲜花信息
     * @return 鲜花信息
     */
    @Override
    public List<FlowerInfo> selectFlowerInfoList(FlowerInfo flowerInfo)
    {
        return flowerInfoMapper.selectFlowerInfoList(flowerInfo);
    }

    /**
     * 新增鲜花信息
     * 
     * @param flowerInfo 鲜花信息
     * @return 结果
     */
    @Override
    public int insertFlowerInfo(FlowerInfo flowerInfo)
    {
        return flowerInfoMapper.insertFlowerInfo(flowerInfo);
    }

    /**
     * 修改鲜花信息
     * 
     * @param flowerInfo 鲜花信息
     * @return 结果
     */
    @Override
    public int updateFlowerInfo(FlowerInfo flowerInfo)
    {
        return flowerInfoMapper.updateFlowerInfo(flowerInfo);
    }

    /**
     * 批量删除鲜花信息
     * 
     * @param flowerIds 需要删除的鲜花信息主键
     * @return 结果
     */
    @Override
    public int deleteFlowerInfoByFlowerIds(Long[] flowerIds)
    {
        return flowerInfoMapper.deleteFlowerInfoByFlowerIds(flowerIds);
    }

    /**
     * 删除鲜花信息信息
     * 
     * @param flowerId 鲜花信息主键
     * @return 结果
     */
    @Override
    public int deleteFlowerInfoByFlowerId(Long flowerId)
    {
        return flowerInfoMapper.deleteFlowerInfoByFlowerId(flowerId);
    }
}
