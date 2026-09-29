package com.ruoyi.info.controller;

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
import com.ruoyi.info.domain.FlowerInfo;
import com.ruoyi.info.service.IFlowerInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 鲜花信息Controller
 * 
 * @author gbr
 * @date 2025-06-18
 */
@RestController
@RequestMapping("/info/info")
public class FlowerInfoController extends BaseController
{
    @Autowired
    private IFlowerInfoService flowerInfoService;

    /**
     * 查询鲜花信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(FlowerInfo flowerInfo)
    {
        startPage();
        List<FlowerInfo> list = flowerInfoService.selectFlowerInfoList(flowerInfo);
        return getDataTable(list);
    }

    /**
     * 导出鲜花信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:info:export')")
    @Log(title = "鲜花信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FlowerInfo flowerInfo)
    {
        List<FlowerInfo> list = flowerInfoService.selectFlowerInfoList(flowerInfo);
        ExcelUtil<FlowerInfo> util = new ExcelUtil<FlowerInfo>(FlowerInfo.class);
        util.exportExcel(response, list, "鲜花信息数据");
    }

    /**
     * 获取鲜花信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:query')")
    @GetMapping(value = "/{flowerId}")
    public AjaxResult getInfo(@PathVariable("flowerId") Long flowerId)
    {
        return success(flowerInfoService.selectFlowerInfoByFlowerId(flowerId));
    }

    /**
     * 新增鲜花信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:add')")
    @Log(title = "鲜花信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowerInfo flowerInfo)
    {
        return toAjax(flowerInfoService.insertFlowerInfo(flowerInfo));
    }

    /**
     * 修改鲜花信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:edit')")
    @Log(title = "鲜花信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowerInfo flowerInfo)
    {
        return toAjax(flowerInfoService.updateFlowerInfo(flowerInfo));
    }

    /**
     * 删除鲜花信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:remove')")
    @Log(title = "鲜花信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{flowerIds}")
    public AjaxResult remove(@PathVariable Long[] flowerIds)
    {
        return toAjax(flowerInfoService.deleteFlowerInfoByFlowerIds(flowerIds));
    }
}
