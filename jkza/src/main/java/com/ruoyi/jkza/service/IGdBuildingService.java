package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdBuilding;

/**
 * 工地信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public interface IGdBuildingService 
{
    /**
     * 查询工地信息
     * 
     * @param id 工地信息主键
     * @return 工地信息
     */
    public GdBuilding selectGdBuildingById(Long id);

    /**
     * 查询工地信息列表
     * 
     * @param gdBuilding 工地信息
     * @return 工地信息集合
     */
    public List<GdBuilding> selectGdBuildingList(GdBuilding gdBuilding);

    /**
     * 新增工地信息
     * 
     * @param gdBuilding 工地信息
     * @return 结果
     */
    public int insertGdBuilding(GdBuilding gdBuilding);

    /**
     * 修改工地信息
     * 
     * @param gdBuilding 工地信息
     * @return 结果
     */
    public int updateGdBuilding(GdBuilding gdBuilding);

    /**
     * 批量删除工地信息
     * 
     * @param ids 需要删除的工地信息主键集合
     * @return 结果
     */
    public int deleteGdBuildingByIds(Long[] ids);

    /**
     * 删除工地信息信息
     * 
     * @param id 工地信息主键
     * @return 结果
     */
    public int deleteGdBuildingById(Long id);
}
