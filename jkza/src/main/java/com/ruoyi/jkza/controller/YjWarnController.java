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
import com.ruoyi.jkza.domain.YjWarn;
import com.ruoyi.jkza.service.IYjWarnService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预警信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/jkza/yjWarn")
public class YjWarnController extends BaseController
{
    @Autowired
    private IYjWarnService yjWarnService;

    /**
     * 查询预警信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjWarn:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjWarn yjWarn)
    {
        startPage();
        List<YjWarn> list = yjWarnService.selectYjWarnList(yjWarn);
        return getDataTable(list);
    }

    /**
     * 导出预警信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjWarn:export')")
    @Log(title = "预警信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjWarn yjWarn)
    {
        List<YjWarn> list = yjWarnService.selectYjWarnList(yjWarn);
        ExcelUtil<YjWarn> util = new ExcelUtil<YjWarn>(YjWarn.class);
        util.exportExcel(response, list, "预警信息数据");
    }

    /**
     * 获取预警信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjWarn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjWarnService.selectYjWarnById(id));
    }

    /**
     * 新增预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjWarn:add')")
    @Log(title = "预警信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjWarn yjWarn)
    {
        return toAjax(yjWarnService.insertYjWarn(yjWarn));
    }

    /**
     * 修改预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjWarn:edit')")
    @Log(title = "预警信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjWarn yjWarn)
    {
        return toAjax(yjWarnService.updateYjWarn(yjWarn));
    }

    /**
     * 删除预警信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjWarn:remove')")
    @Log(title = "预警信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjWarnService.deleteYjWarnByIds(ids));
    }
}
