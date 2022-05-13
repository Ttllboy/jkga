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
import com.ruoyi.jkza.domain.YyPerception;
import com.ruoyi.jkza.service.IYyPerceptionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 感知信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/yyPerception")
public class YyPerceptionController extends BaseController
{
    @Autowired
    private IYyPerceptionService yyPerceptionService;

    /**
     * 查询感知信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPerception:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyPerception yyPerception)
    {
        startPage();
        List<YyPerception> list = yyPerceptionService.selectYyPerceptionList(yyPerception);
        return getDataTable(list);
    }

    /**
     * 导出感知信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPerception:export')")
    @Log(title = "感知信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyPerception yyPerception)
    {
        List<YyPerception> list = yyPerceptionService.selectYyPerceptionList(yyPerception);
        ExcelUtil<YyPerception> util = new ExcelUtil<YyPerception>(YyPerception.class);
        util.exportExcel(response, list, "感知信息数据");
    }

    /**
     * 获取感知信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPerception:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyPerceptionService.selectYyPerceptionById(id));
    }

    /**
     * 新增感知信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPerception:add')")
    @Log(title = "感知信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyPerception yyPerception)
    {
        return toAjax(yyPerceptionService.insertYyPerception(yyPerception));
    }

    /**
     * 修改感知信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPerception:edit')")
    @Log(title = "感知信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyPerception yyPerception)
    {
        return toAjax(yyPerceptionService.updateYyPerception(yyPerception));
    }

    /**
     * 删除感知信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyPerception:remove')")
    @Log(title = "感知信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyPerceptionService.deleteYyPerceptionByIds(ids));
    }
}
