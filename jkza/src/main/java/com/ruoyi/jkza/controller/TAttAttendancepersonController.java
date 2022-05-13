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
import com.ruoyi.jkza.domain.TAttAttendanceperson;
import com.ruoyi.jkza.service.ITAttAttendancepersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 管理人员信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/jkza/FxAttendancePerson")
public class TAttAttendancepersonController extends BaseController
{
    @Autowired
    private ITAttAttendancepersonService tAttAttendancepersonService;

    /**
     * 查询管理人员信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancePerson:list')")
    @GetMapping("/list")
    public TableDataInfo list(TAttAttendanceperson tAttAttendanceperson)
    {
        startPage();
        List<TAttAttendanceperson> list = tAttAttendancepersonService.selectTAttAttendancepersonList(tAttAttendanceperson);
        return getDataTable(list);
    }

    /**
     * 导出管理人员信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancePerson:export')")
    @Log(title = "管理人员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TAttAttendanceperson tAttAttendanceperson)
    {
        List<TAttAttendanceperson> list = tAttAttendancepersonService.selectTAttAttendancepersonList(tAttAttendanceperson);
        ExcelUtil<TAttAttendanceperson> util = new ExcelUtil<TAttAttendanceperson>(TAttAttendanceperson.class);
        util.exportExcel(response, list, "管理人员信息数据");
    }

    /**
     * 获取管理人员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancePerson:query')")
    @GetMapping(value = "/{projectguid}")
    public AjaxResult getInfo(@PathVariable("projectguid") String projectguid)
    {
        return AjaxResult.success(tAttAttendancepersonService.selectTAttAttendancepersonByProjectguid(projectguid));
    }

    /**
     * 新增管理人员信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancePerson:add')")
    @Log(title = "管理人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAttAttendanceperson tAttAttendanceperson)
    {
        return toAjax(tAttAttendancepersonService.insertTAttAttendanceperson(tAttAttendanceperson));
    }

    /**
     * 修改管理人员信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancePerson:edit')")
    @Log(title = "管理人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAttAttendanceperson tAttAttendanceperson)
    {
        return toAjax(tAttAttendancepersonService.updateTAttAttendanceperson(tAttAttendanceperson));
    }

    /**
     * 删除管理人员信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxAttendancePerson:remove')")
    @Log(title = "管理人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectguids}")
    public AjaxResult remove(@PathVariable String[] projectguids)
    {
        return toAjax(tAttAttendancepersonService.deleteTAttAttendancepersonByProjectguids(projectguids));
    }
}
