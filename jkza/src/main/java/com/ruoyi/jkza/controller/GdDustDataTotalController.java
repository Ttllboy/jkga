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
import com.ruoyi.jkza.domain.GdDustDataTotal;
import com.ruoyi.jkza.service.IGdDustDataTotalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扬尘数据总Controller
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
@RestController
@RequestMapping("/jkza/gdDustDataTotal")
public class GdDustDataTotalController extends BaseController
{
    @Autowired
    private IGdDustDataTotalService gdDustDataTotalService;

    /**
     * 查询扬尘数据总列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustDataTotal:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdDustDataTotal gdDustDataTotal)
    {
        startPage();
        List<GdDustDataTotal> list = gdDustDataTotalService.selectGdDustDataTotalList(gdDustDataTotal);
        return getDataTable(list);
    }

    /**
     * 导出扬尘数据总列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustDataTotal:export')")
    @Log(title = "扬尘数据总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdDustDataTotal gdDustDataTotal)
    {
        List<GdDustDataTotal> list = gdDustDataTotalService.selectGdDustDataTotalList(gdDustDataTotal);
        ExcelUtil<GdDustDataTotal> util = new ExcelUtil<GdDustDataTotal>(GdDustDataTotal.class);
        util.exportExcel(response, list, "扬尘数据总数据");
    }

    /**
     * 获取扬尘数据总详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustDataTotal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdDustDataTotalService.selectGdDustDataTotalById(id));
    }

    /**
     * 新增扬尘数据总
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustDataTotal:add')")
    @Log(title = "扬尘数据总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdDustDataTotal gdDustDataTotal)
    {
        return toAjax(gdDustDataTotalService.insertGdDustDataTotal(gdDustDataTotal));
    }

    /**
     * 修改扬尘数据总
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustDataTotal:edit')")
    @Log(title = "扬尘数据总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdDustDataTotal gdDustDataTotal)
    {
        return toAjax(gdDustDataTotalService.updateGdDustDataTotal(gdDustDataTotal));
    }

    /**
     * 删除扬尘数据总
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdDustDataTotal:remove')")
    @Log(title = "扬尘数据总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdDustDataTotalService.deleteGdDustDataTotalByIds(ids));
    }
}
