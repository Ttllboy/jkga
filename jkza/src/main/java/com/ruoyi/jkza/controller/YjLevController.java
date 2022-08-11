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
import com.ruoyi.jkza.domain.YjLev;
import com.ruoyi.jkza.service.IYjLevService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 应急等级Controller
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
@RestController
@RequestMapping("/jkza/yjLev")
public class YjLevController extends BaseController
{
    @Autowired
    private IYjLevService yjLevService;

    /**
     * 查询应急等级列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLev:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjLev yjLev)
    {
        startPage();
        List<YjLev> list = yjLevService.selectYjLevList(yjLev);
        return getDataTable(list);
    }

    /**
     * 导出应急等级列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLev:export')")
    @Log(title = "应急等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjLev yjLev)
    {
        List<YjLev> list = yjLevService.selectYjLevList(yjLev);
        ExcelUtil<YjLev> util = new ExcelUtil<YjLev>(YjLev.class);
        util.exportExcel(response, list, "应急等级数据");
    }

    /**
     * 获取应急等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLev:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjLevService.selectYjLevById(id));
    }

    /**
     * 新增应急等级
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLev:add')")
    @Log(title = "应急等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjLev yjLev)
    {
        return toAjax(yjLevService.insertYjLev(yjLev));
    }

    /**
     * 修改应急等级
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLev:edit')")
    @Log(title = "应急等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjLev yjLev)
    {
        return toAjax(yjLevService.updateYjLev(yjLev));
    }

    /**
     * 删除应急等级
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLev:remove')")
    @Log(title = "应急等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjLevService.deleteYjLevByIds(ids));
    }
}
