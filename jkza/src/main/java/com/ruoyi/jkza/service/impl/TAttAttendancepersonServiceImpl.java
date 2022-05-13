package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.TAttAttendancepersonMapper;
import com.ruoyi.jkza.domain.TAttAttendanceperson;
import com.ruoyi.jkza.service.ITAttAttendancepersonService;

/**
 * 管理人员信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
@Service
public class TAttAttendancepersonServiceImpl implements ITAttAttendancepersonService 
{
    @Autowired
    private TAttAttendancepersonMapper tAttAttendancepersonMapper;

    /**
     * 查询管理人员信息
     * 
     * @param projectguid 管理人员信息主键
     * @return 管理人员信息
     */
    @Override
    public TAttAttendanceperson selectTAttAttendancepersonByProjectguid(String projectguid)
    {
        return tAttAttendancepersonMapper.selectTAttAttendancepersonByProjectguid(projectguid);
    }

    /**
     * 查询管理人员信息列表
     * 
     * @param tAttAttendanceperson 管理人员信息
     * @return 管理人员信息
     */
    @Override
    public List<TAttAttendanceperson> selectTAttAttendancepersonList(TAttAttendanceperson tAttAttendanceperson)
    {
        return tAttAttendancepersonMapper.selectTAttAttendancepersonList(tAttAttendanceperson);
    }

    /**
     * 新增管理人员信息
     * 
     * @param tAttAttendanceperson 管理人员信息
     * @return 结果
     */
    @Override
    public int insertTAttAttendanceperson(TAttAttendanceperson tAttAttendanceperson)
    {
        tAttAttendanceperson.setCreateTime(DateUtils.getNowDate());
        return tAttAttendancepersonMapper.insertTAttAttendanceperson(tAttAttendanceperson);
    }

    /**
     * 修改管理人员信息
     * 
     * @param tAttAttendanceperson 管理人员信息
     * @return 结果
     */
    @Override
    public int updateTAttAttendanceperson(TAttAttendanceperson tAttAttendanceperson)
    {
        return tAttAttendancepersonMapper.updateTAttAttendanceperson(tAttAttendanceperson);
    }

    /**
     * 批量删除管理人员信息
     * 
     * @param projectguids 需要删除的管理人员信息主键
     * @return 结果
     */
    @Override
    public int deleteTAttAttendancepersonByProjectguids(String[] projectguids)
    {
        return tAttAttendancepersonMapper.deleteTAttAttendancepersonByProjectguids(projectguids);
    }

    /**
     * 删除管理人员信息信息
     * 
     * @param projectguid 管理人员信息主键
     * @return 结果
     */
    @Override
    public int deleteTAttAttendancepersonByProjectguid(String projectguid)
    {
        return tAttAttendancepersonMapper.deleteTAttAttendancepersonByProjectguid(projectguid);
    }
}
