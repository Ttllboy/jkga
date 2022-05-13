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
import com.ruoyi.jkza.domain.YjVideoBase;
import com.ruoyi.jkza.service.IYjVideoBaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频列表Controller
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
@RestController
@RequestMapping("/jkza/yjVideoBase")
public class YjVideoBaseController extends BaseController
{
    @Autowired
    private IYjVideoBaseService yjVideoBaseService;

    /**
     * 查询视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideoBase:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjVideoBase yjVideoBase)
    {
        startPage();
        List<YjVideoBase> list = yjVideoBaseService.selectYjVideoBaseList(yjVideoBase);
        return getDataTable(list);
    }

    /**
     * 导出视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideoBase:export')")
    @Log(title = "视频列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjVideoBase yjVideoBase)
    {
        List<YjVideoBase> list = yjVideoBaseService.selectYjVideoBaseList(yjVideoBase);
        ExcelUtil<YjVideoBase> util = new ExcelUtil<YjVideoBase>(YjVideoBase.class);
        util.exportExcel(response, list, "视频列表数据");
    }

    /**
     * 获取视频列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideoBase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjVideoBaseService.selectYjVideoBaseById(id));
    }

    /**
     * 新增视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideoBase:add')")
    @Log(title = "视频列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjVideoBase yjVideoBase)
    {
        return toAjax(yjVideoBaseService.insertYjVideoBase(yjVideoBase));
    }

    /**
     * 修改视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideoBase:edit')")
    @Log(title = "视频列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjVideoBase yjVideoBase)
    {
        return toAjax(yjVideoBaseService.updateYjVideoBase(yjVideoBase));
    }

    /**
     * 删除视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideoBase:remove')")
    @Log(title = "视频列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(yjVideoBaseService.deleteYjVideoBaseByIds(ids));
    }
}
