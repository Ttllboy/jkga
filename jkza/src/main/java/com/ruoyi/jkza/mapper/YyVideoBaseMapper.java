package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YyVideoBase;

/**
 * 视频列表Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface YyVideoBaseMapper 
{
    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    public YyVideoBase selectYyVideoBaseById(Long id);

    /**
     * 查询视频列表列表
     * 
     * @param yyVideoBase 视频列表
     * @return 视频列表集合
     */
    public List<YyVideoBase> selectYyVideoBaseList(YyVideoBase yyVideoBase);

    /**
     * 新增视频列表
     * 
     * @param yyVideoBase 视频列表
     * @return 结果
     */
    public int insertYyVideoBase(YyVideoBase yyVideoBase);

    /**
     * 修改视频列表
     * 
     * @param yyVideoBase 视频列表
     * @return 结果
     */
    public int updateYyVideoBase(YyVideoBase yyVideoBase);

    /**
     * 删除视频列表
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    public int deleteYyVideoBaseById(Long id);

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyVideoBaseByIds(Integer[] ids);
}
