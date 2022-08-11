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
import com.ruoyi.jkza.domain.YyPsychopathControl;
import com.ruoyi.jkza.service.IYyPsychopathControlService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 病人管控Controller
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@RestController
@RequestMapping("/jkza/patientControl")
public class YyPsychopathControlController extends BaseController
{
    @Autowired
    private IYyPsychopathControlService yyPsychopathControlService;

    /**
     * 查询病人管控列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:patientControl:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyPsychopathControl yyPsychopathControl)
    {
        startPage();
        List<YyPsychopathControl> list = yyPsychopathControlService.selectYyPsychopathControlList(yyPsychopathControl);
        return getDataTable(list);
    }

    /**
     * 导出病人管控列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:patientControl:export')")
    @Log(title = "病人管控", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyPsychopathControl yyPsychopathControl)
    {
        List<YyPsychopathControl> list = yyPsychopathControlService.selectYyPsychopathControlList(yyPsychopathControl);
        ExcelUtil<YyPsychopathControl> util = new ExcelUtil<YyPsychopathControl>(YyPsychopathControl.class);
        util.exportExcel(response, list, "病人管控数据");
    }

    /**
     * 获取病人管控详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:patientControl:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyPsychopathControlService.selectYyPsychopathControlById(id));
    }

    /**
     * 新增病人管控
     */
    @PreAuthorize("@ss.hasPermi('jkza:patientControl:add')")
    @Log(title = "病人管控", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyPsychopathControl yyPsychopathControl)
    {
        return toAjax(yyPsychopathControlService.insertYyPsychopathControl(yyPsychopathControl));
    }

    /**
     * 修改病人管控
     */
    @PreAuthorize("@ss.hasPermi('jkza:patientControl:edit')")
    @Log(title = "病人管控", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyPsychopathControl yyPsychopathControl)
    {
        return toAjax(yyPsychopathControlService.updateYyPsychopathControl(yyPsychopathControl));
    }

    /**
     * 删除病人管控
     */
    @PreAuthorize("@ss.hasPermi('jkza:patientControl:remove')")
    @Log(title = "病人管控", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyPsychopathControlService.deleteYyPsychopathControlByIds(ids));
    }
}
