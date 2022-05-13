package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.StWarnProportion;

/**
 * 预警占比Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface StWarnProportionMapper 
{
    /**
     * 查询预警占比
     * 
     * @param id 预警占比主键
     * @return 预警占比
     */
    public StWarnProportion selectStWarnProportionById(Long id);

    /**
     * 查询预警占比列表
     * 
     * @param stWarnProportion 预警占比
     * @return 预警占比集合
     */
    public List<StWarnProportion> selectStWarnProportionList(StWarnProportion stWarnProportion);

    /**
     * 新增预警占比
     * 
     * @param stWarnProportion 预警占比
     * @return 结果
     */
    public int insertStWarnProportion(StWarnProportion stWarnProportion);

    /**
     * 修改预警占比
     * 
     * @param stWarnProportion 预警占比
     * @return 结果
     */
    public int updateStWarnProportion(StWarnProportion stWarnProportion);

    /**
     * 删除预警占比
     * 
     * @param id 预警占比主键
     * @return 结果
     */
    public int deleteStWarnProportionById(Long id);

    /**
     * 批量删除预警占比
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStWarnProportionByIds(Integer[] ids);
}
