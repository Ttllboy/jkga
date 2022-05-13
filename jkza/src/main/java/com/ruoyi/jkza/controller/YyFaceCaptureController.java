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
import com.ruoyi.jkza.domain.YyFaceCapture;
import com.ruoyi.jkza.service.IYyFaceCaptureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人脸抓拍Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/yyFaceCapture")
public class YyFaceCaptureController extends BaseController
{
    @Autowired
    private IYyFaceCaptureService yyFaceCaptureService;

    /**
     * 查询人脸抓拍列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyFaceCapture:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyFaceCapture yyFaceCapture)
    {
        startPage();
        List<YyFaceCapture> list = yyFaceCaptureService.selectYyFaceCaptureList(yyFaceCapture);
        return getDataTable(list);
    }

    /**
     * 导出人脸抓拍列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyFaceCapture:export')")
    @Log(title = "人脸抓拍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyFaceCapture yyFaceCapture)
    {
        List<YyFaceCapture> list = yyFaceCaptureService.selectYyFaceCaptureList(yyFaceCapture);
        ExcelUtil<YyFaceCapture> util = new ExcelUtil<YyFaceCapture>(YyFaceCapture.class);
        util.exportExcel(response, list, "人脸抓拍数据");
    }

    /**
     * 获取人脸抓拍详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyFaceCapture:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyFaceCaptureService.selectYyFaceCaptureById(id));
    }

    /**
     * 新增人脸抓拍
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyFaceCapture:add')")
    @Log(title = "人脸抓拍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyFaceCapture yyFaceCapture)
    {
        return toAjax(yyFaceCaptureService.insertYyFaceCapture(yyFaceCapture));
    }

    /**
     * 修改人脸抓拍
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyFaceCapture:edit')")
    @Log(title = "人脸抓拍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyFaceCapture yyFaceCapture)
    {
        return toAjax(yyFaceCaptureService.updateYyFaceCapture(yyFaceCapture));
    }

    /**
     * 删除人脸抓拍
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyFaceCapture:remove')")
    @Log(title = "人脸抓拍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyFaceCaptureService.deleteYyFaceCaptureByIds(ids));
    }
}
