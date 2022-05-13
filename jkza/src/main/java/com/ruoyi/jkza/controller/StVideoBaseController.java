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
import com.ruoyi.jkza.domain.StVideoBase;
import com.ruoyi.jkza.service.IStVideoBaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频列表Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stVideoBase")
public class StVideoBaseController extends BaseController
{
    @Autowired
    private IStVideoBaseService stVideoBaseService;

    /**
     * 查询视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideoBase:list')")
    @GetMapping("/list")
    public TableDataInfo list(StVideoBase stVideoBase)
    {
        startPage();
        List<StVideoBase> list = stVideoBaseService.selectStVideoBaseList(stVideoBase);
        return getDataTable(list);
    }

    /**
     * 导出视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideoBase:export')")
    @Log(title = "视频列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StVideoBase stVideoBase)
    {
        List<StVideoBase> list = stVideoBaseService.selectStVideoBaseList(stVideoBase);
        ExcelUtil<StVideoBase> util = new ExcelUtil<StVideoBase>(StVideoBase.class);
        util.exportExcel(response, list, "视频列表数据");
    }

    /**
     * 获取视频列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideoBase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stVideoBaseService.selectStVideoBaseById(id));
    }

    /**
     * 新增视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideoBase:add')")
    @Log(title = "视频列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StVideoBase stVideoBase)
    {
        return toAjax(stVideoBaseService.insertStVideoBase(stVideoBase));
    }

    /**
     * 修改视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideoBase:edit')")
    @Log(title = "视频列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StVideoBase stVideoBase)
    {
        return toAjax(stVideoBaseService.updateStVideoBase(stVideoBase));
    }

    /**
     * 删除视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideoBase:remove')")
    @Log(title = "视频列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(stVideoBaseService.deleteStVideoBaseByIds(ids));
    }
}
