package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.StVideoBase;

/**
 * 视频列表Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IStVideoBaseService 
{
    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    public StVideoBase selectStVideoBaseById(Long id);

    /**
     * 查询视频列表列表
     * 
     * @param stVideoBase 视频列表
     * @return 视频列表集合
     */
    public List<StVideoBase> selectStVideoBaseList(StVideoBase stVideoBase);

    /**
     * 新增视频列表
     * 
     * @param stVideoBase 视频列表
     * @return 结果
     */
    public int insertStVideoBase(StVideoBase stVideoBase);

    /**
     * 修改视频列表
     * 
     * @param stVideoBase 视频列表
     * @return 结果
     */
    public int updateStVideoBase(StVideoBase stVideoBase);

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的视频列表主键集合
     * @return 结果
     */
    public int deleteStVideoBaseByIds(Integer[] ids);

    /**
     * 删除视频列表信息
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    public int deleteStVideoBaseById(Long id);
}
