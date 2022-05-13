package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjVideoMapper;
import com.ruoyi.jkza.domain.YjVideo;
import com.ruoyi.jkza.service.IYjVideoService;

/**
 * 视频设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
@Service
public class YjVideoServiceImpl implements IYjVideoService 
{
    @Autowired
    private YjVideoMapper yjVideoMapper;

    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    @Override
    public YjVideo selectYjVideoById(Long id)
    {
        return yjVideoMapper.selectYjVideoById(id);
    }

    /**
     * 查询视频设备列表
     * 
     * @param yjVideo 视频设备
     * @return 视频设备
     */
    @Override
    public List<YjVideo> selectYjVideoList(YjVideo yjVideo)
    {
        return yjVideoMapper.selectYjVideoList(yjVideo);
    }
    @Override
    public List<YjVideo> selectYjVideoListAndName(YjVideo yjVideo)
    {
        return yjVideoMapper.selectYjVideoListAndName(yjVideo);
    }

    /**
     * 新增视频设备
     * 
     * @param yjVideo 视频设备
     * @return 结果
     */
    @Override
    public int insertYjVideo(YjVideo yjVideo)
    {
        return yjVideoMapper.insertYjVideo(yjVideo);
    }

    /**
     * 修改视频设备
     * 
     * @param yjVideo 视频设备
     * @return 结果
     */
    @Override
    public int updateYjVideo(YjVideo yjVideo)
    {
        return yjVideoMapper.updateYjVideo(yjVideo);
    }

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的视频设备主键
     * @return 结果
     */
    @Override
    public int deleteYjVideoByIds(Integer[] ids)
    {
        return yjVideoMapper.deleteYjVideoByIds(ids);
    }

    /**
     * 删除视频设备信息
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    @Override
    public int deleteYjVideoById(Long id)
    {
        return yjVideoMapper.deleteYjVideoById(id);
    }
}
