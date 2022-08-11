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
import com.ruoyi.jkza.domain.StMassif;
import com.ruoyi.jkza.service.IStMassifService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地块数量Controller
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
@RestController
@RequestMapping("/jkza/stMassif")
public class StMassifController extends BaseController
{
    @Autowired
    private IStMassifService stMassifService;

    /**
     * 查询地块数量列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stMassif:list')")
    @GetMapping("/list")
    public TableDataInfo list(StMassif stMassif)
    {
        startPage();
        List<StMassif> list = stMassifService.selectStMassifList(stMassif);
        return getDataTable(list);
    }

    /**
     * 导出地块数量列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stMassif:export')")
    @Log(title = "地块数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StMassif stMassif)
    {
        List<StMassif> list = stMassifService.selectStMassifList(stMassif);
        ExcelUtil<StMassif> util = new ExcelUtil<StMassif>(StMassif.class);
        util.exportExcel(response, list, "地块数量数据");
    }

    /**
     * 获取地块数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stMassif:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stMassifService.selectStMassifById(id));
    }

    /**
     * 新增地块数量
     */
    @PreAuthorize("@ss.hasPermi('jkza:stMassif:add')")
    @Log(title = "地块数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StMassif stMassif)
    {
        return toAjax(stMassifService.insertStMassif(stMassif));
    }

    /**
     * 修改地块数量
     */
    @PreAuthorize("@ss.hasPermi('jkza:stMassif:edit')")
    @Log(title = "地块数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StMassif stMassif)
    {
        return toAjax(stMassifService.updateStMassif(stMassif));
    }

    /**
     * 删除地块数量
     */
    @PreAuthorize("@ss.hasPermi('jkza:stMassif:remove')")
    @Log(title = "地块数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stMassifService.deleteStMassifByIds(ids));
    }
}
