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
import com.ruoyi.jkza.domain.GdVideo;
import com.ruoyi.jkza.service.IGdVideoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频设备Controller
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@RestController
@RequestMapping("/jkza/gdVideo")
public class GdVideoController extends BaseController
{
    @Autowired
    private IGdVideoService gdVideoService;

    /**
     * 查询视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdVideo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdVideo gdVideo)
    {
        startPage();
        List<GdVideo> list = gdVideoService.selectGdVideoList(gdVideo);
        return getDataTable(list);
    }

    /**
     * 导出视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdVideo:export')")
    @Log(title = "视频设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdVideo gdVideo)
    {
        List<GdVideo> list = gdVideoService.selectGdVideoList(gdVideo);
        ExcelUtil<GdVideo> util = new ExcelUtil<GdVideo>(GdVideo.class);
        util.exportExcel(response, list, "视频设备数据");
    }

    /**
     * 获取视频设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdVideo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdVideoService.selectGdVideoById(id));
    }

    /**
     * 新增视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdVideo:add')")
    @Log(title = "视频设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdVideo gdVideo)
    {
        return toAjax(gdVideoService.insertGdVideo(gdVideo));
    }

    /**
     * 修改视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdVideo:edit')")
    @Log(title = "视频设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdVideo gdVideo)
    {
        return toAjax(gdVideoService.updateGdVideo(gdVideo));
    }

    /**
     * 删除视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:gdVideo:remove')")
    @Log(title = "视频设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdVideoService.deleteGdVideoByIds(ids));
    }
}
