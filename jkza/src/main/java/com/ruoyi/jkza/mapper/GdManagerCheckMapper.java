package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdManagerCheck;

/**
 * 管理人员考勤Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public interface GdManagerCheckMapper 
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
     * 删除管理人员考勤
     * 
     * @param id 管理人员考勤主键
     * @return 结果
     */
    public int deleteGdManagerCheckById(Long id);

    /**
     * 批量删除管理人员考勤
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdManagerCheckByIds(Long[] ids);
}
