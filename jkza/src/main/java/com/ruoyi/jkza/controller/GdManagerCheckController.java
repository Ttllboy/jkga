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
import com.ruoyi.jkza.domain.GdManagerCheck;
import com.ruoyi.jkza.service.IGdManagerCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 管理人员考勤Controller
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@RestController
@RequestMapping("/jkza/managerCheck")
public class GdManagerCheckController extends BaseController
{
    @Autowired
    private IGdManagerCheckService gdManagerCheckService;

    /**
     * 查询管理人员考勤列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:managerCheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdManagerCheck gdManagerCheck)
    {
        startPage();
        List<GdManagerCheck> list = gdManagerCheckService.selectGdManagerCheckList(gdManagerCheck);
        return getDataTable(list);
    }

    /**
     * 导出管理人员考勤列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:managerCheck:export')")
    @Log(title = "管理人员考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdManagerCheck gdManagerCheck)
    {
        List<GdManagerCheck> list = gdManagerCheckService.selectGdManagerCheckList(gdManagerCheck);
        ExcelUtil<GdManagerCheck> util = new ExcelUtil<GdManagerCheck>(GdManagerCheck.class);
        util.exportExcel(response, list, "管理人员考勤数据");
    }

    /**
     * 获取管理人员考勤详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:managerCheck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdManagerCheckService.selectGdManagerCheckById(id));
    }

    /**
     * 新增管理人员考勤
     */
    @PreAuthorize("@ss.hasPermi('jkza:managerCheck:add')")
    @Log(title = "管理人员考勤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdManagerCheck gdManagerCheck)
    {
        gdManagerCheck.setDataType(1);
        return toAjax(gdManagerCheckService.insertGdManagerCheck(gdManagerCheck));
    }

    /**
     * 修改管理人员考勤
     */
    @PreAuthorize("@ss.hasPermi('jkza:managerCheck:edit')")
    @Log(title = "管理人员考勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdManagerCheck gdManagerCheck)
    {
        return toAjax(gdManagerCheckService.updateGdManagerCheck(gdManagerCheck));
    }

    /**
     * 删除管理人员考勤
     */
    @PreAuthorize("@ss.hasPermi('jkza:managerCheck:remove')")
    @Log(title = "管理人员考勤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdManagerCheckService.deleteGdManagerCheckByIds(ids));
    }
}
