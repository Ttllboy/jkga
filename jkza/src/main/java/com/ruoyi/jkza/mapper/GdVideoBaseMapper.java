package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdVideoBase;

/**
 * 视频列表Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public interface GdVideoBaseMapper 
{
    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    public GdVideoBase selectGdVideoBaseById(Long id);

    /**
     * 查询视频列表列表
     * 
     * @param gdVideoBase 视频列表
     * @return 视频列表集合
     */
    public List<GdVideoBase> selectGdVideoBaseList(GdVideoBase gdVideoBase);

    /**
     * 新增视频列表
     * 
     * @param gdVideoBase 视频列表
     * @return 结果
     */
    public int insertGdVideoBase(GdVideoBase gdVideoBase);

    /**
     * 修改视频列表
     * 
     * @param gdVideoBase 视频列表
     * @return 结果
     */
    public int updateGdVideoBase(GdVideoBase gdVideoBase);

    /**
     * 删除视频列表
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    public int deleteGdVideoBaseById(Long id);

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdVideoBaseByIds(Long[] ids);
}
