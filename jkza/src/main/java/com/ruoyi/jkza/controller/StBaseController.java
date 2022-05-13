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
import com.ruoyi.jkza.domain.StBase;
import com.ruoyi.jkza.service.IStBaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础数据Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stBase")
public class StBaseController extends BaseController
{
    @Autowired
    private IStBaseService stBaseService;

    /**
     * 查询基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stBase:list')")
    @GetMapping("/list")
    public TableDataInfo list(StBase stBase)
    {
        startPage();
        List<StBase> list = stBaseService.selectStBaseList(stBase);
        return getDataTable(list);
    }

    /**
     * 导出基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stBase:export')")
    @Log(title = "基础数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StBase stBase)
    {
        List<StBase> list = stBaseService.selectStBaseList(stBase);
        ExcelUtil<StBase> util = new ExcelUtil<StBase>(StBase.class);
        util.exportExcel(response, list, "基础数据数据");
    }

    /**
     * 获取基础数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stBase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stBaseService.selectStBaseById(id));
    }

    /**
     * 新增基础数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:stBase:add')")
    @Log(title = "基础数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StBase stBase)
    {
        return toAjax(stBaseService.insertStBase(stBase));
    }

    /**
     * 修改基础数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:stBase:edit')")
    @Log(title = "基础数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StBase stBase)
    {
        return toAjax(stBaseService.updateStBase(stBase));
    }

    /**
     * 删除基础数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:stBase:remove')")
    @Log(title = "基础数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stBaseService.deleteStBaseByIds(ids));
    }
}
