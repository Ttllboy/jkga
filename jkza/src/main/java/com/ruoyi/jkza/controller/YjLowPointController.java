package com.ruoyi.jkza.controller;

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
import com.ruoyi.jkza.domain.YjLowPoint;
import com.ruoyi.jkza.service.IYjLowPointService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 低洼点Controller
 * 
 * @author ruoyi
 * @date 2022-06-20
 */
@RestController
@RequestMapping("/jkza/yjLowPoint")
public class YjLowPointController extends BaseController
{
    @Autowired
    private IYjLowPointService yjLowPointService;

    /**
     * 查询低洼点列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowPoint:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjLowPoint yjLowPoint)
    {
        startPage();
        List<YjLowPoint> list = yjLowPointService.selectYjLowPointList(yjLowPoint);
        return getDataTable(list);
    }

    /**
     * 导出低洼点列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowPoint:export')")
    @Log(title = "低洼点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjLowPoint yjLowPoint)
    {
        List<YjLowPoint> list = yjLowPointService.selectYjLowPointList(yjLowPoint);
        ExcelUtil<YjLowPoint> util = new ExcelUtil<YjLowPoint>(YjLowPoint.class);
        util.exportExcel(response, list, "低洼点数据");
    }

    /**
     * 获取低洼点详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowPoint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjLowPointService.selectYjLowPointById(id));
    }

    /**
     * 新增低洼点
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowPoint:add')")
    @Log(title = "低洼点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjLowPoint yjLowPoint)
    {
        return toAjax(yjLowPointService.insertYjLowPoint(yjLowPoint));
    }

    /**
     * 修改低洼点
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowPoint:edit')")
    @Log(title = "低洼点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjLowPoint yjLowPoint)
    {
        return toAjax(yjLowPointService.updateYjLowPoint(yjLowPoint));
    }

    /**
     * 删除低洼点
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowPoint:remove')")
    @Log(title = "低洼点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjLowPointService.deleteYjLowPointByIds(ids));
    }
}
