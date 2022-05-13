package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.StIncidentProcess;

/**
 * 事件处理Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IStIncidentProcessService 
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
     * 批量删除事件处理
     * 
     * @param ids 需要删除的事件处理主键集合
     * @return 结果
     */
    public int deleteStIncidentProcessByIds(Long[] ids);

    /**
     * 删除事件处理信息
     * 
     * @param id 事件处理主键
     * @return 结果
     */
    public int deleteStIncidentProcessById(Long id);
}
