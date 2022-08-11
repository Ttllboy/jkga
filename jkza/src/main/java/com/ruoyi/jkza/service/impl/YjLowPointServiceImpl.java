package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjLowPointMapper;
import com.ruoyi.jkza.domain.YjLowPoint;
import com.ruoyi.jkza.service.IYjLowPointService;

/**
 * 低洼点Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-20
 */
@Service
public class YjLowPointServiceImpl implements IYjLowPointService 
{
    @Autowired
    private YjLowPointMapper yjLowPointMapper;

    /**
     * 查询低洼点
     * 
     * @param id 低洼点主键
     * @return 低洼点
     */
    @Override
    public YjLowPoint selectYjLowPointById(Long id)
    {
        return yjLowPointMapper.selectYjLowPointById(id);
    }

    /**
     * 查询低洼点列表
     * 
     * @param yjLowPoint 低洼点
     * @return 低洼点
     */
    @Override
    public List<YjLowPoint> selectYjLowPointList(YjLowPoint yjLowPoint)
    {
        return yjLowPointMapper.selectYjLowPointList(yjLowPoint);
    }

    /**
     * 新增低洼点
     * 
     * @param yjLowPoint 低洼点
     * @return 结果
     */
    @Override
    public int insertYjLowPoint(YjLowPoint yjLowPoint)
    {
        return yjLowPointMapper.insertYjLowPoint(yjLowPoint);
    }

    /**
     * 修改低洼点
     * 
     * @param yjLowPoint 低洼点
     * @return 结果
     */
    @Override
    public int updateYjLowPoint(YjLowPoint yjLowPoint)
    {
        return yjLowPointMapper.updateYjLowPoint(yjLowPoint);
    }

    /**
     * 批量删除低洼点
     * 
     * @param ids 需要删除的低洼点主键
     * @return 结果
     */
    @Override
    public int deleteYjLowPointByIds(Long[] ids)
    {
        return yjLowPointMapper.deleteYjLowPointByIds(ids);
    }

    /**
     * 删除低洼点信息
     * 
     * @param id 低洼点主键
     * @return 结果
     */
    @Override
    public int deleteYjLowPointById(Long id)
    {
        return yjLowPointMapper.deleteYjLowPointById(id);
    }
}
