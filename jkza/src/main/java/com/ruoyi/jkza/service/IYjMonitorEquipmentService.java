package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjMonitorEquipment;

/**
 * 监测设备Service接口
 * 
 * @author ruoyi
 * @date 2022-04-17
 */
public interface IYjMonitorEquipmentService 
{
    /**
     * 查询监测设备
     * 
     * @param id 监测设备主键
     * @return 监测设备
     */
    public YjMonitorEquipment selectYjMonitorEquipmentById(Long id);

    /**
     * 查询监测设备列表
     * 
     * @param yjMonitorEquipment 监测设备
     * @return 监测设备集合
     */
    public List<YjMonitorEquipment> selectYjMonitorEquipmentList(YjMonitorEquipment yjMonitorEquipment);

    /**
     * 新增监测设备
     * 
     * @param yjMonitorEquipment 监测设备
     * @return 结果
     */
    public int insertYjMonitorEquipment(YjMonitorEquipment yjMonitorEquipment);

    /**
     * 修改监测设备
     * 
     * @param yjMonitorEquipment 监测设备
     * @return 结果
     */
    public int updateYjMonitorEquipment(YjMonitorEquipment yjMonitorEquipment);

    /**
     * 批量删除监测设备
     * 
     * @param ids 需要删除的监测设备主键集合
     * @return 结果
     */
    public int deleteYjMonitorEquipmentByIds(Long[] ids);

    /**
     * 删除监测设备信息
     * 
     * @param id 监测设备主键
     * @return 结果
     */
    public int deleteYjMonitorEquipmentById(Long id);
}
