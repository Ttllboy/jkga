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
import com.ruoyi.jkza.domain.GdCheckLose;
import com.ruoyi.jkza.service.IGdCheckLoseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤缺失Controller
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@RestController
@RequestMapping("/jkza/checkLose")
public class GdCheckLoseController extends BaseController
{
    @Autowired
    private IGdCheckLoseService gdCheckLoseService;

    /**
     * 查询考勤缺失列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:checkLose:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdCheckLose gdCheckLose)
    {
        startPage();
        List<GdCheckLose> list = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);
        return getDataTable(list);
    }

    /**
     * 导出考勤缺失列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:checkLose:export')")
    @Log(title = "考勤缺失", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdCheckLose gdCheckLose)
    {
        List<GdCheckLose> list = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);
        ExcelUtil<GdCheckLose> util = new ExcelUtil<GdCheckLose>(GdCheckLose.class);
        util.exportExcel(response, list, "考勤缺失数据");
    }

    /**
     * 获取考勤缺失详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:checkLose:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdCheckLoseService.selectGdCheckLoseById(id));
    }

    /**
     * 新增考勤缺失
     */
    @PreAuthorize("@ss.hasPermi('jkza:checkLose:add')")
    @Log(title = "考勤缺失", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdCheckLose gdCheckLose)
    {
        gdCheckLose.setDataType(1);
        return toAjax(gdCheckLoseService.insertGdCheckLose(gdCheckLose));
    }

    /**
     * 修改考勤缺失
     */
    @PreAuthorize("@ss.hasPermi('jkza:checkLose:edit')")
    @Log(title = "考勤缺失", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdCheckLose gdCheckLose)
    {
        return toAjax(gdCheckLoseService.updateGdCheckLose(gdCheckLose));
    }

    /**
     * 删除考勤缺失
     */
    @PreAuthorize("@ss.hasPermi('jkza:checkLose:remove')")
    @Log(title = "考勤缺失", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdCheckLoseService.deleteGdCheckLoseByIds(ids));
    }
}
