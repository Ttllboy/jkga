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
import com.ruoyi.jkza.domain.GdKeyPerson;
import com.ruoyi.jkza.service.IGdKeyPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 重点人员预警Controller
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@RestController
@RequestMapping("/jkza/keyPerson")
public class GdKeyPersonController extends BaseController
{
    @Autowired
    private IGdKeyPersonService gdKeyPersonService;

    /**
     * 查询重点人员预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:keyPerson:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdKeyPerson gdKeyPerson)
    {
        startPage();
        List<GdKeyPerson> list = gdKeyPersonService.selectGdKeyPersonList(gdKeyPerson);
        return getDataTable(list);
    }

    /**
     * 导出重点人员预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:keyPerson:export')")
    @Log(title = "重点人员预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdKeyPerson gdKeyPerson)
    {
        List<GdKeyPerson> list = gdKeyPersonService.selectGdKeyPersonList(gdKeyPerson);
        ExcelUtil<GdKeyPerson> util = new ExcelUtil<GdKeyPerson>(GdKeyPerson.class);
        util.exportExcel(response, list, "重点人员预警数据");
    }

    /**
     * 获取重点人员预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:keyPerson:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdKeyPersonService.selectGdKeyPersonById(id));
    }

    /**
     * 新增重点人员预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:keyPerson:add')")
    @Log(title = "重点人员预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdKeyPerson gdKeyPerson)
    {
        gdKeyPerson.setDataType(1);
        gdKeyPerson.setWarnType("重点人员预警");
        return toAjax(gdKeyPersonService.insertGdKeyPerson(gdKeyPerson));
    }

    /**
     * 修改重点人员预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:keyPerson:edit')")
    @Log(title = "重点人员预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdKeyPerson gdKeyPerson)
    {
        return toAjax(gdKeyPersonService.updateGdKeyPerson(gdKeyPerson));
    }

    /**
     * 删除重点人员预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:keyPerson:remove')")
    @Log(title = "重点人员预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdKeyPersonService.deleteGdKeyPersonByIds(ids));
    }
}
