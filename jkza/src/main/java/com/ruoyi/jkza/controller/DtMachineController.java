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
import com.ruoyi.jkza.domain.DtMachine;
import com.ruoyi.jkza.service.IDtMachineService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 起重设备Controller
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@RestController
@RequestMapping("/jkza/DtMachine")
public class DtMachineController extends BaseController
{
    @Autowired
    private IDtMachineService dtMachineService;

    /**
     * 查询起重设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:DtMachine:list')")
    @GetMapping("/list")
    public TableDataInfo list(DtMachine dtMachine)
    {
        startPage();
        List<DtMachine> list = dtMachineService.selectDtMachineList(dtMachine);
        return getDataTable(list);
    }

    /**
     * 导出起重设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:DtMachine:export')")
    @Log(title = "起重设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DtMachine dtMachine)
    {
        List<DtMachine> list = dtMachineService.selectDtMachineList(dtMachine);
        ExcelUtil<DtMachine> util = new ExcelUtil<DtMachine>(DtMachine.class);
        util.exportExcel(response, list, "起重设备数据");
    }

    /**
     * 获取起重设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:DtMachine:query')")
    @GetMapping(value = "/{install}")
    public AjaxResult getInfo(@PathVariable("install") Long install)
    {
        return AjaxResult.success(dtMachineService.selectDtMachineByInstall(install));
    }

    /**
     * 新增起重设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:DtMachine:add')")
    @Log(title = "起重设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DtMachine dtMachine)
    {
        return toAjax(dtMachineService.insertDtMachine(dtMachine));
    }

    /**
     * 修改起重设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:DtMachine:edit')")
    @Log(title = "起重设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DtMachine dtMachine)
    {
        return toAjax(dtMachineService.updateDtMachine(dtMachine));
    }

    /**
     * 删除起重设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:DtMachine:remove')")
    @Log(title = "起重设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{installs}")
    public AjaxResult remove(@PathVariable Long[] installs)
    {
        return toAjax(dtMachineService.deleteDtMachineByInstalls(installs));
    }
}
