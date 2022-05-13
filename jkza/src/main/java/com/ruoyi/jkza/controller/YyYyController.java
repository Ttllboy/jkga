package com.ruoyi.jkza.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
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
import com.ruoyi.jkza.domain.YyYy;
import com.ruoyi.jkza.service.IYyYyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医院信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/yyYy")
public class YyYyController extends BaseController
{
    @Autowired
    private IYyYyService yyYyService;

    /**
     * 查询医院信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyYy:list')")
    @GetMapping("/list")
    public TableDataInfo list(YyYy yyYy)
    {
        startPage();
        List<YyYy> list = yyYyService.selectYyYyList(yyYy);
        return getDataTable(list);
    }

    /**
     * 导出医院信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyYy:export')")
    @Log(title = "医院信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YyYy yyYy)
    {
        List<YyYy> list = yyYyService.selectYyYyList(yyYy);
        ExcelUtil<YyYy> util = new ExcelUtil<YyYy>(YyYy.class);
        util.exportExcel(response, list, "医院信息数据");
    }

    /**
     * 获取医院信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyYy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yyYyService.selectYyYyById(id));
    }

    /**
     * 新增医院信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyYy:add')")
    @Log(title = "医院信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YyYy yyYy)
    {
        return toAjax(yyYyService.insertYyYy(yyYy));
    }

    /**
     * 修改医院信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyYy:edit')")
    @Log(title = "医院信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YyYy yyYy)
    {
        return toAjax(yyYyService.updateYyYy(yyYy));
    }

    /**
     * 删除医院信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:yyYy:remove')")
    @Log(title = "医院信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(yyYyService.deleteYyYyByIds(ids));
    }

    @PostMapping("/listYyNames")
    public JSONArray listYyNames(){
        YyYy yyYy = new YyYy();
        List<YyYy> yyYyList = yyYyService.selectYyYyList(yyYy);
        JSONArray data = new JSONArray();
        for(int i = 0; i < yyYyList.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("value",yyYyList.get(i).getId());
            jsonObject.set("label",yyYyList.get(i).getHosName());
            data.add(i,jsonObject);
        }
        return data;
    }

}
