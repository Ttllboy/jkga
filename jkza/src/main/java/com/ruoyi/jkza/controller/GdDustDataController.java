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
import com.ruoyi.jkza.domain.GdDustData;
import com.ruoyi.jkza.service.IGdDustDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扬尘设备数据Controller
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@RestController
@RequestMapping("/jkza/dustData")
public class GdDustDataController extends BaseController
{
    @Autowired
    private IGdDustDataService gdDustDataService;

    /**
     * 查询扬尘设备数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:dustData:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdDustData gdDustData)
    {
        startPage();
        List<GdDustData> list = gdDustDataService.selectGdDustDataList(gdDustData);
        return getDataTable(list);
    }

    /**
     * 导出扬尘设备数据列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:dustData:export')")
    @Log(title = "扬尘设备数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdDustData gdDustData)
    {
        List<GdDustData> list = gdDustDataService.selectGdDustDataList(gdDustData);
        ExcelUtil<GdDustData> util = new ExcelUtil<GdDustData>(GdDustData.class);
        util.exportExcel(response, list, "扬尘设备数据数据");
    }

    /**
     * 获取扬尘设备数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:dustData:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdDustDataService.selectGdDustDataById(id));
    }

    /**
     * 新增扬尘设备数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:dustData:add')")
    @Log(title = "扬尘设备数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdDustData gdDustData)
    {
        gdDustData.setDataType(1);
        return toAjax(gdDustDataService.insertGdDustData(gdDustData));
    }

    /**
     * 修改扬尘设备数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:dustData:edit')")
    @Log(title = "扬尘设备数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdDustData gdDustData)
    {
        return toAjax(gdDustDataService.updateGdDustData(gdDustData));
    }

    /**
     * 删除扬尘设备数据
     */
    @PreAuthorize("@ss.hasPermi('jkza:dustData:remove')")
    @Log(title = "扬尘设备数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdDustDataService.deleteGdDustDataByIds(ids));
    }
}
