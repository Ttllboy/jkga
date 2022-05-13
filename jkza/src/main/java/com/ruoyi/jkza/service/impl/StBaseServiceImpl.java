package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StBaseMapper;
import com.ruoyi.jkza.domain.StBase;
import com.ruoyi.jkza.service.IStBaseService;

/**
 * 基础数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StBaseServiceImpl implements IStBaseService 
{
    @Autowired
    private StBaseMapper stBaseMapper;

    /**
     * 查询基础数据
     * 
     * @param id 基础数据主键
     * @return 基础数据
     */
    @Override
    public StBase selectStBaseById(Long id)
    {
        return stBaseMapper.selectStBaseById(id);
    }

    /**
     * 查询基础数据列表
     * 
     * @param stBase 基础数据
     * @return 基础数据
     */
    @Override
    public List<StBase> selectStBaseList(StBase stBase)
    {
        return stBaseMapper.selectStBaseList(stBase);
    }

    /**
     * 新增基础数据
     * 
     * @param stBase 基础数据
     * @return 结果
     */
    @Override
    public int insertStBase(StBase stBase)
    {
        return stBaseMapper.insertStBase(stBase);
    }

    /**
     * 修改基础数据
     * 
     * @param stBase 基础数据
     * @return 结果
     */
    @Override
    public int updateStBase(StBase stBase)
    {
        return stBaseMapper.updateStBase(stBase);
    }

    /**
     * 批量删除基础数据
     * 
     * @param ids 需要删除的基础数据主键
     * @return 结果
     */
    @Override
    public int deleteStBaseByIds(Long[] ids)
    {
        return stBaseMapper.deleteStBaseByIds(ids);
    }

    /**
     * 删除基础数据信息
     * 
     * @param id 基础数据主键
     * @return 结果
     */
    @Override
    public int deleteStBaseById(Long id)
    {
        return stBaseMapper.deleteStBaseById(id);
    }
}
