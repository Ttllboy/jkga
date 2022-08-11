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
import com.ruoyi.jkza.domain.MqRlzp;
import com.ruoyi.jkza.service.IMqRlzpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人脸抓怕Controller
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
@RestController
@RequestMapping("/jkza/mqRlzp")
public class MqRlzpController extends BaseController
{
    @Autowired
    private IMqRlzpService mqRlzpService;

    /**
     * 查询人脸抓怕列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:mqRlzp:list')")
    @GetMapping("/list")
    public TableDataInfo list(MqRlzp mqRlzp)
    {
        startPage();
        List<MqRlzp> list = mqRlzpService.selectMqRlzpList(mqRlzp);
        return getDataTable(list);
    }

    /**
     * 导出人脸抓怕列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:mqRlzp:export')")
    @Log(title = "人脸抓怕", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MqRlzp mqRlzp)
    {
        List<MqRlzp> list = mqRlzpService.selectMqRlzpList(mqRlzp);
        ExcelUtil<MqRlzp> util = new ExcelUtil<MqRlzp>(MqRlzp.class);
        util.exportExcel(response, list, "人脸抓怕数据");
    }

    /**
     * 获取人脸抓怕详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:mqRlzp:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mqRlzpService.selectMqRlzpById(id));
    }

    /**
     * 新增人脸抓怕
     */
    @PreAuthorize("@ss.hasPermi('jkza:mqRlzp:add')")
    @Log(title = "人脸抓怕", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MqRlzp mqRlzp)
    {
        return toAjax(mqRlzpService.insertMqRlzp(mqRlzp));
    }

    /**
     * 修改人脸抓怕
     */
    @PreAuthorize("@ss.hasPermi('jkza:mqRlzp:edit')")
    @Log(title = "人脸抓怕", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MqRlzp mqRlzp)
    {
        return toAjax(mqRlzpService.updateMqRlzp(mqRlzp));
    }

    /**
     * 删除人脸抓怕
     */
    @PreAuthorize("@ss.hasPermi('jkza:mqRlzp:remove')")
    @Log(title = "人脸抓怕", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mqRlzpService.deleteMqRlzpByIds(ids));
    }
}
