package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdTowerMapper;
import com.ruoyi.jkza.domain.GdTower;
import com.ruoyi.jkza.service.IGdTowerService;

/**
 * 塔吊预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-25
 */
@Service
public class GdTowerServiceImpl implements IGdTowerService 
{
    @Autowired
    private GdTowerMapper gdTowerMapper;

    /**
     * 查询塔吊预警
     * 
     * @param id 塔吊预警主键
     * @return 塔吊预警
     */
    @Override
    public GdTower selectGdTowerById(Long id)
    {
        return gdTowerMapper.selectGdTowerById(id);
    }

    /**
     * 查询塔吊预警列表
     * 
     * @param gdTower 塔吊预警
     * @return 塔吊预警
     */
    @Override
    public List<GdTower> selectGdTowerList(GdTower gdTower)
    {
        return gdTowerMapper.selectGdTowerList(gdTower);
    }

    /**
     * 新增塔吊预警
     * 
     * @param gdTower 塔吊预警
     * @return 结果
     */
    @Override
    public int insertGdTower(GdTower gdTower)
    {
        return gdTowerMapper.insertGdTower(gdTower);
    }

    /**
     * 修改塔吊预警
     * 
     * @param gdTower 塔吊预警
     * @return 结果
     */
    @Override
    public int updateGdTower(GdTower gdTower)
    {
        return gdTowerMapper.updateGdTower(gdTower);
    }

    /**
     * 批量删除塔吊预警
     * 
     * @param ids 需要删除的塔吊预警主键
     * @return 结果
     */
    @Override
    public int deleteGdTowerByIds(Long[] ids)
    {
        return gdTowerMapper.deleteGdTowerByIds(ids);
    }

    /**
     * 删除塔吊预警信息
     * 
     * @param id 塔吊预警主键
     * @return 结果
     */
    @Override
    public int deleteGdTowerById(Long id)
    {
        return gdTowerMapper.deleteGdTowerById(id);
    }
}
