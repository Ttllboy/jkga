package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.StWarn;

/**
 * 预警信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IStWarnService 
{
    /**
     * 查询预警信息
     * 
     * @param id 预警信息主键
     * @return 预警信息
     */
    public StWarn selectStWarnById(Long id);

    /**
     * 查询预警信息列表
     * 
     * @param stWarn 预警信息
     * @return 预警信息集合
     */
    public List<StWarn> selectStWarnList(StWarn stWarn);

    /**
     * 新增预警信息
     * 
     * @param stWarn 预警信息
     * @return 结果
     */
    public int insertStWarn(StWarn stWarn);

    /**
     * 修改预警信息
     * 
     * @param stWarn 预警信息
     * @return 结果
     */
    public int updateStWarn(StWarn stWarn);

    /**
     * 批量删除预警信息
     * 
     * @param ids 需要删除的预警信息主键集合
     * @return 结果
     */
    public int deleteStWarnByIds(Integer[] ids);

    /**
     * 删除预警信息信息
     * 
     * @param id 预警信息主键
     * @return 结果
     */
    public int deleteStWarnById(Long id);
}
