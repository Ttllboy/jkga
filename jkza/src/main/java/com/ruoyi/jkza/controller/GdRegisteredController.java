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
import com.ruoyi.jkza.domain.GdRegistered;
import com.ruoyi.jkza.service.IGdRegisteredService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 登记信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@RestController
@RequestMapping("/jkza/registered")
public class GdRegisteredController extends BaseController
{
    @Autowired
    private IGdRegisteredService gdRegisteredService;

    /**
     * 查询登记信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:registered:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdRegistered gdRegistered)
    {
        startPage();
        List<GdRegistered> list = gdRegisteredService.selectGdRegisteredList(gdRegistered);
        return getDataTable(list);
    }

    /**
     * 导出登记信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:registered:export')")
    @Log(title = "登记信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdRegistered gdRegistered)
    {
        List<GdRegistered> list = gdRegisteredService.selectGdRegisteredList(gdRegistered);
        ExcelUtil<GdRegistered> util = new ExcelUtil<GdRegistered>(GdRegistered.class);
        util.exportExcel(response, list, "登记信息数据");
    }

    /**
     * 获取登记信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:registered:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdRegisteredService.selectGdRegisteredById(id));
    }

    /**
     * 新增登记信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:registered:add')")
    @Log(title = "登记信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdRegistered gdRegistered)
    {
        gdRegistered.setDataType(1);
        return toAjax(gdRegisteredService.insertGdRegistered(gdRegistered));
    }

    /**
     * 修改登记信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:registered:edit')")
    @Log(title = "登记信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdRegistered gdRegistered)
    {
        return toAjax(gdRegisteredService.updateGdRegistered(gdRegistered));
    }

    /**
     * 删除登记信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:registered:remove')")
    @Log(title = "登记信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdRegisteredService.deleteGdRegisteredByIds(ids));
    }
}
