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
import com.ruoyi.jkza.domain.DtRectificationnotice;
import com.ruoyi.jkza.service.IDtRectificationnoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 整改信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@RestController
@RequestMapping("/jkza/FxRrectificationnotice")
public class DtRectificationnoticeController extends BaseController
{
    @Autowired
    private IDtRectificationnoticeService dtRectificationnoticeService;

    /**
     * 查询整改信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxRrectificationnotice:list')")
    @GetMapping("/list")
    public TableDataInfo list(DtRectificationnotice dtRectificationnotice)
    {
        startPage();
        List<DtRectificationnotice> list = dtRectificationnoticeService.selectDtRectificationnoticeList(dtRectificationnotice);
        return getDataTable(list);
    }

    /**
     * 导出整改信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxRrectificationnotice:export')")
    @Log(title = "整改信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DtRectificationnotice dtRectificationnotice)
    {
        List<DtRectificationnotice> list = dtRectificationnoticeService.selectDtRectificationnoticeList(dtRectificationnotice);
        ExcelUtil<DtRectificationnotice> util = new ExcelUtil<DtRectificationnotice>(DtRectificationnotice.class);
        util.exportExcel(response, list, "整改信息数据");
    }

    /**
     * 获取整改信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxRrectificationnotice:query')")
    @GetMapping(value = "/{projectguid}")
    public AjaxResult getInfo(@PathVariable("projectguid") String projectguid)
    {
        return AjaxResult.success(dtRectificationnoticeService.selectDtRectificationnoticeByProjectguid(projectguid));
    }

    /**
     * 新增整改信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxRrectificationnotice:add')")
    @Log(title = "整改信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DtRectificationnotice dtRectificationnotice)
    {
        return toAjax(dtRectificationnoticeService.insertDtRectificationnotice(dtRectificationnotice));
    }

    /**
     * 修改整改信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxRrectificationnotice:edit')")
    @Log(title = "整改信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DtRectificationnotice dtRectificationnotice)
    {
        return toAjax(dtRectificationnoticeService.updateDtRectificationnotice(dtRectificationnotice));
    }

    /**
     * 删除整改信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxRrectificationnotice:remove')")
    @Log(title = "整改信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectguids}")
    public AjaxResult remove(@PathVariable String[] projectguids)
    {
        return toAjax(dtRectificationnoticeService.deleteDtRectificationnoticeByProjectguids(projectguids));
    }
}
