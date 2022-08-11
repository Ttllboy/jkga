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
import com.ruoyi.jkza.domain.YyPerceptionType;
import com.ruoyi.jkza.service.IYyPerceptionTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 感知筛选Controller
 * 
 * @author ruoyi
 * @date 2022-08-01
 */
@RestController
@RequestMapping("/jkza/yyPeceptionTtype")
public class YyPerceptionTypeController extends BaseController
{
    @Autowired
    private IYyPerceptionTypeService yyPerceptionTypeService;

    /**
     * 查询感知筛选列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPeceptionTtype:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyPerceptionType yyPerceptionType)
    {
        startPage();
        List<YyPerceptionType> list = yyPerceptionTypeService.selectYyPerceptionTypeList(yyPerceptionType);
        return getDataTable(list);
    }

    /**
     * 导出感知筛选列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPeceptionTtype:export')")
    @Log(title = "感知筛选", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyPerceptionType yyPerceptionType)
    {
        List<YyPerceptionType> list = yyPerceptionTypeService.selectYyPerceptionTypeList(yyPerceptionType);
        ExcelUtil<YyPerceptionType> util = new ExcelUtil<YyPerceptionType>(YyPerceptionType.class);
        util.exportExcel(response, list, "感知筛选数据");
    }

    /**
     * 获取感知筛选详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPeceptionTtype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyPerceptionTypeService.selectYyPerceptionTypeById(id));
    }

    /**
     * 新增感知筛选
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPeceptionTtype:add')")
    @Log(title = "感知筛选", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyPerceptionType yyPerceptionType)
    {
        return toAjax(yyPerceptionTypeService.insertYyPerceptionType(yyPerceptionType));
    }

    /**
     * 修改感知筛选
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPeceptionTtype:edit')")
    @Log(title = "感知筛选", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyPerceptionType yyPerceptionType)
    {
        return toAjax(yyPerceptionTypeService.updateYyPerceptionType(yyPerceptionType));
    }

    /**
     * 删除感知筛选
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPeceptionTtype:remove')")
    @Log(title = "感知筛选", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyPerceptionTypeService.deleteYyPerceptionTypeByIds(ids));
    }
}
