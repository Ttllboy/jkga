package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdBaseMapper;
import com.ruoyi.jkza.domain.GdBase;
import com.ruoyi.jkza.service.IGdBaseService;

/**
 * 基础数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@Service
public class GdBaseServiceImpl implements IGdBaseService 
{
    @Autowired
    private GdBaseMapper gdBaseMapper;

    /**
     * 查询基础数据
     * 
     * @param id 基础数据主键
     * @return 基础数据
     */
    @Override
    public GdBase selectGdBaseById(Long id)
    {
        return gdBaseMapper.selectGdBaseById(id);
    }

    /**
     * 查询基础数据列表
     * 
     * @param gdBase 基础数据
     * @return 基础数据
     */
    @Override
    public List<GdBase> selectGdBaseList(GdBase gdBase)
    {
        return gdBaseMapper.selectGdBaseList(gdBase);
    }

    /**
     * 新增基础数据
     * 
     * @param gdBase 基础数据
     * @return 结果
     */
    @Override
    public int insertGdBase(GdBase gdBase)
    {
        return gdBaseMapper.insertGdBase(gdBase);
    }

    /**
     * 修改基础数据
     * 
     * @param gdBase 基础数据
     * @return 结果
     */
    @Override
    public int updateGdBase(GdBase gdBase)
    {
        return gdBaseMapper.updateGdBase(gdBase);
    }

    /**
     * 批量删除基础数据
     * 
     * @param ids 需要删除的基础数据主键
     * @return 结果
     */
    @Override
    public int deleteGdBaseByIds(Long[] ids)
    {
        return gdBaseMapper.deleteGdBaseByIds(ids);
    }

    /**
     * 删除基础数据信息
     * 
     * @param id 基础数据主键
     * @return 结果
     */
    @Override
    public int deleteGdBaseById(Long id)
    {
        return gdBaseMapper.deleteGdBaseById(id);
    }
}
