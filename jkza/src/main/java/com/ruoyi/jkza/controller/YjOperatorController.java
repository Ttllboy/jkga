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
import com.ruoyi.jkza.domain.YjOperator;
import com.ruoyi.jkza.service.IYjOperatorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 值班人员Controller
 * 
 * @author ruoyi
 * @date 2022-04-26
 */
@RestController
@RequestMapping("/jkza/yjOperator")
public class YjOperatorController extends BaseController
{
    @Autowired
    private IYjOperatorService yjOperatorService;

    /**
     * 查询值班人员列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjOperator:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjOperator yjOperator)
    {
        startPage();
        List<YjOperator> list = yjOperatorService.selectYjOperatorList(yjOperator);
        return getDataTable(list);
    }

    /**
     * 导出值班人员列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjOperator:export')")
    @Log(title = "值班人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjOperator yjOperator)
    {
        List<YjOperator> list = yjOperatorService.selectYjOperatorList(yjOperator);
        ExcelUtil<YjOperator> util = new ExcelUtil<YjOperator>(YjOperator.class);
        util.exportExcel(response, list, "值班人员数据");
    }

    /**
     * 获取值班人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjOperator:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjOperatorService.selectYjOperatorById(id));
    }

    /**
     * 新增值班人员
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjOperator:add')")
    @Log(title = "值班人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjOperator yjOperator)
    {
        return toAjax(yjOperatorService.insertYjOperator(yjOperator));
    }

    /**
     * 修改值班人员
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjOperator:edit')")
    @Log(title = "值班人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjOperator yjOperator)
    {
        return toAjax(yjOperatorService.updateYjOperator(yjOperator));
    }

    /**
     * 删除值班人员
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjOperator:remove')")
    @Log(title = "值班人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjOperatorService.deleteYjOperatorByIds(ids));
    }
}
