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
import com.ruoyi.jkza.domain.AdmYyCw;
import com.ruoyi.jkza.service.IAdmYyCwService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医院对应Controller
 * 
 * @author ruoyi
 * @date 2022-07-04
 */
@RestController
@RequestMapping("/jkza/YyCw")
public class AdmYyCwController extends BaseController
{
    @Autowired
    private IAdmYyCwService admYyCwService;

    /**
     * 查询医院对应列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:YyCw:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmYyCw admYyCw)
    {
        startPage();
        List<AdmYyCw> list = admYyCwService.selectAdmYyCwList(admYyCw);
        return getDataTable(list);
    }

    /**
     * 导出医院对应列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:YyCw:export')")
    @Log(title = "医院对应", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmYyCw admYyCw)
    {
        List<AdmYyCw> list = admYyCwService.selectAdmYyCwList(admYyCw);
        ExcelUtil<AdmYyCw> util = new ExcelUtil<AdmYyCw>(AdmYyCw.class);
        util.exportExcel(response, list, "医院对应数据");
    }

    /**
     * 获取医院对应详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:YyCw:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(admYyCwService.selectAdmYyCwById(id));
    }

    /**
     * 新增医院对应
     */
    @PreAuthorize("@ss.hasPermi('jkza:YyCw:add')")
    @Log(title = "医院对应", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmYyCw admYyCw)
    {
        return toAjax(admYyCwService.insertAdmYyCw(admYyCw));
    }

    /**
     * 修改医院对应
     */
    @PreAuthorize("@ss.hasPermi('jkza:YyCw:edit')")
    @Log(title = "医院对应", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmYyCw admYyCw)
    {
        return toAjax(admYyCwService.updateAdmYyCw(admYyCw));
    }

    /**
     * 删除医院对应
     */
    @PreAuthorize("@ss.hasPermi('jkza:YyCw:remove')")
    @Log(title = "医院对应", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(admYyCwService.deleteAdmYyCwByIds(ids));
    }
}
