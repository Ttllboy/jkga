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
import com.ruoyi.jkza.domain.MqBkyj;
import com.ruoyi.jkza.service.IMqBkyjService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 布控预警Controller
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@RestController
@RequestMapping("/jkza/bkyj")
public class MqBkyjController extends BaseController
{
    @Autowired
    private IMqBkyjService mqBkyjService;

    /**
     * 查询布控预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:bkyj:list')")
    @GetMapping("/list")
    public TableDataInfo list(MqBkyj mqBkyj)
    {
        startPage();
        List<MqBkyj> list = mqBkyjService.selectMqBkyjList(mqBkyj);
        return getDataTable(list);
    }

    /**
     * 导出布控预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:bkyj:export')")
    @Log(title = "布控预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MqBkyj mqBkyj)
    {
        List<MqBkyj> list = mqBkyjService.selectMqBkyjList(mqBkyj);
        ExcelUtil<MqBkyj> util = new ExcelUtil<MqBkyj>(MqBkyj.class);
        util.exportExcel(response, list, "布控预警数据");
    }

    /**
     * 获取布控预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:bkyj:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mqBkyjService.selectMqBkyjById(id));
    }

    /**
     * 新增布控预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:bkyj:add')")
    @Log(title = "布控预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MqBkyj mqBkyj)
    {
        return toAjax(mqBkyjService.insertMqBkyj(mqBkyj));
    }

    /**
     * 修改布控预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:bkyj:edit')")
    @Log(title = "布控预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MqBkyj mqBkyj)
    {
        return toAjax(mqBkyjService.updateMqBkyj(mqBkyj));
    }

    /**
     * 删除布控预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:bkyj:remove')")
    @Log(title = "布控预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mqBkyjService.deleteMqBkyjByIds(ids));
    }
}
