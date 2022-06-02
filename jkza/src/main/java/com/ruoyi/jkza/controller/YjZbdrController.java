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
import com.ruoyi.jkza.domain.YjZbdr;
import com.ruoyi.jkza.service.IYjZbdrService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 值班导入Controller
 * 
 * @author ruoyi
 * @date 2022-05-25
 */
@RestController
@RequestMapping("/jkza/zbdr")
public class YjZbdrController extends BaseController
{
    @Autowired
    private IYjZbdrService yjZbdrService;

    /**
     * 查询值班导入列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:zbdr:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjZbdr yjZbdr)
    {
        startPage();
        List<YjZbdr> list = yjZbdrService.selectYjZbdrList(yjZbdr);
        return getDataTable(list);
    }

    /**
     * 导出值班导入列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:zbdr:export')")
    @Log(title = "值班导入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjZbdr yjZbdr)
    {
        List<YjZbdr> list = yjZbdrService.selectYjZbdrList(yjZbdr);
        ExcelUtil<YjZbdr> util = new ExcelUtil<YjZbdr>(YjZbdr.class);
        util.exportExcel(response, list, "值班导入数据");
    }

    /**
     * 获取值班导入详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:zbdr:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjZbdrService.selectYjZbdrById(id));
    }

    /**
     * 新增值班导入
     */
    @PreAuthorize("@ss.hasPermi('jkza:zbdr:add')")
    @Log(title = "值班导入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjZbdr yjZbdr)
    {
        return toAjax(yjZbdrService.insertYjZbdr(yjZbdr));
    }

    /**
     * 修改值班导入
     */
    @PreAuthorize("@ss.hasPermi('jkza:zbdr:edit')")
    @Log(title = "值班导入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjZbdr yjZbdr)
    {
        return toAjax(yjZbdrService.updateYjZbdr(yjZbdr));
    }

    /**
     * 删除值班导入
     */
    @PreAuthorize("@ss.hasPermi('jkza:zbdr:remove')")
    @Log(title = "值班导入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjZbdrService.deleteYjZbdrByIds(ids));
    }
}
