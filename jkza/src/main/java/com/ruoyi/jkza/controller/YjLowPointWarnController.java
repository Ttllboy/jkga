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
import com.ruoyi.jkza.domain.YjLowPointWarn;
import com.ruoyi.jkza.service.IYjLowPointWarnService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 低洼点预警Controller
 * 
 * @author ruoyi
 * @date 2022-06-24
 */
@RestController
@RequestMapping("/jkza/yjLowWarn")
public class YjLowPointWarnController extends BaseController
{
    @Autowired
    private IYjLowPointWarnService yjLowPointWarnService;

    /**
     * 查询低洼点预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowWarn:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjLowPointWarn yjLowPointWarn)
    {
        startPage();
        List<YjLowPointWarn> list = yjLowPointWarnService.selectYjLowPointWarnList(yjLowPointWarn);
        return getDataTable(list);
    }

    /**
     * 导出低洼点预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowWarn:export')")
    @Log(title = "低洼点预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjLowPointWarn yjLowPointWarn)
    {
        List<YjLowPointWarn> list = yjLowPointWarnService.selectYjLowPointWarnList(yjLowPointWarn);
        ExcelUtil<YjLowPointWarn> util = new ExcelUtil<YjLowPointWarn>(YjLowPointWarn.class);
        util.exportExcel(response, list, "低洼点预警数据");
    }

    /**
     * 获取低洼点预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowWarn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjLowPointWarnService.selectYjLowPointWarnById(id));
    }

    /**
     * 新增低洼点预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowWarn:add')")
    @Log(title = "低洼点预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjLowPointWarn yjLowPointWarn)
    {
        return toAjax(yjLowPointWarnService.insertYjLowPointWarn(yjLowPointWarn));
    }

    /**
     * 修改低洼点预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowWarn:edit')")
    @Log(title = "低洼点预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjLowPointWarn yjLowPointWarn)
    {
        return toAjax(yjLowPointWarnService.updateYjLowPointWarn(yjLowPointWarn));
    }

    /**
     * 删除低洼点预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjLowWarn:remove')")
    @Log(title = "低洼点预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjLowPointWarnService.deleteYjLowPointWarnByIds(ids));
    }
}
