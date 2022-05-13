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
import com.ruoyi.jkza.domain.GdVideoBase;
import com.ruoyi.jkza.service.IGdVideoBaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频列表Controller
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@RestController
@RequestMapping("/jkza/videoBase")
public class GdVideoBaseController extends BaseController
{
    @Autowired
    private IGdVideoBaseService gdVideoBaseService;

    /**
     * 查询视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:videoBase:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdVideoBase gdVideoBase)
    {
        startPage();
        List<GdVideoBase> list = gdVideoBaseService.selectGdVideoBaseList(gdVideoBase);
        return getDataTable(list);
    }

    /**
     * 导出视频列表列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:videoBase:export')")
    @Log(title = "视频列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdVideoBase gdVideoBase)
    {
        List<GdVideoBase> list = gdVideoBaseService.selectGdVideoBaseList(gdVideoBase);
        ExcelUtil<GdVideoBase> util = new ExcelUtil<GdVideoBase>(GdVideoBase.class);
        util.exportExcel(response, list, "视频列表数据");
    }

    /**
     * 获取视频列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:videoBase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdVideoBaseService.selectGdVideoBaseById(id));
    }

    /**
     * 新增视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:videoBase:add')")
    @Log(title = "视频列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdVideoBase gdVideoBase)
    {
        gdVideoBase.setDataType(1);
        return toAjax(gdVideoBaseService.insertGdVideoBase(gdVideoBase));
    }

    /**
     * 修改视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:videoBase:edit')")
    @Log(title = "视频列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdVideoBase gdVideoBase)
    {
        return toAjax(gdVideoBaseService.updateGdVideoBase(gdVideoBase));
    }

    /**
     * 删除视频列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:videoBase:remove')")
    @Log(title = "视频列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdVideoBaseService.deleteGdVideoBaseByIds(ids));
    }
}
