package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjMonitorEquipmentMapper;
import com.ruoyi.jkza.domain.YjMonitorEquipment;
import com.ruoyi.jkza.service.IYjMonitorEquipmentService;

/**
 * 监测设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-17
 */
@Service
public class YjMonitorEquipmentServiceImpl implements IYjMonitorEquipmentService 
{
    @Autowired
    private YjMonitorEquipmentMapper yjMonitorEquipmentMapper;

    /**
     * 查询监测设备
     * 
     * @param id 监测设备主键
     * @return 监测设备
     */
    @Override
    public YjMonitorEquipment selectYjMonitorEquipmentById(Long id)
    {
        return yjMonitorEquipmentMapper.selectYjMonitorEquipmentById(id);
    }

    /**
     * 查询监测设备列表
     * 
     * @param yjMonitorEquipment 监测设备
     * @return 监测设备
     */
    @Override
    public List<YjMonitorEquipment> selectYjMonitorEquipmentList(YjMonitorEquipment yjMonitorEquipment)
    {
        return yjMonitorEquipmentMapper.selectYjMonitorEquipmentList(yjMonitorEquipment);
    }

    /**
     * 新增监测设备
     * 
     * @param yjMonitorEquipment 监测设备
     * @return 结果
     */
    @Override
    public int insertYjMonitorEquipment(YjMonitorEquipment yjMonitorEquipment)
    {
        return yjMonitorEquipmentMapper.insertYjMonitorEquipment(yjMonitorEquipment);
    }

    /**
     * 修改监测设备
     * 
     * @param yjMonitorEquipment 监测设备
     * @return 结果
     */
    @Override
    public int updateYjMonitorEquipment(YjMonitorEquipment yjMonitorEquipment)
    {
        return yjMonitorEquipmentMapper.updateYjMonitorEquipment(yjMonitorEquipment);
    }

    /**
     * 批量删除监测设备
     * 
     * @param ids 需要删除的监测设备主键
     * @return 结果
     */
    @Override
    public int deleteYjMonitorEquipmentByIds(Long[] ids)
    {
        return yjMonitorEquipmentMapper.deleteYjMonitorEquipmentByIds(ids);
    }

    /**
     * 删除监测设备信息
     * 
     * @param id 监测设备主键
     * @return 结果
     */
    @Override
    public int deleteYjMonitorEquipmentById(Long id)
    {
        return yjMonitorEquipmentMapper.deleteYjMonitorEquipmentById(id);
    }
}
