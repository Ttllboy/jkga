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
import com.ruoyi.jkza.domain.YyVideo;
import com.ruoyi.jkza.service.IYyVideoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频设备Controller
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@RestController
@RequestMapping("/jkza/yyVideo")
public class YyVideoController extends BaseController
{
    @Autowired
    private IYyVideoService yyVideoService;

    /**
     * 查询视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideo:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyVideo yyVideo)
    {
        startPage();
        List<YyVideo> list = yyVideoService.selectYyVideoList(yyVideo);
        return getDataTable(list);
    }

    /**
     * 导出视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideo:export')")
    @Log(title = "视频设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyVideo yyVideo)
    {
        List<YyVideo> list = yyVideoService.selectYyVideoList(yyVideo);
        ExcelUtil<YyVideo> util = new ExcelUtil<YyVideo>(YyVideo.class);
        util.exportExcel(response, list, "视频设备数据");
    }

    /**
     * 获取视频设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyVideoService.selectYyVideoById(id));
    }

    /**
     * 新增视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideo:add')")
    @Log(title = "视频设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyVideo yyVideo)
    {
        return toAjax(yyVideoService.insertYyVideo(yyVideo));
    }

    /**
     * 修改视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideo:edit')")
    @Log(title = "视频设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyVideo yyVideo)
    {
        return toAjax(yyVideoService.updateYyVideo(yyVideo));
    }

    /**
     * 删除视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyVideo:remove')")
    @Log(title = "视频设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyVideoService.deleteYyVideoByIds(ids));
    }
}
