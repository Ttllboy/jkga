package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdBuildingMapper;
import com.ruoyi.jkza.domain.GdBuilding;
import com.ruoyi.jkza.service.IGdBuildingService;

/**
 * 工地信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
@Service
public class GdBuildingServiceImpl implements IGdBuildingService 
{
    @Autowired
    private GdBuildingMapper gdBuildingMapper;

    /**
     * 查询工地信息
     * 
     * @param id 工地信息主键
     * @return 工地信息
     */
    @Override
    public GdBuilding selectGdBuildingById(Long id)
    {
        return gdBuildingMapper.selectGdBuildingById(id);
    }

    /**
     * 查询工地信息列表
     * 
     * @param gdBuilding 工地信息
     * @return 工地信息
     */
    @Override
    public List<GdBuilding> selectGdBuildingList(GdBuilding gdBuilding)
    {
        return gdBuildingMapper.selectGdBuildingList(gdBuilding);
    }

    /**
     * 新增工地信息
     * 
     * @param gdBuilding 工地信息
     * @return 结果
     */
    @Override
    public int insertGdBuilding(GdBuilding gdBuilding)
    {
        return gdBuildingMapper.insertGdBuilding(gdBuilding);
    }

    /**
     * 修改工地信息
     * 
     * @param gdBuilding 工地信息
     * @return 结果
     */
    @Override
    public int updateGdBuilding(GdBuilding gdBuilding)
    {
        return gdBuildingMapper.updateGdBuilding(gdBuilding);
    }

    /**
     * 批量删除工地信息
     * 
     * @param ids 需要删除的工地信息主键
     * @return 结果
     */
    @Override
    public int deleteGdBuildingByIds(Long[] ids)
    {
        return gdBuildingMapper.deleteGdBuildingByIds(ids);
    }

    /**
     * 删除工地信息信息
     * 
     * @param id 工地信息主键
     * @return 结果
     */
    @Override
    public int deleteGdBuildingById(Long id)
    {
        return gdBuildingMapper.deleteGdBuildingById(id);
    }
}
