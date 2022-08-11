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
import com.ruoyi.jkza.domain.GdTower;
import com.ruoyi.jkza.service.IGdTowerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 塔吊预警Controller
 * 
 * @author ruoyi
 * @date 2022-06-25
 */
@RestController
@RequestMapping("/jkza/gdTower")
public class GdTowerController extends BaseController
{
    @Autowired
    private IGdTowerService gdTowerService;

    /**
     * 查询塔吊预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdTower:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdTower gdTower)
    {
        startPage();
        List<GdTower> list = gdTowerService.selectGdTowerList(gdTower);
        return getDataTable(list);
    }

    /**
     * 导出塔吊预警列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdTower:export')")
    @Log(title = "塔吊预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdTower gdTower)
    {
        List<GdTower> list = gdTowerService.selectGdTowerList(gdTower);
        ExcelUtil<GdTower> util = new ExcelUtil<GdTower>(GdTower.class);
        util.exportExcel(response, list, "塔吊预警数据");
    }

    /**
     * 获取塔吊预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdTower:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdTowerService.selectGdTowerById(id));
    }

    /**
     * 新增塔吊预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdTower:add')")
    @Log(title = "塔吊预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdTower gdTower)
    {
        return toAjax(gdTowerService.insertGdTower(gdTower));
    }

    /**
     * 修改塔吊预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdTower:edit')")
    @Log(title = "塔吊预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdTower gdTower)
    {
        return toAjax(gdTowerService.updateGdTower(gdTower));
    }

    /**
     * 删除塔吊预警
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdTower:remove')")
    @Log(title = "塔吊预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdTowerService.deleteGdTowerByIds(ids));
    }
}
