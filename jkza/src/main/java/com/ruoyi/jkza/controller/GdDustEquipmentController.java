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
import com.ruoyi.jkza.domain.GdDustEquipment;
import com.ruoyi.jkza.service.IGdDustEquipmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扬尘设备Controller
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
@RestController
@RequestMapping("/jkza/gdDustEquipment")
public class GdDustEquipmentController extends BaseController
{
    @Autowired
    private IGdDustEquipmentService gdDustEquipmentService;

    /**
     * 查询扬尘设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustEquipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdDustEquipment gdDustEquipment)
    {
        startPage();
        List<GdDustEquipment> list = gdDustEquipmentService.selectGdDustEquipmentList(gdDustEquipment);
        return getDataTable(list);
    }

    /**
     * 导出扬尘设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustEquipment:export')")
    @Log(title = "扬尘设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdDustEquipment gdDustEquipment)
    {
        List<GdDustEquipment> list = gdDustEquipmentService.selectGdDustEquipmentList(gdDustEquipment);
        ExcelUtil<GdDustEquipment> util = new ExcelUtil<GdDustEquipment>(GdDustEquipment.class);
        util.exportExcel(response, list, "扬尘设备数据");
    }

    /**
     * 获取扬尘设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustEquipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdDustEquipmentService.selectGdDustEquipmentById(id));
    }

    /**
     * 新增扬尘设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustEquipment:add')")
    @Log(title = "扬尘设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdDustEquipment gdDustEquipment)
    {
        return toAjax(gdDustEquipmentService.insertGdDustEquipment(gdDustEquipment));
    }

    /**
     * 修改扬尘设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustEquipment:edit')")
    @Log(title = "扬尘设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdDustEquipment gdDustEquipment)
    {
        return toAjax(gdDustEquipmentService.updateGdDustEquipment(gdDustEquipment));
    }

    /**
     * 删除扬尘设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustEquipment:remove')")
    @Log(title = "扬尘设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdDustEquipmentService.deleteGdDustEquipmentByIds(ids));
    }
}
