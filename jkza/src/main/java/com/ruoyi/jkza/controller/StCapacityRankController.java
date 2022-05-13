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
import com.ruoyi.jkza.domain.StCapacityRank;
import com.ruoyi.jkza.service.IStCapacityRankService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 容量排名Controller
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/jkza/stCapacityRank")
public class StCapacityRankController extends BaseController
{
    @Autowired
    private IStCapacityRankService stCapacityRankService;

    /**
     * 查询容量排名列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stCapacityRank:list')")
    @GetMapping("/list")
    public TableDataInfo list(StCapacityRank stCapacityRank)
    {
        startPage();
        List<StCapacityRank> list = stCapacityRankService.selectStCapacityRankList(stCapacityRank);
        return getDataTable(list);
    }

    /**
     * 导出容量排名列表
     */
    @PreAuthorize("@ss.hasPermi('jkza:stCapacityRank:export')")
    @Log(title = "容量排名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StCapacityRank stCapacityRank)
    {
        List<StCapacityRank> list = stCapacityRankService.selectStCapacityRankList(stCapacityRank);
        ExcelUtil<StCapacityRank> util = new ExcelUtil<StCapacityRank>(StCapacityRank.class);
        util.exportExcel(response, list, "容量排名数据");
    }

    /**
     * 获取容量排名详细信息
     */
    @PreAuthorize("@ss.hasPermi('jkza:stCapacityRank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stCapacityRankService.selectStCapacityRankById(id));
    }

    /**
     * 新增容量排名
     */
    @PreAuthorize("@ss.hasPermi('jkza:stCapacityRank:add')")
    @Log(title = "容量排名", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StCapacityRank stCapacityRank)
    {
        return toAjax(stCapacityRankService.insertStCapacityRank(stCapacityRank));
    }

    /**
     * 修改容量排名
     */
    @PreAuthorize("@ss.hasPermi('jkza:stCapacityRank:edit')")
    @Log(title = "容量排名", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StCapacityRank stCapacityRank)
    {
        return toAjax(stCapacityRankService.updateStCapacityRank(stCapacityRank));
    }

    /**
     * 删除容量排名
     */
    @PreAuthorize("@ss.hasPermi('jkza:stCapacityRank:remove')")
    @Log(title = "容量排名", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stCapacityRankService.deleteStCapacityRankByIds(ids));
    }
}
