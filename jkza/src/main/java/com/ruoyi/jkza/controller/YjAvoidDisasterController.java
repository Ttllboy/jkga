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
import com.ruoyi.jkza.domain.YjAvoidDisaster;
import com.ruoyi.jkza.service.IYjAvoidDisasterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 避灾场所Controller
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/jkza/yjDisaster")
public class YjAvoidDisasterController extends BaseController
{
    @Autowired
    private IYjAvoidDisasterService yjAvoidDisasterService;

    /**
     * 查询避灾场所列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjDisaster:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjAvoidDisaster yjAvoidDisaster)
    {
        startPage();
        List<YjAvoidDisaster> list = yjAvoidDisasterService.selectYjAvoidDisasterList(yjAvoidDisaster);
        return getDataTable(list);
    }

    /**
     * 导出避灾场所列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjDisaster:export')")
    @Log(title = "避灾场所", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjAvoidDisaster yjAvoidDisaster)
    {
        List<YjAvoidDisaster> list = yjAvoidDisasterService.selectYjAvoidDisasterList(yjAvoidDisaster);
        ExcelUtil<YjAvoidDisaster> util = new ExcelUtil<YjAvoidDisaster>(YjAvoidDisaster.class);
        util.exportExcel(response, list, "避灾场所数据");
    }

    /**
     * 获取避灾场所详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjDisaster:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjAvoidDisasterService.selectYjAvoidDisasterById(id));
    }

    /**
     * 新增避灾场所
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjDisaster:add')")
    @Log(title = "避灾场所", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjAvoidDisaster yjAvoidDisaster)
    {
        return toAjax(yjAvoidDisasterService.insertYjAvoidDisaster(yjAvoidDisaster));
    }

    /**
     * 修改避灾场所
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjDisaster:edit')")
    @Log(title = "避灾场所", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjAvoidDisaster yjAvoidDisaster)
    {
        return toAjax(yjAvoidDisasterService.updateYjAvoidDisaster(yjAvoidDisaster));
    }

    /**
     * 删除避灾场所
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjDisaster:remove')")
    @Log(title = "避灾场所", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjAvoidDisasterService.deleteYjAvoidDisasterByIds(ids));
    }
}
