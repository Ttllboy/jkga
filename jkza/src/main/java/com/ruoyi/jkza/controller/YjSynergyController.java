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
import com.ruoyi.jkza.domain.YjSynergy;
import com.ruoyi.jkza.service.IYjSynergyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 多跨协同Controller
 * 
 * @author ruoyi
 * @date 2022-06-24
 */
@RestController
@RequestMapping("/jkza/yjSynergy")
public class YjSynergyController extends BaseController
{
    @Autowired
    private IYjSynergyService yjSynergyService;

    /**
     * 查询多跨协同列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjSynergy:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjSynergy yjSynergy)
    {
        startPage();
        List<YjSynergy> list = yjSynergyService.selectYjSynergyList(yjSynergy);
        return getDataTable(list);
    }

    /**
     * 导出多跨协同列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjSynergy:export')")
    @Log(title = "多跨协同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjSynergy yjSynergy)
    {
        List<YjSynergy> list = yjSynergyService.selectYjSynergyList(yjSynergy);
        ExcelUtil<YjSynergy> util = new ExcelUtil<YjSynergy>(YjSynergy.class);
        util.exportExcel(response, list, "多跨协同数据");
    }

    /**
     * 获取多跨协同详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjSynergy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjSynergyService.selectYjSynergyById(id));
    }

    /**
     * 新增多跨协同
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjSynergy:add')")
    @Log(title = "多跨协同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjSynergy yjSynergy)
    {
        return toAjax(yjSynergyService.insertYjSynergy(yjSynergy));
    }

    /**
     * 修改多跨协同
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjSynergy:edit')")
    @Log(title = "多跨协同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjSynergy yjSynergy)
    {
        return toAjax(yjSynergyService.updateYjSynergy(yjSynergy));
    }

    /**
     * 删除多跨协同
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjSynergy:remove')")
    @Log(title = "多跨协同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjSynergyService.deleteYjSynergyByIds(ids));
    }
}
