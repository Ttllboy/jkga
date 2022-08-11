package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.StVideo;

/**
 * 视频设备Service接口
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public interface IStVideoService 
{
    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    public StVideo selectStVideoById(Long id);

    /**
     * 查询视频设备列表
     * 
     * @param stVideo 视频设备
     * @return 视频设备集合
     */
    public List<StVideo> selectStVideoList(StVideo stVideo);

    /**
     * 新增视频设备
     * 
     * @param stVideo 视频设备
     * @return 结果
     */
    public int insertStVideo(StVideo stVideo);

    /**
     * 修改视频设备
     * 
     * @param stVideo 视频设备
     * @return 结果
     */
    public int updateStVideo(StVideo stVideo);

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的视频设备主键集合
     * @return 结果
     */
    public int deleteStVideoByIds(Long[] ids);

    /**
     * 删除视频设备信息
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    public int deleteStVideoById(Long id);
}
