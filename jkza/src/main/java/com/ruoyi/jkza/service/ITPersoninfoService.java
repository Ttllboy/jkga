package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.TPersoninfo;

/**
 * 工人考勤Service接口
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
public interface ITPersoninfoService 
{
    /**
     * 查询工人考勤
     * 
     * @param projectguid 工人考勤主键
     * @return 工人考勤
     */
    public TPersoninfo selectTPersoninfoByProjectguid(String projectguid);

    /**
     * 查询工人考勤列表
     * 
     * @param tPersoninfo 工人考勤
     * @return 工人考勤集合
     */
    public List<TPersoninfo> selectTPersoninfoList(TPersoninfo tPersoninfo);

    /**
     * 新增工人考勤
     * 
     * @param tPersoninfo 工人考勤
     * @return 结果
     */
    public int insertTPersoninfo(TPersoninfo tPersoninfo);

    /**
     * 修改工人考勤
     * 
     * @param tPersoninfo 工人考勤
     * @return 结果
     */
    public int updateTPersoninfo(TPersoninfo tPersoninfo);

    /**
     * 批量删除工人考勤
     * 
     * @param projectguids 需要删除的工人考勤主键集合
     * @return 结果
     */
    public int deleteTPersoninfoByProjectguids(String[] projectguids);

    /**
     * 删除工人考勤信息
     * 
     * @param projectguid 工人考勤主键
     * @return 结果
     */
    public int deleteTPersoninfoByProjectguid(String projectguid);
}
