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
import com.ruoyi.jkza.domain.StWarnProportion;
import com.ruoyi.jkza.service.IStWarnProportionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预警占比Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stWarnProportion")
public class StWarnProportionController extends BaseController
{
    @Autowired
    private IStWarnProportionService stWarnProportionService;

    /**
     * 查询预警占比列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarnProportion:list')")
    @GetMapping("/list")
    public TableDataInfo list(StWarnProportion stWarnProportion)
    {
        startPage();
        List<StWarnProportion> list = stWarnProportionService.selectStWarnProportionList(stWarnProportion);
        return getDataTable(list);
    }

    /**
     * 导出预警占比列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarnProportion:export')")
    @Log(title = "预警占比", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StWarnProportion stWarnProportion)
    {
        List<StWarnProportion> list = stWarnProportionService.selectStWarnProportionList(stWarnProportion);
        ExcelUtil<StWarnProportion> util = new ExcelUtil<StWarnProportion>(StWarnProportion.class);
        util.exportExcel(response, list, "预警占比数据");
    }

    /**
     * 获取预警占比详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarnProportion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stWarnProportionService.selectStWarnProportionById(id));
    }

    /**
     * 新增预警占比
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarnProportion:add')")
    @Log(title = "预警占比", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StWarnProportion stWarnProportion)
    {
        return toAjax(stWarnProportionService.insertStWarnProportion(stWarnProportion));
    }

    /**
     * 修改预警占比
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarnProportion:edit')")
    @Log(title = "预警占比", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StWarnProportion stWarnProportion)
    {
        return toAjax(stWarnProportionService.updateStWarnProportion(stWarnProportion));
    }

    /**
     * 删除预警占比
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWarnProportion:remove')")
    @Log(title = "预警占比", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(stWarnProportionService.deleteStWarnProportionByIds(ids));
    }
}
