package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.StIncidentProcess;

/**
 * 事件处理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface StIncidentProcessMapper 
{
    /**
     * 查询事件处理
     * 
     * @param id 事件处理主键
     * @return 事件处理
     */
    public StIncidentProcess selectStIncidentProcessById(Long id);

    /**
     * 查询事件处理列表
     * 
     * @param stIncidentProcess 事件处理
     * @return 事件处理集合
     */
    public List<StIncidentProcess> selectStIncidentProcessList(StIncidentProcess stIncidentProcess);

    /**
     * 新增事件处理
     * 
     * @param stIncidentProcess 事件处理
     * @return 结果
     */
    public int insertStIncidentProcess(StIncidentProcess stIncidentProcess);

    /**
     * 修改事件处理
     * 
     * @param stIncidentProcess 事件处理
     * @return 结果
     */
    public int updateStIncidentProcess(StIncidentProcess stIncidentProcess);

    /**
     * 删除事件处理
     * 
     * @param id 事件处理主键
     * @return 结果
     */
    public int deleteStIncidentProcessById(Long id);

    /**
     * 批量删除事件处理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStIncidentProcessByIds(Long[] ids);
}
