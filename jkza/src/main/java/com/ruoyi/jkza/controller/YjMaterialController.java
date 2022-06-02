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
import com.ruoyi.jkza.domain.YjMaterial;
import com.ruoyi.jkza.service.IYjMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物资信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-27
 */
@RestController
@RequestMapping("/jkza/yjMaterial")
public class YjMaterialController extends BaseController
{
    @Autowired
    private IYjMaterialService yjMaterialService;

    /**
     * 查询物资信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjMaterial:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjMaterial yjMaterial)
    {
        startPage();
        List<YjMaterial> list = yjMaterialService.selectYjMaterialList(yjMaterial);
        return getDataTable(list);
    }

    /**
     * 导出物资信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjMaterial:export')")
    @Log(title = "物资信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjMaterial yjMaterial)
    {
        List<YjMaterial> list = yjMaterialService.selectYjMaterialList(yjMaterial);
        ExcelUtil<YjMaterial> util = new ExcelUtil<YjMaterial>(YjMaterial.class);
        util.exportExcel(response, list, "物资信息数据");
    }

    /**
     * 获取物资信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjMaterial:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjMaterialService.selectYjMaterialById(id));
    }

    /**
     * 新增物资信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjMaterial:add')")
    @Log(title = "物资信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjMaterial yjMaterial)
    {
        return toAjax(yjMaterialService.insertYjMaterial(yjMaterial));
    }

    /**
     * 修改物资信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjMaterial:edit')")
    @Log(title = "物资信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjMaterial yjMaterial)
    {
        return toAjax(yjMaterialService.updateYjMaterial(yjMaterial));
    }

    /**
     * 删除物资信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjMaterial:remove')")
    @Log(title = "物资信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjMaterialService.deleteYjMaterialByIds(ids));
    }
}
