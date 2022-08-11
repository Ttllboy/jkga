package com.ruoyi.jkza.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ruoyi.jkza.domain.GdStreet;
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
import com.ruoyi.jkza.domain.GdBuilding;
import com.ruoyi.jkza.service.IGdBuildingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工地信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
@RestController
@RequestMapping("/jkza/building")
public class GdBuildingController extends BaseController
{
    @Autowired
    private IGdBuildingService gdBuildingService;

    /**
     * 查询工地信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:building:list')")
    @GetMapping("/list")
    public TableDataInfo list(GdBuilding gdBuilding)
    {
        startPage();
        List<GdBuilding> list = gdBuildingService.selectGdBuildingList(gdBuilding);
        return getDataTable(list);
    }

    /**
     * 导出工地信息列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:building:export')")
    @Log(title = "工地信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GdBuilding gdBuilding)
    {
        List<GdBuilding> list = gdBuildingService.selectGdBuildingList(gdBuilding);
        ExcelUtil<GdBuilding> util = new ExcelUtil<GdBuilding>(GdBuilding.class);
        util.exportExcel(response, list, "工地信息数据");
    }

    /**
     * 获取工地信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:building:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gdBuildingService.selectGdBuildingById(id));
    }

    /**
     * 新增工地信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:building:add')")
    @Log(title = "工地信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GdBuilding gdBuilding)
    {
        return toAjax(gdBuildingService.insertGdBuilding(gdBuilding));
    }

    /**
     * 修改工地信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:building:edit')")
    @Log(title = "工地信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GdBuilding gdBuilding)
    {
        return toAjax(gdBuildingService.updateGdBuilding(gdBuilding));
    }

    /**
     * 删除工地信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:building:remove')")
    @Log(title = "工地信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)

    {
        return toAjax(gdBuildingService.deleteGdBuildingByIds(ids));
    }

    //查询所有工地名称
    @PostMapping("/listBuildingNames")
    public JSONArray listBuildingNames(){
        GdBuilding gdBuilding = new GdBuilding();
        List<GdBuilding> gdBuildings = gdBuildingService.selectGdBuildingList(gdBuilding);
        JSONArray data = new JSONArray();
        for(int i = 0; i < gdBuildings.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("value",gdBuildings.get(i).getId());
            jsonObject.set("label",gdBuildings.get(i).getBuildingSiteName());
            data.add(i,jsonObject);
        }
        return data;
    }
    @PostMapping("/listProjectId")
    public JSONArray listProjectId(){
        List<GdBuilding> gdBuildings = gdBuildingService.selectGdBuildingList(new GdBuilding());
        JSONArray data = new JSONArray();
        for(int i = 0; i < gdBuildings.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("value",gdBuildings.get(i).getId());
            jsonObject.set("label",gdBuildings.get(i).getProjectInfoNum());
            data.add(i,jsonObject);
        }
        return data;
    }
}
