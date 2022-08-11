package com.ruoyi.jkza.mapper;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.jkza.domain.YyVideo;
import org.apache.ibatis.annotations.Param;

/**
 * 视频设备Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-18
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
    public int truncateYyVideo();

    /**
     * 批量删除视频设备
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyVideoByIds(Long[] ids);
    public List<HashMap> selectYyClassify(@Param("channelCode") String channelCode);
}
