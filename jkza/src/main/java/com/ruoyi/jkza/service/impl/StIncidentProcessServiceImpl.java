package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StIncidentProcessMapper;
import com.ruoyi.jkza.domain.StIncidentProcess;
import com.ruoyi.jkza.service.IStIncidentProcessService;

/**
 * 事件处理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StIncidentProcessServiceImpl implements IStIncidentProcessService 
{
    @Autowired
    private StIncidentProcessMapper stIncidentProcessMapper;

    /**
     * 查询事件处理
     * 
     * @param id 事件处理主键
     * @return 事件处理
     */
    @Override
    public StIncidentProcess selectStIncidentProcessById(Long id)
    {
        return stIncidentProcessMapper.selectStIncidentProcessById(id);
    }

    /**
     * 查询事件处理列表
     * 
     * @param stIncidentProcess 事件处理
     * @return 事件处理
     */
    @Override
    public List<StIncidentProcess> selectStIncidentProcessList(StIncidentProcess stIncidentProcess)
    {
        return stIncidentProcessMapper.selectStIncidentProcessList(stIncidentProcess);
    }

    /**
     * 新增事件处理
     * 
     * @param stIncidentProcess 事件处理
     * @return 结果
     */
    @Override
    public int insertStIncidentProcess(StIncidentProcess stIncidentProcess)
    {
        return stIncidentProcessMapper.insertStIncidentProcess(stIncidentProcess);
    }

    /**
     * 修改事件处理
     * 
     * @param stIncidentProcess 事件处理
     * @return 结果
     */
    @Override
    public int updateStIncidentProcess(StIncidentProcess stIncidentProcess)
    {
        return stIncidentProcessMapper.updateStIncidentProcess(stIncidentProcess);
    }

    /**
     * 批量删除事件处理
     * 
     * @param ids 需要删除的事件处理主键
     * @return 结果
     */
    @Override
    public int deleteStIncidentProcessByIds(Long[] ids)
    {
        return stIncidentProcessMapper.deleteStIncidentProcessByIds(ids);
    }

    /**
     * 删除事件处理信息
     * 
     * @param id 事件处理主键
     * @return 结果
     */
    @Override
    public int deleteStIncidentProcessById(Long id)
    {
        return stIncidentProcessMapper.deleteStIncidentProcessById(id);
    }
}
