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
import com.ruoyi.jkza.domain.StIllegalTrend;
import com.ruoyi.jkza.service.IStIllegalTrendService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 非法趋势Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stIllegalTrend")
public class StIllegalTrendController extends BaseController
{
    @Autowired
    private IStIllegalTrendService stIllegalTrendService;

    /**
     * 查询非法趋势列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIllegalTrend:list')")
    @GetMapping("/list")
    public TableDataInfo list(StIllegalTrend stIllegalTrend)
    {
        startPage();
        List<StIllegalTrend> list = stIllegalTrendService.selectStIllegalTrendList(stIllegalTrend);
        return getDataTable(list);
    }

    /**
     * 导出非法趋势列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIllegalTrend:export')")
    @Log(title = "非法趋势", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StIllegalTrend stIllegalTrend)
    {
        List<StIllegalTrend> list = stIllegalTrendService.selectStIllegalTrendList(stIllegalTrend);
        ExcelUtil<StIllegalTrend> util = new ExcelUtil<StIllegalTrend>(StIllegalTrend.class);
        util.exportExcel(response, list, "非法趋势数据");
    }

    /**
     * 获取非法趋势详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIllegalTrend:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stIllegalTrendService.selectStIllegalTrendById(id));
    }

    /**
     * 新增非法趋势
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIllegalTrend:add')")
    @Log(title = "非法趋势", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StIllegalTrend stIllegalTrend)
    {
        return toAjax(stIllegalTrendService.insertStIllegalTrend(stIllegalTrend));
    }

    /**
     * 修改非法趋势
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIllegalTrend:edit')")
    @Log(title = "非法趋势", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StIllegalTrend stIllegalTrend)
    {
        return toAjax(stIllegalTrendService.updateStIllegalTrend(stIllegalTrend));
    }

    /**
     * 删除非法趋势
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIllegalTrend:remove')")
    @Log(title = "非法趋势", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(stIllegalTrendService.deleteStIllegalTrendByIds(ids));
    }
}
