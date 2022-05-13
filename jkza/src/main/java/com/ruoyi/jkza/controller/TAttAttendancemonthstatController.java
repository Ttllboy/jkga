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
import com.ruoyi.jkza.domain.TAttAttendancemonthstat;
import com.ruoyi.jkza.service.ITAttAttendancemonthstatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 管理人员考勤数据Controller
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/jkza/FxAttendancemonthstat")
public class TAttAttendancemonthstatController extends BaseController
{
    @Autowired
    private ITAttAttendancemonthstatService tAttAttendancemonthstatService;

    /**
     * 查询管理人员考勤数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancemonthstat:list')")
    @GetMapping("/list")
    public TableDataInfo list(TAttAttendancemonthstat tAttAttendancemonthstat)
    {
        startPage();
        List<TAttAttendancemonthstat> list = tAttAttendancemonthstatService.selectTAttAttendancemonthstatList(tAttAttendancemonthstat);
        return getDataTable(list);
    }

    /**
     * 导出管理人员考勤数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancemonthstat:export')")
    @Log(title = "管理人员考勤数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TAttAttendancemonthstat tAttAttendancemonthstat)
    {
        List<TAttAttendancemonthstat> list = tAttAttendancemonthstatService.selectTAttAttendancemonthstatList(tAttAttendancemonthstat);
        ExcelUtil<TAttAttendancemonthstat> util = new ExcelUtil<TAttAttendancemonthstat>(TAttAttendancemonthstat.class);
        util.exportExcel(response, list, "管理人员考勤数据数据");
    }

    /**
     * 获取管理人员考勤数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancemonthstat:query')")
    @GetMapping(value = "/{projectguid}")
    public AjaxResult getInfo(@PathVariable("projectguid") String projectguid)
    {
        return AjaxResult.success(tAttAttendancemonthstatService.selectTAttAttendancemonthstatByProjectguid(projectguid));
    }

    /**
     * 新增管理人员考勤数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancemonthstat:add')")
    @Log(title = "管理人员考勤数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAttAttendancemonthstat tAttAttendancemonthstat)
    {
        return toAjax(tAttAttendancemonthstatService.insertTAttAttendancemonthstat(tAttAttendancemonthstat));
    }

    /**
     * 修改管理人员考勤数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancemonthstat:edit')")
    @Log(title = "管理人员考勤数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAttAttendancemonthstat tAttAttendancemonthstat)
    {
        return toAjax(tAttAttendancemonthstatService.updateTAttAttendancemonthstat(tAttAttendancemonthstat));
    }

    /**
     * 删除管理人员考勤数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancemonthstat:remove')")
    @Log(title = "管理人员考勤数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectguids}")
    public AjaxResult remove(@PathVariable String[] projectguids)
    {
        return toAjax(tAttAttendancemonthstatService.deleteTAttAttendancemonthstatByProjectguids(projectguids));
    }
}
