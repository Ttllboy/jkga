package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StWarnProportionMapper;
import com.ruoyi.jkza.domain.StWarnProportion;
import com.ruoyi.jkza.service.IStWarnProportionService;

/**
 * 预警占比Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StWarnProportionServiceImpl implements IStWarnProportionService 
{
    @Autowired
    private StWarnProportionMapper stWarnProportionMapper;

    /**
     * 查询预警占比
     * 
     * @param id 预警占比主键
     * @return 预警占比
     */
    @Override
    public StWarnProportion selectStWarnProportionById(Long id)
    {
        return stWarnProportionMapper.selectStWarnProportionById(id);
    }

    /**
     * 查询预警占比列表
     * 
     * @param stWarnProportion 预警占比
     * @return 预警占比
     */
    @Override
    public List<StWarnProportion> selectStWarnProportionList(StWarnProportion stWarnProportion)
    {
        return stWarnProportionMapper.selectStWarnProportionList(stWarnProportion);
    }

    /**
     * 新增预警占比
     * 
     * @param stWarnProportion 预警占比
     * @return 结果
     */
    @Override
    public int insertStWarnProportion(StWarnProportion stWarnProportion)
    {
        return stWarnProportionMapper.insertStWarnProportion(stWarnProportion);
    }

    /**
     * 修改预警占比
     * 
     * @param stWarnProportion 预警占比
     * @return 结果
     */
    @Override
    public int updateStWarnProportion(StWarnProportion stWarnProportion)
    {
        return stWarnProportionMapper.updateStWarnProportion(stWarnProportion);
    }

    /**
     * 批量删除预警占比
     * 
     * @param ids 需要删除的预警占比主键
     * @return 结果
     */
    @Override
    public int deleteStWarnProportionByIds(Integer[] ids)
    {
        return stWarnProportionMapper.deleteStWarnProportionByIds(ids);
    }

    /**
     * 删除预警占比信息
     * 
     * @param id 预警占比主键
     * @return 结果
     */
    @Override
    public int deleteStWarnProportionById(Long id)
    {
        return stWarnProportionMapper.deleteStWarnProportionById(id);
    }
}
