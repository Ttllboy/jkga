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
import com.ruoyi.jkza.domain.YjVideo;
import com.ruoyi.jkza.service.IYjVideoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频设备Controller
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@RestController
@RequestMapping("/jkza/yjVideo")
public class YjVideoController extends BaseController
{
    @Autowired
    private IYjVideoService yjVideoService;

    /**
     * 查询视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideo:list')")
    @GetMapping("/list")
    public TableDataInfo list(YjVideo yjVideo)
    {
        startPage();
        List<YjVideo> list = yjVideoService.selectYjVideoList(yjVideo);
        return getDataTable(list);
    }

    /**
     * 导出视频设备列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideo:export')")
    @Log(title = "视频设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjVideo yjVideo)
    {
        List<YjVideo> list = yjVideoService.selectYjVideoList(yjVideo);
        ExcelUtil<YjVideo> util = new ExcelUtil<YjVideo>(YjVideo.class);
        util.exportExcel(response, list, "视频设备数据");
    }

    /**
     * 获取视频设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yjVideoService.selectYjVideoById(id));
    }

    /**
     * 新增视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideo:add')")
    @Log(title = "视频设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjVideo yjVideo)
    {
        return toAjax(yjVideoService.insertYjVideo(yjVideo));
    }

    /**
     * 修改视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideo:edit')")
    @Log(title = "视频设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjVideo yjVideo)
    {
        return toAjax(yjVideoService.updateYjVideo(yjVideo));
    }

    /**
     * 删除视频设备
     */
    @PreAuthorize("@ss.hasPermi('jkza:yjVideo:remove')")
    @Log(title = "视频设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yjVideoService.deleteYjVideoByIds(ids));
    }
}
