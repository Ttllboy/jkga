package com.ruoyi.jkza.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ruoyi.jkza.domain.GdBuilding;
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
import com.ruoyi.jkza.domain.StSolidWaste;
import com.ruoyi.jkza.service.IStSolidWasteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 固废点Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stWaste")
public class StSolidWasteController extends BaseController
{
    @Autowired
    private IStSolidWasteService stSolidWasteService;

    /**
     * 查询固废点列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWaste:list')")
    @GetMapping("/list")
    public TableDataInfo list(StSolidWaste stSolidWaste)
    {
        startPage();
        List<StSolidWaste> list = stSolidWasteService.selectStSolidWasteList(stSolidWaste);
        return getDataTable(list);
    }

    /**
     * 导出固废点列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWaste:export')")
    @Log(title = "固废点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StSolidWaste stSolidWaste)
    {
        List<StSolidWaste> list = stSolidWasteService.selectStSolidWasteList(stSolidWaste);
        ExcelUtil<StSolidWaste> util = new ExcelUtil<StSolidWaste>(StSolidWaste.class);
        util.exportExcel(response, list, "固废点数据");
    }

    /**
     * 获取固废点详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWaste:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stSolidWasteService.selectStSolidWasteById(id));
    }

    /**
     * 新增固废点
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWaste:add')")
    @Log(title = "固废点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StSolidWaste stSolidWaste)
    {
        return toAjax(stSolidWasteService.insertStSolidWaste(stSolidWaste));
    }

    /**
     * 修改固废点
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWaste:edit')")
    @Log(title = "固废点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StSolidWaste stSolidWaste)
    {
        return toAjax(stSolidWasteService.updateStSolidWaste(stSolidWaste));
    }

    /**
     * 删除固废点
     */
    @PreAuthorize("@ss.hasPermi('jkza:stWaste:remove')")
    @Log(title = "固废点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stSolidWasteService.deleteStSolidWasteByIds(ids));
    }

    //查询所有固废点名称
    @PostMapping("/listSolidNames")
    public JSONArray listSolidNames(){
        StSolidWaste stSolidWaste = new StSolidWaste();
        List<StSolidWaste> stSolidWastes = stSolidWasteService.selectStSolidWasteList(stSolidWaste);
        JSONArray data = new JSONArray();
        for(int i = 0; i < stSolidWastes.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("value",stSolidWastes.get(i).getId());
            jsonObject.set("label",stSolidWastes.get(i).getSolidName());
            data.add(i,jsonObject);
        }
        return data;
    }
}
