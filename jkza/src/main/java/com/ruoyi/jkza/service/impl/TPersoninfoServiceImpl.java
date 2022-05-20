package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.TPersoninfoMapper;
import com.ruoyi.jkza.domain.TPersoninfo;
import com.ruoyi.jkza.service.ITPersoninfoService;

/**
 * 工人考勤Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
@Service
public class TPersoninfoServiceImpl implements ITPersoninfoService 
{
    @Autowired
    private TPersoninfoMapper tPersoninfoMapper;

    /**
     * 查询工人考勤
     * 
     * @param projectguid 工人考勤主键
     * @return 工人考勤
     */
    @Override
    public TPersoninfo selectTPersoninfoByProjectguid(String projectguid)
    {
        return tPersoninfoMapper.selectTPersoninfoByProjectguid(projectguid);
    }

    /**
     * 查询工人考勤列表
     * 
     * @param tPersoninfo 工人考勤
     * @return 工人考勤
     */
    @Override
    public List<TPersoninfo> selectTPersoninfoList(TPersoninfo tPersoninfo)
    {
        return tPersoninfoMapper.selectTPersoninfoList(tPersoninfo);
    }

    /**
     * 新增工人考勤
     * 
     * @param tPersoninfo 工人考勤
     * @return 结果
     */
    @Override
    public int insertTPersoninfo(TPersoninfo tPersoninfo)
    {
        tPersoninfo.setCreateTime(DateUtils.getNowDate());
        return tPersoninfoMapper.insertTPersoninfo(tPersoninfo);
    }

    /**
     * 修改工人考勤
     * 
     * @param tPersoninfo 工人考勤
     * @return 结果
     */
    @Override
    public int updateTPersoninfo(TPersoninfo tPersoninfo)
    {
        return tPersoninfoMapper.updateTPersoninfo(tPersoninfo);
    }

    /**
     * 批量删除工人考勤
     * 
     * @param projectguids 需要删除的工人考勤主键
     * @return 结果
     */
    @Override
    public int deleteTPersoninfoByProjectguids(String[] projectguids)
    {
        return tPersoninfoMapper.deleteTPersoninfoByProjectguids(projectguids);
    }

    /**
     * 删除工人考勤信息
     * 
     * @param projectguid 工人考勤主键
     * @return 结果
     */
    @Override
    public int deleteTPersoninfoByProjectguid(String projectguid)
    {
        return tPersoninfoMapper.deleteTPersoninfoByProjectguid(projectguid);
    }
}
