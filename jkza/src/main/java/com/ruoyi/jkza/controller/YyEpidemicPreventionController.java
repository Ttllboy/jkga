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
import com.ruoyi.jkza.domain.YyEpidemicPrevention;
import com.ruoyi.jkza.service.IYyEpidemicPreventionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 防疫详情Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/yyEpidemicPrevention")
public class YyEpidemicPreventionController extends BaseController
{
    @Autowired
    private IYyEpidemicPreventionService yyEpidemicPreventionService;

    /**
     * 查询防疫详情列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyEpidemicPrevention:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyEpidemicPrevention yyEpidemicPrevention)
    {
        startPage();
        List<YyEpidemicPrevention> list = yyEpidemicPreventionService.selectYyEpidemicPreventionList(yyEpidemicPrevention);
        return getDataTable(list);
    }

    /**
     * 导出防疫详情列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyEpidemicPrevention:export')")
    @Log(title = "防疫详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyEpidemicPrevention yyEpidemicPrevention)
    {
        List<YyEpidemicPrevention> list = yyEpidemicPreventionService.selectYyEpidemicPreventionList(yyEpidemicPrevention);
        ExcelUtil<YyEpidemicPrevention> util = new ExcelUtil<YyEpidemicPrevention>(YyEpidemicPrevention.class);
        util.exportExcel(response, list, "防疫详情数据");
    }

    /**
     * 获取防疫详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyEpidemicPrevention:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyEpidemicPreventionService.selectYyEpidemicPreventionById(id));
    }

    /**
     * 新增防疫详情
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyEpidemicPrevention:add')")
    @Log(title = "防疫详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyEpidemicPrevention yyEpidemicPrevention)
    {
        return toAjax(yyEpidemicPreventionService.insertYyEpidemicPrevention(yyEpidemicPrevention));
    }

    /**
     * 修改防疫详情
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyEpidemicPrevention:edit')")
    @Log(title = "防疫详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyEpidemicPrevention yyEpidemicPrevention)
    {
        return toAjax(yyEpidemicPreventionService.updateYyEpidemicPrevention(yyEpidemicPrevention));
    }

    /**
     * 删除防疫详情
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyEpidemicPrevention:remove')")
    @Log(title = "防疫详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(yyEpidemicPreventionService.deleteYyEpidemicPreventionByIds(ids));
    }
}
