package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdManagerCheck;

/**
 * 管理人员考勤Service接口
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public interface IGdManagerCheckService 
{
    /**
     * 查询管理人员考勤
     * 
     * @param id 管理人员考勤主键
     * @return 管理人员考勤
     */
    public GdManagerCheck selectGdManagerCheckById(Long id);

    /**
     * 查询管理人员考勤列表
     * 
     * @param gdManagerCheck 管理人员考勤
     * @return 管理人员考勤集合
     */
    public List<GdManagerCheck> selectGdManagerCheckList(GdManagerCheck gdManagerCheck);

    /**
     * 新增管理人员考勤
     * 
     * @param gdManagerCheck 管理人员考勤
     * @return 结果
     */
    public int insertGdManagerCheck(GdManagerCheck gdManagerCheck);

    /**
     * 修改管理人员考勤
     * 
     * @param gdManagerCheck 管理人员考勤
     * @return 结果
     */
    public int updateGdManagerCheck(GdManagerCheck gdManagerCheck);

    /**
     * 批量删除管理人员考勤
     * 
     * @param ids 需要删除的管理人员考勤主键集合
     * @return 结果
     */
    public int deleteGdManagerCheckByIds(Long[] ids);

    /**
     * 删除管理人员考勤信息
     * 
     * @param id 管理人员考勤主键
     * @return 结果
     */
    public int deleteGdManagerCheckById(Long id);
}
