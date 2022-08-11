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
import com.ruoyi.jkza.domain.MqCar;
import com.ruoyi.jkza.service.IMqCarService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆卡口Controller
 * 
 * @author ruoyi
 * @date 2022-06-23
 */
@RestController
@RequestMapping("/jkza/yyCar")
public class MqCarController extends BaseController
{
    @Autowired
    private IMqCarService mqCarService;

    /**
     * 查询车辆卡口列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyCar:list')")
    @GetMapping("/list")
    public TableDataInfo list(MqCar mqCar)
    {
        startPage();
        List<MqCar> list = mqCarService.selectMqCarList(mqCar);
        return getDataTable(list);
    }

    /**
     * 导出车辆卡口列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyCar:export')")
    @Log(title = "车辆卡口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MqCar mqCar)
    {
        List<MqCar> list = mqCarService.selectMqCarList(mqCar);
        ExcelUtil<MqCar> util = new ExcelUtil<MqCar>(MqCar.class);
        util.exportExcel(response, list, "车辆卡口数据");
    }

    /**
     * 获取车辆卡口详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyCar:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mqCarService.selectMqCarById(id));
    }

    /**
     * 新增车辆卡口
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyCar:add')")
    @Log(title = "车辆卡口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MqCar mqCar)
    {
        return toAjax(mqCarService.insertMqCar(mqCar));
    }

    /**
     * 修改车辆卡口
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyCar:edit')")
    @Log(title = "车辆卡口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MqCar mqCar)
    {
        return toAjax(mqCarService.updateMqCar(mqCar));
    }

    /**
     * 删除车辆卡口
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyCar:remove')")
    @Log(title = "车辆卡口", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mqCarService.deleteMqCarByIds(ids));
    }
}
