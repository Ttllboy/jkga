package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdVideoBaseMapper;
import com.ruoyi.jkza.domain.GdVideoBase;
import com.ruoyi.jkza.service.IGdVideoBaseService;

/**
 * 视频列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@Service
public class GdVideoBaseServiceImpl implements IGdVideoBaseService 
{
    @Autowired
    private GdVideoBaseMapper gdVideoBaseMapper;

    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    @Override
    public GdVideoBase selectGdVideoBaseById(Long id)
    {
        return gdVideoBaseMapper.selectGdVideoBaseById(id);
    }

    /**
     * 查询视频列表列表
     * 
     * @param gdVideoBase 视频列表
     * @return 视频列表
     */
    @Override
    public List<GdVideoBase> selectGdVideoBaseList(GdVideoBase gdVideoBase)
    {
        return gdVideoBaseMapper.selectGdVideoBaseList(gdVideoBase);
    }

    /**
     * 新增视频列表
     * 
     * @param gdVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int insertGdVideoBase(GdVideoBase gdVideoBase)
    {
        return gdVideoBaseMapper.insertGdVideoBase(gdVideoBase);
    }

    /**
     * 修改视频列表
     * 
     * @param gdVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int updateGdVideoBase(GdVideoBase gdVideoBase)
    {
        return gdVideoBaseMapper.updateGdVideoBase(gdVideoBase);
    }

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的视频列表主键
     * @return 结果
     */
    @Override
    public int deleteGdVideoBaseByIds(Long[] ids)
    {
        return gdVideoBaseMapper.deleteGdVideoBaseByIds(ids);
    }

    /**
     * 删除视频列表信息
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    @Override
    public int deleteGdVideoBaseById(Long id)
    {
        return gdVideoBaseMapper.deleteGdVideoBaseById(id);
    }
}
