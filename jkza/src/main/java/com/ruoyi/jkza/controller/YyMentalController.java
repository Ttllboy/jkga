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
import com.ruoyi.jkza.domain.YyMental;
import com.ruoyi.jkza.service.IYyMentalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 精神病人Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/yyMmental")
public class YyMentalController extends BaseController
{
    @Autowired
    private IYyMentalService yyMentalService;

    /**
     * 查询精神病人列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyMmental:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyMental yyMental)
    {
        startPage();
        List<YyMental> list = yyMentalService.selectYyMentalList(yyMental);
        return getDataTable(list);
    }

    /**
     * 导出精神病人列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyMmental:export')")
    @Log(title = "精神病人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyMental yyMental)
    {
        List<YyMental> list = yyMentalService.selectYyMentalList(yyMental);
        ExcelUtil<YyMental> util = new ExcelUtil<YyMental>(YyMental.class);
        util.exportExcel(response, list, "精神病人数据");
    }

    /**
     * 获取精神病人详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyMmental:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyMentalService.selectYyMentalById(id));
    }

    /**
     * 新增精神病人
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyMmental:add')")
    @Log(title = "精神病人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyMental yyMental)
    {
        return toAjax(yyMentalService.insertYyMental(yyMental));
    }

    /**
     * 修改精神病人
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyMmental:edit')")
    @Log(title = "精神病人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyMental yyMental)
    {
        return toAjax(yyMentalService.updateYyMental(yyMental));
    }

    /**
     * 删除精神病人
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyMmental:remove')")
    @Log(title = "精神病人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyMentalService.deleteYyMentalByIds(ids));
    }
}
