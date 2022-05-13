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
import com.ruoyi.jkza.domain.StIncidentProcess;
import com.ruoyi.jkza.service.IStIncidentProcessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 事件处理Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stIncidentProcess")
public class StIncidentProcessController extends BaseController
{
    @Autowired
    private IStIncidentProcessService stIncidentProcessService;

    /**
     * 查询事件处理列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIncidentProcess:list')")
    @GetMapping("/list")
    public TableDataInfo list(StIncidentProcess stIncidentProcess)
    {
        startPage();
        List<StIncidentProcess> list = stIncidentProcessService.selectStIncidentProcessList(stIncidentProcess);
        return getDataTable(list);
    }

    /**
     * 导出事件处理列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIncidentProcess:export')")
    @Log(title = "事件处理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StIncidentProcess stIncidentProcess)
    {
        List<StIncidentProcess> list = stIncidentProcessService.selectStIncidentProcessList(stIncidentProcess);
        ExcelUtil<StIncidentProcess> util = new ExcelUtil<StIncidentProcess>(StIncidentProcess.class);
        util.exportExcel(response, list, "事件处理数据");
    }

    /**
     * 获取事件处理详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIncidentProcess:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stIncidentProcessService.selectStIncidentProcessById(id));
    }

    /**
     * 新增事件处理
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIncidentProcess:add')")
    @Log(title = "事件处理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StIncidentProcess stIncidentProcess)
    {
        return toAjax(stIncidentProcessService.insertStIncidentProcess(stIncidentProcess));
    }

    /**
     * 修改事件处理
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIncidentProcess:edit')")
    @Log(title = "事件处理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StIncidentProcess stIncidentProcess)
    {
        return toAjax(stIncidentProcessService.updateStIncidentProcess(stIncidentProcess));
    }

    /**
     * 删除事件处理
     */
    @PreAuthorize("@ss.hasPermi('jkza:stIncidentProcess:remove')")
    @Log(title = "事件处理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stIncidentProcessService.deleteStIncidentProcessByIds(ids));
    }
}
