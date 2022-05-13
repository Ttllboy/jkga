package com.ruoyi.jkza.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
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
import com.ruoyi.jkza.domain.GdStreet;
import com.ruoyi.jkza.service.IGdStreetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 街道信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
@RestController
@RequestMapping("/jkza/street")
public class GdStreetController extends BaseController
{
    @Autowired
    private IGdStreetService gdStreetService;

    /**
     * 查询街道信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:street:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdStreet gdStreet)
    {
        startPage();
        List<GdStreet> list = gdStreetService.selectGdStreetList(gdStreet);
        return getDataTable(list);
    }

    /**
     * 导出街道信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:street:export')")
    @Log(title = "街道信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdStreet gdStreet)
    {
        List<GdStreet> list = gdStreetService.selectGdStreetList(gdStreet);
        ExcelUtil<GdStreet> util = new ExcelUtil<GdStreet>(GdStreet.class);
        util.exportExcel(response, list, "街道信息数据");
    }

    /**
     * 获取街道信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:street:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdStreetService.selectGdStreetById(id));
    }

    /**
     * 新增街道信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:street:add')")
    @Log(title = "街道信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdStreet gdStreet)
    {
        gdStreet.setDataType(1);
        return toAjax(gdStreetService.insertGdStreet(gdStreet));
    }

    /**
     * 修改街道信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:street:edit')")
    @Log(title = "街道信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdStreet gdStreet)
    {
        return toAjax(gdStreetService.updateGdStreet(gdStreet));
    }

    /**
     * 删除街道信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:street:remove')")
    @Log(title = "街道信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gdStreetService.deleteGdStreetByIds(ids));
    }

    //查询所有街道名称
    @PostMapping("/listStreetNames")
    public JSONArray listStreetNames(){
        GdStreet gdStreet = new GdStreet();
        List<GdStreet> gdStreets = gdStreetService.selectGdStreetList(gdStreet);
        JSONArray data = new JSONArray();
        for(int i = 0; i < gdStreets.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("value",gdStreets.get(i).getId());
            jsonObject.set("label",gdStreets.get(i).getStreetName());
            data.add(i,jsonObject);
        }
        return data;
    }
}
