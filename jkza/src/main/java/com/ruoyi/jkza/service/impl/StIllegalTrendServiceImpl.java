package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StIllegalTrendMapper;
import com.ruoyi.jkza.domain.StIllegalTrend;
import com.ruoyi.jkza.service.IStIllegalTrendService;

/**
 * 非法趋势Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StIllegalTrendServiceImpl implements IStIllegalTrendService 
{
    @Autowired
    private StIllegalTrendMapper stIllegalTrendMapper;

    /**
     * 查询非法趋势
     * 
     * @param id 非法趋势主键
     * @return 非法趋势
     */
    @Override
    public StIllegalTrend selectStIllegalTrendById(Long id)
    {
        return stIllegalTrendMapper.selectStIllegalTrendById(id);
    }

    /**
     * 查询非法趋势列表
     * 
     * @param stIllegalTrend 非法趋势
     * @return 非法趋势
     */
    @Override
    public List<StIllegalTrend> selectStIllegalTrendList(StIllegalTrend stIllegalTrend)
    {
        return stIllegalTrendMapper.selectStIllegalTrendList(stIllegalTrend);
    }

    /**
     * 新增非法趋势
     * 
     * @param stIllegalTrend 非法趋势
     * @return 结果
     */
    @Override
    public int insertStIllegalTrend(StIllegalTrend stIllegalTrend)
    {
        return stIllegalTrendMapper.insertStIllegalTrend(stIllegalTrend);
    }

    /**
     * 修改非法趋势
     * 
     * @param stIllegalTrend 非法趋势
     * @return 结果
     */
    @Override
    public int updateStIllegalTrend(StIllegalTrend stIllegalTrend)
    {
        return stIllegalTrendMapper.updateStIllegalTrend(stIllegalTrend);
    }

    /**
     * 批量删除非法趋势
     * 
     * @param ids 需要删除的非法趋势主键
     * @return 结果
     */
    @Override
    public int deleteStIllegalTrendByIds(Integer[] ids)
    {
        return stIllegalTrendMapper.deleteStIllegalTrendByIds(ids);
    }

    /**
     * 删除非法趋势信息
     * 
     * @param id 非法趋势主键
     * @return 结果
     */
    @Override
    public int deleteStIllegalTrendById(Long id)
    {
        return stIllegalTrendMapper.deleteStIllegalTrendById(id);
    }
}
