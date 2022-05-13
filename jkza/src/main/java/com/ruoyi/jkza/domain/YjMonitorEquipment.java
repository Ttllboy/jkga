package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 监测设备对象 yj_monitor_equipment
 * 
 * @author ruoyi
 * @date 2022-04-17
 */
public class YjMonitorEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 监测设备接入数 */
    @Excel(name = "监测设备接入数")
    private Long monitorEquipment;

    /** 监测设备在线数 */
    @Excel(name = "监测设备在线数")
    private Long monitorEquipmentDuty;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private Integer dataType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMonitorEquipment(Long monitorEquipment) 
    {
        this.monitorEquipment = monitorEquipment;
    }

    public Long getMonitorEquipment() 
    {
        return monitorEquipment;
    }
    public void setMonitorEquipmentDuty(Long monitorEquipmentDuty) 
    {
        this.monitorEquipmentDuty = monitorEquipmentDuty;
    }

    public Long getMonitorEquipmentDuty() 
    {
        return monitorEquipmentDuty;
    }
    public void setDataType(Integer dataType) 
    {
        this.dataType = dataType;
    }

    public Integer getDataType() 
    {
        return dataType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("monitorEquipment", getMonitorEquipment())
            .append("monitorEquipmentDuty", getMonitorEquipmentDuty())
            .append("dataType", getDataType())
            .toString();
    }
}
