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
import com.ruoyi.jkza.domain.YyWarn;
import com.ruoyi.jkza.service.IYyWarnService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预警信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/yyWarn")
public class YyWarnController extends BaseController
{
    @Autowired
    private IYyWarnService yyWarnService;

    /**
     * 查询预警信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyWarn:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyWarn yyWarn)
    {
        startPage();
        List<YyWarn> list = yyWarnService.selectYyWarnList(yyWarn);
        return getDataTable(list);
    }

    /**
     * 导出预警信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyWarn:export')")
    @Log(title = "预警信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyWarn yyWarn)
    {
        List<YyWarn> list = yyWarnService.selectYyWarnList(yyWarn);
        ExcelUtil<YyWarn> util = new ExcelUtil<YyWarn>(YyWarn.class);
        util.exportExcel(response, list, "预警信息数据");
    }

    /**
     * 获取预警信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyWarn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyWarnService.selectYyWarnById(id));
    }

    /**
     * 新增预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyWarn:add')")
    @Log(title = "预警信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyWarn yyWarn)
    {
        return toAjax(yyWarnService.insertYyWarn(yyWarn));
    }

    /**
     * 修改预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyWarn:edit')")
    @Log(title = "预警信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyWarn yyWarn)
    {
        return toAjax(yyWarnService.updateYyWarn(yyWarn));
    }

    /**
     * 删除预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyWarn:remove')")
    @Log(title = "预警信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyWarnService.deleteYyWarnByIds(ids));
    }
}
