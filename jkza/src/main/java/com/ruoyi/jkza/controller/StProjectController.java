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
import com.ruoyi.jkza.domain.StProject;
import com.ruoyi.jkza.service.IStProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目基本信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-09
 */
@RestController
@RequestMapping("/jkza/fxStProject")
public class StProjectController extends BaseController
{
    @Autowired
    private IStProjectService stProjectService;

    /**
     * 查询项目基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:fxStProject:list')")
    @GetMapping("/list")
    public TableDataInfo list(StProject stProject)
    {
        startPage();
        List<StProject> list = stProjectService.selectStProjectList(stProject);
        return getDataTable(list);
    }

    /**
     * 导出项目基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:fxStProject:export')")
    @Log(title = "项目基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StProject stProject)
    {
        List<StProject> list = stProjectService.selectStProjectList(stProject);
        ExcelUtil<StProject> util = new ExcelUtil<StProject>(StProject.class);
        util.exportExcel(response, list, "项目基本信息数据");
    }

    /**
     * 获取项目基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:fxStProject:query')")
    @GetMapping(value = "/{guid}")
    public AjaxResult getInfo(@PathVariable("guid") String guid)
    {
        return AjaxResult.success(stProjectService.selectStProjectByGuid(guid));
    }

    /**
     * 新增项目基本信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:fxStProject:add')")
    @Log(title = "项目基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StProject stProject)
    {
        return toAjax(stProjectService.insertStProject(stProject));
    }

    /**
     * 修改项目基本信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:fxStProject:edit')")
    @Log(title = "项目基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StProject stProject)
    {
        return toAjax(stProjectService.updateStProject(stProject));
    }

    /**
     * 删除项目基本信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:fxStProject:remove')")
    @Log(title = "项目基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{guids}")
    public AjaxResult remove(@PathVariable String[] guids)
    {
        return toAjax(stProjectService.deleteStProjectByGuids(guids));
    }
}
