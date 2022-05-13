package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StVideoBaseMapper;
import com.ruoyi.jkza.domain.StVideoBase;
import com.ruoyi.jkza.service.IStVideoBaseService;

/**
 * 视频列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StVideoBaseServiceImpl implements IStVideoBaseService 
{
    @Autowired
    private StVideoBaseMapper stVideoBaseMapper;

    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    @Override
    public StVideoBase selectStVideoBaseById(Long id)
    {
        return stVideoBaseMapper.selectStVideoBaseById(id);
    }

    /**
     * 查询视频列表列表
     * 
     * @param stVideoBase 视频列表
     * @return 视频列表
     */
    @Override
    public List<StVideoBase> selectStVideoBaseList(StVideoBase stVideoBase)
    {
        return stVideoBaseMapper.selectStVideoBaseList(stVideoBase);
    }

    /**
     * 新增视频列表
     * 
     * @param stVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int insertStVideoBase(StVideoBase stVideoBase)
    {
        return stVideoBaseMapper.insertStVideoBase(stVideoBase);
    }

    /**
     * 修改视频列表
     * 
     * @param stVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int updateStVideoBase(StVideoBase stVideoBase)
    {
        return stVideoBaseMapper.updateStVideoBase(stVideoBase);
    }

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的视频列表主键
     * @return 结果
     */
    @Override
    public int deleteStVideoBaseByIds(Integer[] ids)
    {
        return stVideoBaseMapper.deleteStVideoBaseByIds(ids);
    }

    /**
     * 删除视频列表信息
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    @Override
    public int deleteStVideoBaseById(Long id)
    {
        return stVideoBaseMapper.deleteStVideoBaseById(id);
    }
}
