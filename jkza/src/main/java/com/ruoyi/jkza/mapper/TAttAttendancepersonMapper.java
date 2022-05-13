package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.TAttAttendanceperson;

/**
 * 管理人员信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
public interface TAttAttendancepersonMapper 
{
    /**
     * 查询管理人员信息
     * 
     * @param projectguid 管理人员信息主键
     * @return 管理人员信息
     */
    public TAttAttendanceperson selectTAttAttendancepersonByProjectguid(String projectguid);

    /**
     * 查询管理人员信息列表
     * 
     * @param tAttAttendanceperson 管理人员信息
     * @return 管理人员信息集合
     */
    public List<TAttAttendanceperson> selectTAttAttendancepersonList(TAttAttendanceperson tAttAttendanceperson);

    /**
     * 新增管理人员信息
     * 
     * @param tAttAttendanceperson 管理人员信息
     * @return 结果
     */
    public int insertTAttAttendanceperson(TAttAttendanceperson tAttAttendanceperson);

    /**
     * 修改管理人员信息
     * 
     * @param tAttAttendanceperson 管理人员信息
     * @return 结果
     */
    public int updateTAttAttendanceperson(TAttAttendanceperson tAttAttendanceperson);

    /**
     * 删除管理人员信息
     * 
     * @param projectguid 管理人员信息主键
     * @return 结果
     */
    public int deleteTAttAttendancepersonByProjectguid(String projectguid);

    /**
     * 批量删除管理人员信息
     * 
     * @param projectguids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTAttAttendancepersonByProjectguids(String[] projectguids);
}
