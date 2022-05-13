package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjVideoBase;

/**
 * 视频列表Service接口
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
public interface IYjVideoBaseService 
{
    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    public YjVideoBase selectYjVideoBaseById(Long id);

    /**
     * 查询视频列表列表
     * 
     * @param yjVideoBase 视频列表
     * @return 视频列表集合
     */
    public List<YjVideoBase> selectYjVideoBaseList(YjVideoBase yjVideoBase);

    /**
     * 新增视频列表
     * 
     * @param yjVideoBase 视频列表
     * @return 结果
     */
    public int insertYjVideoBase(YjVideoBase yjVideoBase);

    /**
     * 修改视频列表
     * 
     * @param yjVideoBase 视频列表
     * @return 结果
     */
    public int updateYjVideoBase(YjVideoBase yjVideoBase);

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的视频列表主键集合
     * @return 结果
     */
    public int deleteYjVideoBaseByIds(Integer[] ids);

    /**
     * 删除视频列表信息
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    public int deleteYjVideoBaseById(Long id);
}
