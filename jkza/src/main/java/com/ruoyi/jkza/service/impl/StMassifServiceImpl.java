package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StMassifMapper;
import com.ruoyi.jkza.domain.StMassif;
import com.ruoyi.jkza.service.IStMassifService;

/**
 * 地块数量Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
@Service
public class StMassifServiceImpl implements IStMassifService 
{
    @Autowired
    private StMassifMapper stMassifMapper;

    /**
     * 查询地块数量
     * 
     * @param id 地块数量主键
     * @return 地块数量
     */
    @Override
    public StMassif selectStMassifById(Long id)
    {
        return stMassifMapper.selectStMassifById(id);
    }

    /**
     * 查询地块数量列表
     * 
     * @param stMassif 地块数量
     * @return 地块数量
     */
    @Override
    public List<StMassif> selectStMassifList(StMassif stMassif)
    {
        return stMassifMapper.selectStMassifList(stMassif);
    }

    /**
     * 新增地块数量
     * 
     * @param stMassif 地块数量
     * @return 结果
     */
    @Override
    public int insertStMassif(StMassif stMassif)
    {
        return stMassifMapper.insertStMassif(stMassif);
    }

    /**
     * 修改地块数量
     * 
     * @param stMassif 地块数量
     * @return 结果
     */
    @Override
    public int updateStMassif(StMassif stMassif)
    {
        return stMassifMapper.updateStMassif(stMassif);
    }

    /**
     * 批量删除地块数量
     * 
     * @param ids 需要删除的地块数量主键
     * @return 结果
     */
    @Override
    public int deleteStMassifByIds(Long[] ids)
    {
        return stMassifMapper.deleteStMassifByIds(ids);
    }

    /**
     * 删除地块数量信息
     * 
     * @param id 地块数量主键
     * @return 结果
     */
    @Override
    public int deleteStMassifById(Long id)
    {
        return stMassifMapper.deleteStMassifById(id);
    }
}
