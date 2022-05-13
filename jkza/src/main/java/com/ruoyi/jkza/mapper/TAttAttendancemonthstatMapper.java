package com.ruoyi.jkza.mapper;

import java.util.List;

import com.ruoyi.jkza.domain.DateCal;
import com.ruoyi.jkza.domain.TAttAttendancemonthstat;

/**
 * 管理人员考勤数据Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
public interface TAttAttendancemonthstatMapper 
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
     * 删除管理人员考勤数据
     * 
     * @param projectguid 管理人员考勤数据主键
     * @return 结果
     */
    public int deleteTAttAttendancemonthstatByProjectguid(String projectguid);

    /**
     * 批量删除管理人员考勤数据
     * 
     * @param projectguids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTAttAttendancemonthstatByProjectguids(String[] projectguids);

    public List<TAttAttendancemonthstat> selectList(DateCal dateCal);
    public List<TAttAttendancemonthstat> selectListByGd(DateCal dateCal);

}
