package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdWorkerCheck;

/**
 * 考勤信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public interface IGdWorkerCheckService 
{
    /**
     * 查询考勤信息
     * 
     * @param id 考勤信息主键
     * @return 考勤信息
     */
    public GdWorkerCheck selectGdWorkerCheckById(Long id);

    /**
     * 查询考勤信息列表
     * 
     * @param gdWorkerCheck 考勤信息
     * @return 考勤信息集合
     */
    public List<GdWorkerCheck> selectGdWorkerCheckList(GdWorkerCheck gdWorkerCheck);

    /**
     * 新增考勤信息
     * 
     * @param gdWorkerCheck 考勤信息
     * @return 结果
     */
    public int insertGdWorkerCheck(GdWorkerCheck gdWorkerCheck);

    /**
     * 修改考勤信息
     * 
     * @param gdWorkerCheck 考勤信息
     * @return 结果
     */
    public int updateGdWorkerCheck(GdWorkerCheck gdWorkerCheck);

    /**
     * 批量删除考勤信息
     * 
     * @param ids 需要删除的考勤信息主键集合
     * @return 结果
     */
    public int deleteGdWorkerCheckByIds(Long[] ids);

    /**
     * 删除考勤信息信息
     * 
     * @param id 考勤信息主键
     * @return 结果
     */
    public int deleteGdWorkerCheckById(Long id);
}
