package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdVideo;

/**
 * 视频设备Service接口
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public interface IGdVideoService 
{
    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    public GdVideo selectGdVideoById(Long id);

    /**
     * 查询视频设备列表
     * 
     * @param gdVideo 视频设备
     * @return 视频设备集合
     */
    public List<GdVideo> selectGdVideoList(GdVideo gdVideo);

    /**
     * 新增视频设备
     * 
     * @param gdVideo 视频设备
     * @return 结果
     */
    public int insertGdVideo(GdVideo gdVideo);

    /**
     * 修改视频设备
     * 
     * @param gdVideo 视频设备
     * @return 结果
     */
    public int updateGdVideo(GdVideo gdVideo);

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的视频设备主键集合
     * @return 结果
     */
    public int deleteGdVideoByIds(Long[] ids);

    /**
     * 删除视频设备信息
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    public int deleteGdVideoById(Long id);
}
