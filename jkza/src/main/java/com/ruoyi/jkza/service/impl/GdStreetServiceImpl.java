package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdStreetMapper;
import com.ruoyi.jkza.domain.GdStreet;
import com.ruoyi.jkza.service.IGdStreetService;

/**
 * 街道信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
@Service
public class GdStreetServiceImpl implements IGdStreetService 
{
    @Autowired
    private GdStreetMapper gdStreetMapper;

    /**
     * 查询街道信息
     * 
     * @param id 街道信息主键
     * @return 街道信息
     */
    @Override
    public GdStreet selectGdStreetById(Long id)
    {
        return gdStreetMapper.selectGdStreetById(id);
    }

    /**
     * 查询街道信息列表
     * 
     * @param gdStreet 街道信息
     * @return 街道信息
     */
    @Override
    public List<GdStreet> selectGdStreetList(GdStreet gdStreet)
    {
        return gdStreetMapper.selectGdStreetList(gdStreet);
    }

    /**
     * 新增街道信息
     * 
     * @param gdStreet 街道信息
     * @return 结果
     */
    @Override
    public int insertGdStreet(GdStreet gdStreet)
    {
        return gdStreetMapper.insertGdStreet(gdStreet);
    }

    /**
     * 修改街道信息
     * 
     * @param gdStreet 街道信息
     * @return 结果
     */
    @Override
    public int updateGdStreet(GdStreet gdStreet)
    {
        return gdStreetMapper.updateGdStreet(gdStreet);
    }

    /**
     * 批量删除街道信息
     * 
     * @param ids 需要删除的街道信息主键
     * @return 结果
     */
    @Override
    public int deleteGdStreetByIds(Long[] ids)
    {
        return gdStreetMapper.deleteGdStreetByIds(ids);
    }

    /**
     * 删除街道信息信息
     * 
     * @param id 街道信息主键
     * @return 结果
     */
    @Override
    public int deleteGdStreetById(Long id)
    {
        return gdStreetMapper.deleteGdStreetById(id);
    }

    @Override
    public int selectGdStreetByName(String name)
    {
        return gdStreetMapper.selectGdStreetByName(name);
    }
}
