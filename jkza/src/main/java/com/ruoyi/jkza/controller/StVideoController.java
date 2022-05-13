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
import com.ruoyi.jkza.domain.StVideo;
import com.ruoyi.jkza.service.IStVideoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频设备Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stVideo")
public class StVideoController extends BaseController
{
    @Autowired
    private IStVideoService stVideoService;

    /**
     * 查询视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideo:list')")
    @GetMapping("/list")
    public TableDataInfo list(StVideo stVideo)
    {
        startPage();
        List<StVideo> list = stVideoService.selectStVideoList(stVideo);
        return getDataTable(list);
    }

    /**
     * 导出视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideo:export')")
    @Log(title = "视频设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StVideo stVideo)
    {
        List<StVideo> list = stVideoService.selectStVideoList(stVideo);
        ExcelUtil<StVideo> util = new ExcelUtil<StVideo>(StVideo.class);
        util.exportExcel(response, list, "视频设备数据");
    }

    /**
     * 获取视频设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stVideoService.selectStVideoById(id));
    }

    /**
     * 新增视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideo:add')")
    @Log(title = "视频设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StVideo stVideo)
    {
        return toAjax(stVideoService.insertStVideo(stVideo));
    }

    /**
     * 修改视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideo:edit')")
    @Log(title = "视频设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StVideo stVideo)
    {
        return toAjax(stVideoService.updateStVideo(stVideo));
    }

    /**
     * 删除视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:stVideo:remove')")
    @Log(title = "视频设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(stVideoService.deleteStVideoByIds(ids));
    }
}
