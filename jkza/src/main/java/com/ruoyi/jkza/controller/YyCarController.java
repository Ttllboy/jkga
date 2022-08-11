package com.ruoyi.jkza.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.jkza.domain.YyYy;
import com.ruoyi.jkza.mapper.YyYyMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
import com.ruoyi.jkza.domain.YyCar;
import com.ruoyi.jkza.service.IYyCarService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医院车辆Controller
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
@RestController
@RequestMapping("/jkza/yycarmanage")
public class YyCarController extends BaseController
{
    @Autowired
    private IYyCarService yyCarService;

    /**
     * 查询医院车辆列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yycarmanage:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyCar yyCar)
    {
        startPage();
        List<YyCar> list = yyCarService.selectYyCarList(yyCar);
        return getDataTable(list);
    }

    /**
     * 导出医院车辆列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yycarmanage:export')")
    @Log(title = "医院车辆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyCar yyCar)
    {
        List<YyCar> list = yyCarService.selectYyCarList(yyCar);
        ExcelUtil<YyCar> util = new ExcelUtil<YyCar>(YyCar.class);
        util.exportExcel(response, list, "医院车辆数据");
    }

    /**
     * 获取医院车辆详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yycarmanage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyCarService.selectYyCarById(id));
    }

    /**
     * 新增医院车辆
     */
    @PreAuthorize("@ss.hasPermi('jkza:yycarmanage:add')")
    @Log(title = "医院车辆", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyCar yyCar)
    {
        return toAjax(yyCarService.insertYyCar(yyCar));
    }

    /**
     * 修改医院车辆
     */
    @PreAuthorize("@ss.hasPermi('jkza:yycarmanage:edit')")
    @Log(title = "医院车辆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyCar yyCar)
    {
        return toAjax(yyCarService.updateYyCar(yyCar));
    }

    /**
     * 删除医院车辆
     */
    @PreAuthorize("@ss.hasPermi('jkza:yycarmanage:remove')")
    @Log(title = "医院车辆", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yyCarService.deleteYyCarByIds(ids));
    }

    @Autowired
    private YyYyMapper yyYyMapper;
    @PostMapping("/listHosNames")
    public JSONArray listHosNames(){
        List<YyYy> list = yyYyMapper.selectYyYyList(new YyYy());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            YyYy yy = list.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value",yy.getId());
            jsonObject.put("label",yy.getHosName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
