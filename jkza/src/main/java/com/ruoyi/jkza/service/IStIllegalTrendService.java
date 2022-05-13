package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.StIllegalTrend;

/**
 * 非法趋势Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IStIllegalTrendService 
{
    /**
     * 查询非法趋势
     * 
     * @param id 非法趋势主键
     * @return 非法趋势
     */
    public StIllegalTrend selectStIllegalTrendById(Long id);

    /**
     * 查询非法趋势列表
     * 
     * @param stIllegalTrend 非法趋势
     * @return 非法趋势集合
     */
    public List<StIllegalTrend> selectStIllegalTrendList(StIllegalTrend stIllegalTrend);

    /**
     * 新增非法趋势
     * 
     * @param stIllegalTrend 非法趋势
     * @return 结果
     */
    public int insertStIllegalTrend(StIllegalTrend stIllegalTrend);

    /**
     * 修改非法趋势
     * 
     * @param stIllegalTrend 非法趋势
     * @return 结果
     */
    public int updateStIllegalTrend(StIllegalTrend stIllegalTrend);

    /**
     * 批量删除非法趋势
     * 
     * @param ids 需要删除的非法趋势主键集合
     * @return 结果
     */
    public int deleteStIllegalTrendByIds(Integer[] ids);

    /**
     * 删除非法趋势信息
     * 
     * @param id 非法趋势主键
     * @return 结果
     */
    public int deleteStIllegalTrendById(Long id);
}
