package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YjVideo;

/**
 * 视频设备Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
public interface YjVideoMapper 
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
    public List<YjVideo> selectYjVideoListAndName(YjVideo yjVideo);

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
     * 删除视频设备
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    public int deleteYjVideoById(Long id);

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjVideoByIds(Integer[] ids);
}
