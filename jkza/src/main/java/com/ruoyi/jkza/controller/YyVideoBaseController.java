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
import com.ruoyi.jkza.domain.YyVideoBase;
import com.ruoyi.jkza.service.IYyVideoBaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频列表Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/yyVideoBase")
public class YyVideoBaseController extends BaseController
{
    @Autowired
    private IYyVideoBaseService yyVideoBaseService;

    /**
     * 查询视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideoBase:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyVideoBase yyVideoBase)
    {
        startPage();
        List<YyVideoBase> list = yyVideoBaseService.selectYyVideoBaseList(yyVideoBase);
        return getDataTable(list);
    }

    /**
     * 导出视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideoBase:export')")
    @Log(title = "视频列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyVideoBase yyVideoBase)
    {
        List<YyVideoBase> list = yyVideoBaseService.selectYyVideoBaseList(yyVideoBase);
        ExcelUtil<YyVideoBase> util = new ExcelUtil<YyVideoBase>(YyVideoBase.class);
        util.exportExcel(response, list, "视频列表数据");
    }

    /**
     * 获取视频列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideoBase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyVideoBaseService.selectYyVideoBaseById(id));
    }

    /**
     * 新增视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideoBase:add')")
    @Log(title = "视频列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyVideoBase yyVideoBase)
    {
        return toAjax(yyVideoBaseService.insertYyVideoBase(yyVideoBase));
    }

    /**
     * 修改视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideoBase:edit')")
    @Log(title = "视频列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyVideoBase yyVideoBase)
    {
        return toAjax(yyVideoBaseService.updateYyVideoBase(yyVideoBase));
    }

    /**
     * 删除视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideoBase:remove')")
    @Log(title = "视频列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(yyVideoBaseService.deleteYyVideoBaseByIds(ids));
    }
}
