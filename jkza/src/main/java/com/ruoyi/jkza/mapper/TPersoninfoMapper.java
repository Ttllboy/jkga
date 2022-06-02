package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.TPersoninfo;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 工人考勤Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
public interface TPersoninfoMapper 
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
    public List<TPersoninfo> selectList(TPersoninfo tPersoninfo);
    public List<TPersoninfo> selectIndex();
    public List<TPersoninfo> selectTPersoninfoListSize(TPersoninfo tPersoninfo);

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
     * 删除工人考勤
     * 
     * @param projectguid 工人考勤主键
     * @return 结果
     */
    public int deleteTPersoninfoByProjectguid(String projectguid);

    /**
     * 批量删除工人考勤
     * 
     * @param projectguids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPersoninfoByProjectguids(String[] projectguids);
}
