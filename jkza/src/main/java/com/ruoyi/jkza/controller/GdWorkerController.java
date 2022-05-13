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
import com.ruoyi.jkza.domain.GdWorker;
import com.ruoyi.jkza.service.IGdWorkerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工人信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@RestController
@RequestMapping("/jkza/worker")
public class GdWorkerController extends BaseController
{
    @Autowired
    private IGdWorkerService gdWorkerService;

    /**
     * 查询工人信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:worker:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdWorker gdWorker)
    {
        startPage();
        List<GdWorker> list = gdWorkerService.selectGdWorkerList(gdWorker);
        return getDataTable(list);
    }

    /**
     * 导出工人信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:worker:export')")
    @Log(title = "工人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdWorker gdWorker)
    {
        List<GdWorker> list = gdWorkerService.selectGdWorkerList(gdWorker);
        ExcelUtil<GdWorker> util = new ExcelUtil<GdWorker>(GdWorker.class);
        util.exportExcel(response, list, "工人信息数据");
    }

    /**
     * 获取工人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:worker:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdWorkerService.selectGdWorkerById(id));
    }

    /**
     * 新增工人信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:worker:add')")
    @Log(title = "工人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdWorker gdWorker)
    {
        gdWorker.setDataType(1);
        return toAjax(gdWorkerService.insertGdWorker(gdWorker));
    }

    /**
     * 修改工人信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:worker:edit')")
    @Log(title = "工人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdWorker gdWorker)
    {
        return toAjax(gdWorkerService.updateGdWorker(gdWorker));
    }

    /**
     * 删除工人信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:worker:remove')")
    @Log(title = "工人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdWorkerService.deleteGdWorkerByIds(ids));
    }
}
