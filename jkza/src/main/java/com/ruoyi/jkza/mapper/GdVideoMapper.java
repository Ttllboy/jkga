package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdVideo;

/**
 * 视频设备Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public interface GdVideoMapper 
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

    //查询视频设备列表与工地表链接查询
    public List<GdVideo> selectGdVideoListAndBuilding(GdVideo gdVideo);

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
     * 删除视频设备
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    public int deleteGdVideoById(Long id);

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdVideoByIds(Long[] ids);
}
