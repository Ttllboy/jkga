package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdVideoMapper;
import com.ruoyi.jkza.domain.GdVideo;
import com.ruoyi.jkza.service.IGdVideoService;

/**
 * 视频设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@Service
public class GdVideoServiceImpl implements IGdVideoService 
{
    @Autowired
    private GdVideoMapper gdVideoMapper;

    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    @Override
    public GdVideo selectGdVideoById(Long id)
    {
        return gdVideoMapper.selectGdVideoById(id);
    }

    /**
     * 查询视频设备列表
     * 
     * @param gdVideo 视频设备
     * @return 视频设备
     */
    @Override
    public List<GdVideo> selectGdVideoList(GdVideo gdVideo)
    {
        return gdVideoMapper.selectGdVideoList(gdVideo);
    }

    /**
     * 新增视频设备
     * 
     * @param gdVideo 视频设备
     * @return 结果
     */
    @Override
    public int insertGdVideo(GdVideo gdVideo)
    {
        return gdVideoMapper.insertGdVideo(gdVideo);
    }

    /**
     * 修改视频设备
     * 
     * @param gdVideo 视频设备
     * @return 结果
     */
    @Override
    public int updateGdVideo(GdVideo gdVideo)
    {
        return gdVideoMapper.updateGdVideo(gdVideo);
    }

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的视频设备主键
     * @return 结果
     */
    @Override
    public int deleteGdVideoByIds(Long[] ids)
    {
        return gdVideoMapper.deleteGdVideoByIds(ids);
    }

    /**
     * 删除视频设备信息
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    @Override
    public int deleteGdVideoById(Long id)
    {
        return gdVideoMapper.deleteGdVideoById(id);
    }
}
