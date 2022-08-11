package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdTower;

/**
 * 塔吊预警Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-25
 */
public interface GdTowerMapper 
{
    /**
     * 查询塔吊预警
     * 
     * @param id 塔吊预警主键
     * @return 塔吊预警
     */
    public GdTower selectGdTowerById(Long id);

    /**
     * 查询塔吊预警列表
     * 
     * @param gdTower 塔吊预警
     * @return 塔吊预警集合
     */
    public List<GdTower> selectGdTowerList(GdTower gdTower);

    /**
     * 新增塔吊预警
     * 
     * @param gdTower 塔吊预警
     * @return 结果
     */
    public int insertGdTower(GdTower gdTower);

    /**
     * 修改塔吊预警
     * 
     * @param gdTower 塔吊预警
     * @return 结果
     */
    public int updateGdTower(GdTower gdTower);

    /**
     * 删除塔吊预警
     * 
     * @param id 塔吊预警主键
     * @return 结果
     */
    public int deleteGdTowerById(Long id);

    /**
     * 批量删除塔吊预警
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdTowerByIds(Long[] ids);
}
