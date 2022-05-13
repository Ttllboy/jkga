package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.TAttAttendancemonthstat;

/**
 * 管理人员考勤数据Service接口
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
public interface ITAttAttendancemonthstatService 
{
    /**
     * 查询管理人员考勤数据
     * 
     * @param projectguid 管理人员考勤数据主键
     * @return 管理人员考勤数据
     */
    public TAttAttendancemonthstat selectTAttAttendancemonthstatByProjectguid(String projectguid);

    /**
     * 查询管理人员考勤数据列表
     * 
     * @param tAttAttendancemonthstat 管理人员考勤数据
     * @return 管理人员考勤数据集合
     */
    public List<TAttAttendancemonthstat> selectTAttAttendancemonthstatList(TAttAttendancemonthstat tAttAttendancemonthstat);

    /**
     * 新增管理人员考勤数据
     * 
     * @param tAttAttendancemonthstat 管理人员考勤数据
     * @return 结果
     */
    public int insertTAttAttendancemonthstat(TAttAttendancemonthstat tAttAttendancemonthstat);

    /**
     * 修改管理人员考勤数据
     * 
     * @param tAttAttendancemonthstat 管理人员考勤数据
     * @return 结果
     */
    public int updateTAttAttendancemonthstat(TAttAttendancemonthstat tAttAttendancemonthstat);

    /**
     * 批量删除管理人员考勤数据
     * 
     * @param projectguids 需要删除的管理人员考勤数据主键集合
     * @return 结果
     */
    public int deleteTAttAttendancemonthstatByProjectguids(String[] projectguids);

    /**
     * 删除管理人员考勤数据信息
     * 
     * @param projectguid 管理人员考勤数据主键
     * @return 结果
     */
    public int deleteTAttAttendancemonthstatByProjectguid(String projectguid);
}
