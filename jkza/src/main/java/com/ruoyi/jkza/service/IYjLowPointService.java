package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjLowPoint;

/**
 * 低洼点Service接口
 * 
 * @author ruoyi
 * @date 2022-06-20
 */
public interface IYjLowPointService 
{
    /**
     * 查询低洼点
     * 
     * @param id 低洼点主键
     * @return 低洼点
     */
    public YjLowPoint selectYjLowPointById(Long id);

    /**
     * 查询低洼点列表
     * 
     * @param yjLowPoint 低洼点
     * @return 低洼点集合
     */
    public List<YjLowPoint> selectYjLowPointList(YjLowPoint yjLowPoint);

    /**
     * 新增低洼点
     * 
     * @param yjLowPoint 低洼点
     * @return 结果
     */
    public int insertYjLowPoint(YjLowPoint yjLowPoint);

    /**
     * 修改低洼点
     * 
     * @param yjLowPoint 低洼点
     * @return 结果
     */
    public int updateYjLowPoint(YjLowPoint yjLowPoint);

    /**
     * 批量删除低洼点
     * 
     * @param ids 需要删除的低洼点主键集合
     * @return 结果
     */
    public int deleteYjLowPointByIds(Long[] ids);

    /**
     * 删除低洼点信息
     * 
     * @param id 低洼点主键
     * @return 结果
     */
    public int deleteYjLowPointById(Long id);
}
