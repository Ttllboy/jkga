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
import com.ruoyi.jkza.domain.GdWorkerCheck;
import com.ruoyi.jkza.service.IGdWorkerCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@RestController
@RequestMapping("/jkza/workerCheck")
public class GdWorkerCheckController extends BaseController
{
    @Autowired
    private IGdWorkerCheckService gdWorkerCheckService;

    /**
     * 查询考勤信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:workerCheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdWorkerCheck gdWorkerCheck)
    {
        startPage();
        List<GdWorkerCheck> list = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
        return getDataTable(list);
    }

    /**
     * 导出考勤信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:workerCheck:export')")
    @Log(title = "考勤信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdWorkerCheck gdWorkerCheck)
    {
        List<GdWorkerCheck> list = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
        ExcelUtil<GdWorkerCheck> util = new ExcelUtil<GdWorkerCheck>(GdWorkerCheck.class);
        util.exportExcel(response, list, "考勤信息数据");
    }

    /**
     * 获取考勤信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:workerCheck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdWorkerCheckService.selectGdWorkerCheckById(id));
    }

    /**
     * 新增考勤信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:workerCheck:add')")
    @Log(title = "考勤信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdWorkerCheck gdWorkerCheck)
    {
        gdWorkerCheck.setDataType(1);
        return toAjax(gdWorkerCheckService.insertGdWorkerCheck(gdWorkerCheck));
    }

    /**
     * 修改考勤信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:workerCheck:edit')")
    @Log(title = "考勤信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdWorkerCheck gdWorkerCheck)
    {
        return toAjax(gdWorkerCheckService.updateGdWorkerCheck(gdWorkerCheck));
    }

    /**
     * 删除考勤信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:workerCheck:remove')")
    @Log(title = "考勤信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdWorkerCheckService.deleteGdWorkerCheckByIds(ids));
    }
}
