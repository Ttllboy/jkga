package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjVideo;

/**
 * 视频设备Service接口
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public interface IYjVideoService 
{
    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    public YjVideo selectYjVideoById(Long id);

    /**
     * 查询视频设备列表
     * 
     * @param yjVideo 视频设备
     * @return 视频设备集合
     */
    public List<YjVideo> selectYjVideoList(YjVideo yjVideo);

    /**
     * 新增视频设备
     * 
     * @param yjVideo 视频设备
     * @return 结果
     */
    public int insertYjVideo(YjVideo yjVideo);

    /**
     * 修改视频设备
     * 
     * @param yjVideo 视频设备
     * @return 结果
     */
    public int updateYjVideo(YjVideo yjVideo);

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的视频设备主键集合
     * @return 结果
     */
    public int deleteYjVideoByIds(Long[] ids);

    /**
     * 删除视频设备信息
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    public int deleteYjVideoById(Long id);
}
