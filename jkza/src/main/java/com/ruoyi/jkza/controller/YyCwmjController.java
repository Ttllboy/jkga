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
import com.ruoyi.jkza.domain.YyCwmj;
import com.ruoyi.jkza.service.IYyCwmjService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测温门禁Controller
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@RestController
@RequestMapping("/jkza/cwmj")
public class YyCwmjController extends BaseController
{
    @Autowired
    private IYyCwmjService yyCwmjService;

    /**
     * 查询测温门禁列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:cwmj:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyCwmj yyCwmj)
    {
        startPage();
        List<YyCwmj> list = yyCwmjService.selectYyCwmjList(yyCwmj);
        return getDataTable(list);
    }

    /**
     * 导出测温门禁列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:cwmj:export')")
    @Log(title = "测温门禁", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyCwmj yyCwmj)
    {
        List<YyCwmj> list = yyCwmjService.selectYyCwmjList(yyCwmj);
        ExcelUtil<YyCwmj> util = new ExcelUtil<YyCwmj>(YyCwmj.class);
        util.exportExcel(response, list, "测温门禁数据");
    }

    /**
     * 获取测温门禁详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:cwmj:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyCwmjService.selectYyCwmjById(id));
    }

    /**
     * 新增测温门禁
     */
    @PreAuthorize("@ss.hasPermi('jkza:cwmj:add')")
    @Log(title = "测温门禁", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyCwmj yyCwmj)
    {
        return toAjax(yyCwmjService.insertYyCwmj(yyCwmj));
    }

    /**
     * 修改测温门禁
     */
    @PreAuthorize("@ss.hasPermi('jkza:cwmj:edit')")
    @Log(title = "测温门禁", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyCwmj yyCwmj)
    {
        return toAjax(yyCwmjService.updateYyCwmj(yyCwmj));
    }

    /**
     * 删除测温门禁
     */
    @PreAuthorize("@ss.hasPermi('jkza:cwmj:remove')")
    @Log(title = "测温门禁", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyCwmjService.deleteYyCwmjByIds(ids));
    }
}
