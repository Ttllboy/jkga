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
import com.ruoyi.jkza.domain.TPersoninfo;
import com.ruoyi.jkza.service.ITPersoninfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工人考勤Controller
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
@RestController
@RequestMapping("/jkza/FxPersoninfo")
public class TPersoninfoController extends BaseController
{
    @Autowired
    private ITPersoninfoService tPersoninfoService;

    /**
     * 查询工人考勤列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxPersoninfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPersoninfo tPersoninfo)
    {
        startPage();
        List<TPersoninfo> list = tPersoninfoService.selectTPersoninfoList(tPersoninfo);
        return getDataTable(list);
    }

    /**
     * 导出工人考勤列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxPersoninfo:export')")
    @Log(title = "工人考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPersoninfo tPersoninfo)
    {
        List<TPersoninfo> list = tPersoninfoService.selectTPersoninfoList(tPersoninfo);
        ExcelUtil<TPersoninfo> util = new ExcelUtil<TPersoninfo>(TPersoninfo.class);
        util.exportExcel(response, list, "工人考勤数据");
    }

    /**
     * 获取工人考勤详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxPersoninfo:query')")
    @GetMapping(value = "/{projectguid}")
    public AjaxResult getInfo(@PathVariable("projectguid") String projectguid)
    {
        return AjaxResult.success(tPersoninfoService.selectTPersoninfoByProjectguid(projectguid));
    }

    /**
     * 新增工人考勤
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxPersoninfo:add')")
    @Log(title = "工人考勤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPersoninfo tPersoninfo)
    {
        return toAjax(tPersoninfoService.insertTPersoninfo(tPersoninfo));
    }

    /**
     * 修改工人考勤
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxPersoninfo:edit')")
    @Log(title = "工人考勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPersoninfo tPersoninfo)
    {
        return toAjax(tPersoninfoService.updateTPersoninfo(tPersoninfo));
    }

    /**
     * 删除工人考勤
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxPersoninfo:remove')")
    @Log(title = "工人考勤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectguids}")
    public AjaxResult remove(@PathVariable String[] projectguids)
    {
        return toAjax(tPersoninfoService.deleteTPersoninfoByProjectguids(projectguids));
    }
}
