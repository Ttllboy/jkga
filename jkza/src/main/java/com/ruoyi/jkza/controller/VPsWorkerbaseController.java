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
import com.ruoyi.jkza.domain.VPsWorkerbase;
import com.ruoyi.jkza.service.IVPsWorkerbaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工人信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@RestController
@RequestMapping("/jkza/FxWorkerbase")
public class VPsWorkerbaseController extends BaseController
{
    @Autowired
    private IVPsWorkerbaseService vPsWorkerbaseService;

    /**
     * 查询工人信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxWorkerbase:list')")
    @GetMapping("/list")
    public TableDataInfo list(VPsWorkerbase vPsWorkerbase)
    {
        startPage();
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
        return getDataTable(list);
    }

    /**
     * 导出工人信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxWorkerbase:export')")
    @Log(title = "工人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VPsWorkerbase vPsWorkerbase)
    {
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
        ExcelUtil<VPsWorkerbase> util = new ExcelUtil<VPsWorkerbase>(VPsWorkerbase.class);
        util.exportExcel(response, list, "工人信息数据");
    }

    /**
     * 获取工人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxWorkerbase:query')")
    @GetMapping(value = "/{personname}")
    public AjaxResult getInfo(@PathVariable("personname") String personname)
    {
        return AjaxResult.success(vPsWorkerbaseService.selectVPsWorkerbaseByPersonname(personname));
    }

    /**
     * 新增工人信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxWorkerbase:add')")
    @Log(title = "工人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VPsWorkerbase vPsWorkerbase)
    {
        return toAjax(vPsWorkerbaseService.insertVPsWorkerbase(vPsWorkerbase));
    }

    /**
     * 修改工人信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxWorkerbase:edit')")
    @Log(title = "工人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VPsWorkerbase vPsWorkerbase)
    {
        return toAjax(vPsWorkerbaseService.updateVPsWorkerbase(vPsWorkerbase));
    }

    /**
     * 删除工人信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxWorkerbase:remove')")
    @Log(title = "工人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{personnames}")
    public AjaxResult remove(@PathVariable String[] personnames)
    {
        return toAjax(vPsWorkerbaseService.deleteVPsWorkerbaseByPersonnames(personnames));
    }
}
