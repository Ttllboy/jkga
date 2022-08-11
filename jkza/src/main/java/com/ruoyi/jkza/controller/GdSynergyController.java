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
import com.ruoyi.jkza.domain.GdSynergy;
import com.ruoyi.jkza.service.IGdSynergyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 整改通知Controller
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
@RestController
@RequestMapping("/jkza/synergy")
public class GdSynergyController extends BaseController
{
    @Autowired
    private IGdSynergyService gdSynergyService;

    /**
     * 查询整改通知列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:synergy:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdSynergy gdSynergy)
    {
        startPage();
        List<GdSynergy> list = gdSynergyService.selectGdSynergyList(gdSynergy);
        return getDataTable(list);
    }

    /**
     * 导出整改通知列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:synergy:export')")
    @Log(title = "整改通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdSynergy gdSynergy)
    {
        List<GdSynergy> list = gdSynergyService.selectGdSynergyList(gdSynergy);
        ExcelUtil<GdSynergy> util = new ExcelUtil<GdSynergy>(GdSynergy.class);
        util.exportExcel(response, list, "整改通知数据");
    }

    /**
     * 获取整改通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:synergy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdSynergyService.selectGdSynergyById(id));
    }

    /**
     * 新增整改通知
     */
    @PreAuthorize("@ss.hasPermi('jkza:synergy:add')")
    @Log(title = "整改通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdSynergy gdSynergy)
    {
        return toAjax(gdSynergyService.insertGdSynergy(gdSynergy));
    }

    /**
     * 修改整改通知
     */
    @PreAuthorize("@ss.hasPermi('jkza:synergy:edit')")
    @Log(title = "整改通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdSynergy gdSynergy)
    {
        return toAjax(gdSynergyService.updateGdSynergy(gdSynergy));
    }

    /**
     * 删除整改通知
     */
    @PreAuthorize("@ss.hasPermi('jkza:synergy:remove')")
    @Log(title = "整改通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdSynergyService.deleteGdSynergyByIds(ids));
    }
}
