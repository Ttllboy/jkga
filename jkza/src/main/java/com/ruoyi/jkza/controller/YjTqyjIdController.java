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
import com.ruoyi.jkza.domain.YjTqyjId;
import com.ruoyi.jkza.service.IYjTqyjIdService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 天气预警Controller
 * 
 * @author ruoyi
 * @date 2022-08-05
 */
@RestController
@RequestMapping("/jkza/tqyj")
public class YjTqyjIdController extends BaseController
{
    @Autowired
    private IYjTqyjIdService yjTqyjIdService;

    /**
     * 查询天气预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:tqyj:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjTqyjId yjTqyjId)
    {
        startPage();
        List<YjTqyjId> list = yjTqyjIdService.selectYjTqyjIdList(yjTqyjId);
        return getDataTable(list);
    }

    /**
     * 导出天气预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:tqyj:export')")
    @Log(title = "天气预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjTqyjId yjTqyjId)
    {
        List<YjTqyjId> list = yjTqyjIdService.selectYjTqyjIdList(yjTqyjId);
        ExcelUtil<YjTqyjId> util = new ExcelUtil<YjTqyjId>(YjTqyjId.class);
        util.exportExcel(response, list, "天气预警数据");
    }

    /**
     * 获取天气预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:tqyj:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjTqyjIdService.selectYjTqyjIdById(id));
    }

    /**
     * 新增天气预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:tqyj:add')")
    @Log(title = "天气预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjTqyjId yjTqyjId)
    {
        return toAjax(yjTqyjIdService.insertYjTqyjId(yjTqyjId));
    }

    /**
     * 修改天气预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:tqyj:edit')")
    @Log(title = "天气预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjTqyjId yjTqyjId)
    {
        return toAjax(yjTqyjIdService.updateYjTqyjId(yjTqyjId));
    }

    /**
     * 删除天气预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:tqyj:remove')")
    @Log(title = "天气预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjTqyjIdService.deleteYjTqyjIdByIds(ids));
    }
}
