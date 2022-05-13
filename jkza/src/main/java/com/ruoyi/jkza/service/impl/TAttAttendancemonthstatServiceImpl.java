package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.TAttAttendancemonthstatMapper;
import com.ruoyi.jkza.domain.TAttAttendancemonthstat;
import com.ruoyi.jkza.service.ITAttAttendancemonthstatService;

/**
 * 管理人员考勤数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
@Service
public class TAttAttendancemonthstatServiceImpl implements ITAttAttendancemonthstatService 
{
    @Autowired
    private TAttAttendancemonthstatMapper tAttAttendancemonthstatMapper;

    /**
     * 查询管理人员考勤数据
     * 
     * @param projectguid 管理人员考勤数据主键
     * @return 管理人员考勤数据
     */
    @Override
    public TAttAttendancemonthstat selectTAttAttendancemonthstatByProjectguid(String projectguid)
    {
        return tAttAttendancemonthstatMapper.selectTAttAttendancemonthstatByProjectguid(projectguid);
    }

    /**
     * 查询管理人员考勤数据列表
     * 
     * @param tAttAttendancemonthstat 管理人员考勤数据
     * @return 管理人员考勤数据
     */
    @Override
    public List<TAttAttendancemonthstat> selectTAttAttendancemonthstatList(TAttAttendancemonthstat tAttAttendancemonthstat)
    {
        return tAttAttendancemonthstatMapper.selectTAttAttendancemonthstatList(tAttAttendancemonthstat);
    }

    /**
     * 新增管理人员考勤数据
     * 
     * @param tAttAttendancemonthstat 管理人员考勤数据
     * @return 结果
     */
    @Override
    public int insertTAttAttendancemonthstat(TAttAttendancemonthstat tAttAttendancemonthstat)
    {
        tAttAttendancemonthstat.setCreateTime(DateUtils.getNowDate());
        return tAttAttendancemonthstatMapper.insertTAttAttendancemonthstat(tAttAttendancemonthstat);
    }

    /**
     * 修改管理人员考勤数据
     * 
     * @param tAttAttendancemonthstat 管理人员考勤数据
     * @return 结果
     */
    @Override
    public int updateTAttAttendancemonthstat(TAttAttendancemonthstat tAttAttendancemonthstat)
    {
        return tAttAttendancemonthstatMapper.updateTAttAttendancemonthstat(tAttAttendancemonthstat);
    }

    /**
     * 批量删除管理人员考勤数据
     * 
     * @param projectguids 需要删除的管理人员考勤数据主键
     * @return 结果
     */
    @Override
    public int deleteTAttAttendancemonthstatByProjectguids(String[] projectguids)
    {
        return tAttAttendancemonthstatMapper.deleteTAttAttendancemonthstatByProjectguids(projectguids);
    }

    /**
     * 删除管理人员考勤数据信息
     * 
     * @param projectguid 管理人员考勤数据主键
     * @return 结果
     */
    @Override
    public int deleteTAttAttendancemonthstatByProjectguid(String projectguid)
    {
        return tAttAttendancemonthstatMapper.deleteTAttAttendancemonthstatByProjectguid(projectguid);
    }
}
