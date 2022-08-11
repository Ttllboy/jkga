package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyVideoMapper;
import com.ruoyi.jkza.domain.YyVideo;
import com.ruoyi.jkza.service.IYyVideoService;

/**
 * 视频设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@Service
public class YyVideoServiceImpl implements IYyVideoService 
{
    @Autowired
    private YyVideoMapper yyVideoMapper;

    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    @Override
    public YyVideo selectYyVideoById(Long id)
    {
        return yyVideoMapper.selectYyVideoById(id);
    }

    /**
     * 查询视频设备列表
     * 
     * @param yyVideo 视频设备
     * @return 视频设备
     */
    @Override
    public List<YyVideo> selectYyVideoList(YyVideo yyVideo)
    {
        return yyVideoMapper.selectYyVideoList(yyVideo);
    }

    /**
     * 新增视频设备
     * 
     * @param yyVideo 视频设备
     * @return 结果
     */
    @Override
    public int insertYyVideo(YyVideo yyVideo)
    {
        return yyVideoMapper.insertYyVideo(yyVideo);
    }

    /**
     * 修改视频设备
     * 
     * @param yyVideo 视频设备
     * @return 结果
     */
    @Override
    public int updateYyVideo(YyVideo yyVideo)
    {
        return yyVideoMapper.updateYyVideo(yyVideo);
    }

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的视频设备主键
     * @return 结果
     */
    @Override
    public int deleteYyVideoByIds(Long[] ids)
    {
        return yyVideoMapper.deleteYyVideoByIds(ids);
    }

    /**
     * 删除视频设备信息
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    @Override
    public int deleteYyVideoById(Long id)
    {
        return yyVideoMapper.deleteYyVideoById(id);
    }
}
