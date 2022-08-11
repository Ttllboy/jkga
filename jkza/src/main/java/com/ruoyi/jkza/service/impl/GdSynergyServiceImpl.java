package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdSynergyMapper;
import com.ruoyi.jkza.domain.GdSynergy;
import com.ruoyi.jkza.service.IGdSynergyService;

/**
 * 整改通知Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
@Service
public class GdSynergyServiceImpl implements IGdSynergyService 
{
    @Autowired
    private GdSynergyMapper gdSynergyMapper;

    /**
     * 查询整改通知
     * 
     * @param id 整改通知主键
     * @return 整改通知
     */
    @Override
    public GdSynergy selectGdSynergyById(Long id)
    {
        return gdSynergyMapper.selectGdSynergyById(id);
    }

    /**
     * 查询整改通知列表
     * 
     * @param gdSynergy 整改通知
     * @return 整改通知
     */
    @Override
    public List<GdSynergy> selectGdSynergyList(GdSynergy gdSynergy)
    {
        return gdSynergyMapper.selectGdSynergyList(gdSynergy);
    }

    /**
     * 新增整改通知
     * 
     * @param gdSynergy 整改通知
     * @return 结果
     */
    @Override
    public int insertGdSynergy(GdSynergy gdSynergy)
    {
        gdSynergy.setCreateTime(DateUtils.getNowDate());
        return gdSynergyMapper.insertGdSynergy(gdSynergy);
    }

    /**
     * 修改整改通知
     * 
     * @param gdSynergy 整改通知
     * @return 结果
     */
    @Override
    public int updateGdSynergy(GdSynergy gdSynergy)
    {
        return gdSynergyMapper.updateGdSynergy(gdSynergy);
    }

    /**
     * 批量删除整改通知
     * 
     * @param ids 需要删除的整改通知主键
     * @return 结果
     */
    @Override
    public int deleteGdSynergyByIds(Long[] ids)
    {
        return gdSynergyMapper.deleteGdSynergyByIds(ids);
    }

    /**
     * 删除整改通知信息
     * 
     * @param id 整改通知主键
     * @return 结果
     */
    @Override
    public int deleteGdSynergyById(Long id)
    {
        return gdSynergyMapper.deleteGdSynergyById(id);
    }
}
