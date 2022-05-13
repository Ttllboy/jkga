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
import com.ruoyi.jkza.domain.GdBase;
import com.ruoyi.jkza.service.IGdBaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础数据Controller
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@RestController
@RequestMapping("/jkza/base")
public class GdBaseController extends BaseController
{
    @Autowired
    private IGdBaseService gdBaseService;

    /**
     * 查询基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:base:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdBase gdBase)
    {
        startPage();
        List<GdBase> list = gdBaseService.selectGdBaseList(gdBase);
        return getDataTable(list);
    }

    /**
     * 导出基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:base:export')")
    @Log(title = "基础数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdBase gdBase)
    {
        List<GdBase> list = gdBaseService.selectGdBaseList(gdBase);
        ExcelUtil<GdBase> util = new ExcelUtil<GdBase>(GdBase.class);
        util.exportExcel(response, list, "基础数据数据");
    }

    /**
     * 获取基础数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:base:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdBaseService.selectGdBaseById(id));
    }

    /**
     * 新增基础数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:base:add')")
    @Log(title = "基础数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdBase gdBase)
    {
        gdBase.setDataType(1);
        return toAjax(gdBaseService.insertGdBase(gdBase));
    }

    /**
     * 修改基础数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:base:edit')")
    @Log(title = "基础数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdBase gdBase)
    {
        return toAjax(gdBaseService.updateGdBase(gdBase));
    }

    /**
     * 删除基础数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:base:remove')")
    @Log(title = "基础数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdBaseService.deleteGdBaseByIds(ids));
    }
}
