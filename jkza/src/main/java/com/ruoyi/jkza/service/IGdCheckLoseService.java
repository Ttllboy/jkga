package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdCheckLose;

/**
 * 考勤缺失Service接口
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public interface IGdCheckLoseService 
{
    /**
     * 查询考勤缺失
     * 
     * @param id 考勤缺失主键
     * @return 考勤缺失
     */
    public GdCheckLose selectGdCheckLoseById(Long id);

    /**
     * 查询考勤缺失列表
     * 
     * @param gdCheckLose 考勤缺失
     * @return 考勤缺失集合
     */
    public List<GdCheckLose> selectGdCheckLoseList(GdCheckLose gdCheckLose);

    /**
     * 新增考勤缺失
     * 
     * @param gdCheckLose 考勤缺失
     * @return 结果
     */
    public int insertGdCheckLose(GdCheckLose gdCheckLose);

    /**
     * 修改考勤缺失
     * 
     * @param gdCheckLose 考勤缺失
     * @return 结果
     */
    public int updateGdCheckLose(GdCheckLose gdCheckLose);

    /**
     * 批量删除考勤缺失
     * 
     * @param ids 需要删除的考勤缺失主键集合
     * @return 结果
     */
    public int deleteGdCheckLoseByIds(Long[] ids);

    /**
     * 删除考勤缺失信息
     * 
     * @param id 考勤缺失主键
     * @return 结果
     */
    public int deleteGdCheckLoseById(Long id);
}
