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
import com.ruoyi.jkza.domain.LtdqExcessivestatisticaldata;
import com.ruoyi.jkza.service.ILtdqExcessivestatisticaldataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扬尘数据Controller
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@RestController
@RequestMapping("/jkza/FxExcessivestatisticaldata")
public class LtdqExcessivestatisticaldataController extends BaseController
{
    @Autowired
    private ILtdqExcessivestatisticaldataService ltdqExcessivestatisticaldataService;

    /**
     * 查询扬尘数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxExcessivestatisticaldata:list')")
    @GetMapping("/list")
    public TableDataInfo list(LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata)
    {
        startPage();
        List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataService.selectLtdqExcessivestatisticaldataList(ltdqExcessivestatisticaldata);
        return getDataTable(list);
    }

    /**
     * 导出扬尘数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxExcessivestatisticaldata:export')")
    @Log(title = "扬尘数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata)
    {
        List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataService.selectLtdqExcessivestatisticaldataList(ltdqExcessivestatisticaldata);
        ExcelUtil<LtdqExcessivestatisticaldata> util = new ExcelUtil<LtdqExcessivestatisticaldata>(LtdqExcessivestatisticaldata.class);
        util.exportExcel(response, list, "扬尘数据数据");
    }

    /**
     * 获取扬尘数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxExcessivestatisticaldata:query')")
    @GetMapping(value = "/{projectguid}")
    public AjaxResult getInfo(@PathVariable("projectguid") String projectguid)
    {
        return AjaxResult.success(ltdqExcessivestatisticaldataService.selectLtdqExcessivestatisticaldataByProjectguid(projectguid));
    }

    /**
     * 新增扬尘数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxExcessivestatisticaldata:add')")
    @Log(title = "扬尘数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata)
    {
        return toAjax(ltdqExcessivestatisticaldataService.insertLtdqExcessivestatisticaldata(ltdqExcessivestatisticaldata));
    }

    /**
     * 修改扬尘数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxExcessivestatisticaldata:edit')")
    @Log(title = "扬尘数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata)
    {
        return toAjax(ltdqExcessivestatisticaldataService.updateLtdqExcessivestatisticaldata(ltdqExcessivestatisticaldata));
    }

    /**
     * 删除扬尘数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:FxExcessivestatisticaldata:remove')")
    @Log(title = "扬尘数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectguids}")
    public AjaxResult remove(@PathVariable String[] projectguids)
    {
        return toAjax(ltdqExcessivestatisticaldataService.deleteLtdqExcessivestatisticaldataByProjectguids(projectguids));
    }
}
