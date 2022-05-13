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
import com.ruoyi.jkza.domain.YjMonitorEquipment;
import com.ruoyi.jkza.service.IYjMonitorEquipmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 监测设备Controller
 * 
 * @author ruoyi
 * @date 2022-04-17
 */
@RestController
@RequestMapping("/jkza/monitorEquipment")
public class YjMonitorEquipmentController extends BaseController
{
    @Autowired
    private IYjMonitorEquipmentService yjMonitorEquipmentService;

    /**
     * 查询监测设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:monitorEquipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjMonitorEquipment yjMonitorEquipment)
    {
        startPage();
        List<YjMonitorEquipment> list = yjMonitorEquipmentService.selectYjMonitorEquipmentList(yjMonitorEquipment);
        return getDataTable(list);
    }

    /**
     * 导出监测设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:monitorEquipment:export')")
    @Log(title = "监测设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjMonitorEquipment yjMonitorEquipment)
    {
        List<YjMonitorEquipment> list = yjMonitorEquipmentService.selectYjMonitorEquipmentList(yjMonitorEquipment);
        ExcelUtil<YjMonitorEquipment> util = new ExcelUtil<YjMonitorEquipment>(YjMonitorEquipment.class);
        util.exportExcel(response, list, "监测设备数据");
    }

    /**
     * 获取监测设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:monitorEquipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjMonitorEquipmentService.selectYjMonitorEquipmentById(id));
    }

    /**
     * 新增监测设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:monitorEquipment:add')")
    @Log(title = "监测设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjMonitorEquipment yjMonitorEquipment)
    {
        yjMonitorEquipment.setDataType(1);
        return toAjax(yjMonitorEquipmentService.insertYjMonitorEquipment(yjMonitorEquipment));
    }

    /**
     * 修改监测设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:monitorEquipment:edit')")
    @Log(title = "监测设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjMonitorEquipment yjMonitorEquipment)
    {
        return toAjax(yjMonitorEquipmentService.updateYjMonitorEquipment(yjMonitorEquipment));
    }

    /**
     * 删除监测设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:monitorEquipment:remove')")
    @Log(title = "监测设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjMonitorEquipmentService.deleteYjMonitorEquipmentByIds(ids));
    }
}
