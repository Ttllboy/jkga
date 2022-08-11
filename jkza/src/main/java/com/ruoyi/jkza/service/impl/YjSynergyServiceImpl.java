package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjSynergyMapper;
import com.ruoyi.jkza.domain.YjSynergy;
import com.ruoyi.jkza.service.IYjSynergyService;

/**
 * 多跨协同Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-24
 */
@Service
public class YjSynergyServiceImpl implements IYjSynergyService 
{
    @Autowired
    private YjSynergyMapper yjSynergyMapper;

    /**
     * 查询多跨协同
     * 
     * @param id 多跨协同主键
     * @return 多跨协同
     */
    @Override
    public YjSynergy selectYjSynergyById(Long id)
    {
        return yjSynergyMapper.selectYjSynergyById(id);
    }

    /**
     * 查询多跨协同列表
     * 
     * @param yjSynergy 多跨协同
     * @return 多跨协同
     */
    @Override
    public List<YjSynergy> selectYjSynergyList(YjSynergy yjSynergy)
    {
        return yjSynergyMapper.selectYjSynergyList(yjSynergy);
    }

    /**
     * 新增多跨协同
     * 
     * @param yjSynergy 多跨协同
     * @return 结果
     */
    @Override
    public int insertYjSynergy(YjSynergy yjSynergy)
    {
        return yjSynergyMapper.insertYjSynergy(yjSynergy);
    }

    /**
     * 修改多跨协同
     * 
     * @param yjSynergy 多跨协同
     * @return 结果
     */
    @Override
    public int updateYjSynergy(YjSynergy yjSynergy)
    {
        return yjSynergyMapper.updateYjSynergy(yjSynergy);
    }

    /**
     * 批量删除多跨协同
     * 
     * @param ids 需要删除的多跨协同主键
     * @return 结果
     */
    @Override
    public int deleteYjSynergyByIds(Long[] ids)
    {
        return yjSynergyMapper.deleteYjSynergyByIds(ids);
    }

    /**
     * 删除多跨协同信息
     * 
     * @param id 多跨协同主键
     * @return 结果
     */
    @Override
    public int deleteYjSynergyById(Long id)
    {
        return yjSynergyMapper.deleteYjSynergyById(id);
    }
}
