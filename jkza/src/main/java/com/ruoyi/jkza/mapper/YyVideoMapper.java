package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YyVideo;

/**
 * 视频设备Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface YyVideoMapper 
{
    /**
     * 查询视频设备
     * 
     * @param id 视频设备主键
     * @return 视频设备
     */
    public YyVideo selectYyVideoById(Long id);

    /**
     * 查询视频设备列表
     * 
     * @param yyVideo 视频设备
     * @return 视频设备集合
     */
    public List<YyVideo> selectYyVideoList(YyVideo yyVideo);
    public List<YyVideo> selectYyVideoListAndName(YyVideo yyVideo);

    /**
     * 新增视频设备
     * 
     * @param yyVideo 视频设备
     * @return 结果
     */
    public int insertYyVideo(YyVideo yyVideo);

    /**
     * 修改视频设备
     * 
     * @param yyVideo 视频设备
     * @return 结果
     */
    public int updateYyVideo(YyVideo yyVideo);

    /**
     * 删除视频设备
     * 
     * @param id 视频设备主键
     * @return 结果
     */
    public int deleteYyVideoById(Long id);

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyVideoByIds(Integer[] ids);
}
