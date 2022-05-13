package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.StBase;

/**
 * 基础数据Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface StBaseMapper 
{
    /**
     * 查询基础数据
     * 
     * @param id 基础数据主键
     * @return 基础数据
     */
    public StBase selectStBaseById(Long id);

    /**
     * 查询基础数据列表
     * 
     * @param stBase 基础数据
     * @return 基础数据集合
     */
    public List<StBase> selectStBaseList(StBase stBase);

    /**
     * 新增基础数据
     * 
     * @param stBase 基础数据
     * @return 结果
     */
    public int insertStBase(StBase stBase);

    /**
     * 修改基础数据
     * 
     * @param stBase 基础数据
     * @return 结果
     */
    public int updateStBase(StBase stBase);

    /**
     * 删除基础数据
     * 
     * @param id 基础数据主键
     * @return 结果
     */
    public int deleteStBaseById(Long id);

    /**
     * 批量删除基础数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStBaseByIds(Long[] ids);
}
