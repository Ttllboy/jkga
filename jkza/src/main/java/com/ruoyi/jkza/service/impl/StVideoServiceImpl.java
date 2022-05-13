package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StVideoMapper;
import com.ruoyi.jkza.domain.StVideo;
import com.ruoyi.jkza.service.IStVideoService;

/**
 * 视频设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StVideoServiceImpl implements IStVideoService 
{
    @Autowired
    private StVideoMapper stVideoMapper;

    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    @Override
    public StVideo selectStVideoById(Long id)
    {
        return stVideoMapper.selectStVideoById(id);
    }

    /**
     * 查询视频设备列表
     * 
     * @param stVideo 视频设备
     * @return 视频设备
     */
    @Override
    public List<StVideo> selectStVideoList(StVideo stVideo)
    {
        return stVideoMapper.selectStVideoList(stVideo);
    }
public List<StVideo> selectStVideoListAndName(StVideo stVideo)
    {
        return stVideoMapper.selectStVideoListAndName(stVideo);
    }

    /**
     * 新增视频设备
     * 
     * @param stVideo 视频设备
     * @return 结果
     */
    @Override
    public int insertStVideo(StVideo stVideo)
    {
        return stVideoMapper.insertStVideo(stVideo);
    }

    /**
     * 修改视频设备
     * 
     * @param stVideo 视频设备
     * @return 结果
     */
    @Override
    public int updateStVideo(StVideo stVideo)
    {
        return stVideoMapper.updateStVideo(stVideo);
    }

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的视频设备主键
     * @return 结果
     */
    @Override
    public int deleteStVideoByIds(Integer[] ids)
    {
        return stVideoMapper.deleteStVideoByIds(ids);
    }

    /**
     * 删除视频设备信息
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    @Override
    public int deleteStVideoById(Long id)
    {
        return stVideoMapper.deleteStVideoById(id);
    }
}
