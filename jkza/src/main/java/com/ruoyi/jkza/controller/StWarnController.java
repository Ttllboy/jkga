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
import com.ruoyi.jkza.domain.StWarn;
import com.ruoyi.jkza.service.IStWarnService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预警信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stWarn")
public class StWarnController extends BaseController
{
    @Autowired
    private IStWarnService stWarnService;

    /**
     * 查询预警信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarn:list')")
    @GetMapping("/list")
    public TableDataInfo list(StWarn stWarn)
    {
        startPage();
        List<StWarn> list = stWarnService.selectStWarnList(stWarn);
        return getDataTable(list);
    }

    /**
     * 导出预警信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarn:export')")
    @Log(title = "预警信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StWarn stWarn)
    {
        List<StWarn> list = stWarnService.selectStWarnList(stWarn);
        ExcelUtil<StWarn> util = new ExcelUtil<StWarn>(StWarn.class);
        util.exportExcel(response, list, "预警信息数据");
    }

    /**
     * 获取预警信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stWarnService.selectStWarnById(id));
    }

    /**
     * 新增预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarn:add')")
    @Log(title = "预警信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StWarn stWarn)
    {
        return toAjax(stWarnService.insertStWarn(stWarn));
    }

    /**
     * 修改预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarn:edit')")
    @Log(title = "预警信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StWarn stWarn)
    {
        return toAjax(stWarnService.updateStWarn(stWarn));
    }

    /**
     * 删除预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarn:remove')")
    @Log(title = "预警信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(stWarnService.deleteStWarnByIds(ids));
    }
}
