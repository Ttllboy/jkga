package com.ruoyi.jkza.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础数据对象 st_base
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class StBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 空气质量 */
    @Excel(name = "空气质量")
    private Long airQuality;

    /** 固废点总容量 */
    @Excel(name = "固废点总容量")
    private BigDecimal solidWasteCapacity;

    /** 当前水质情况 */
    @Excel(name = "当前水质情况")
    private Long waterQuality;

    /** 水质情况均值 */
    @Excel(name = "水质情况均值")
    private Long waterQualityAverage;

    /** 接入设备数 */
    @Excel(name = "接入设备数")
    private Long accessEquipment;

    /** 在线设备数 */
    @Excel(name = "在线设备数")
    private Long onlineEquipment;

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
    public void setAirQuality(Long airQuality) 
    {
        this.airQuality = airQuality;
    }

    public Long getAirQuality() 
    {
        return airQuality;
    }
    public void setSolidWasteCapacity(BigDecimal solidWasteCapacity) 
    {
        this.solidWasteCapacity = solidWasteCapacity;
    }

    public BigDecimal getSolidWasteCapacity() 
    {
        return solidWasteCapacity;
    }
    public void setWaterQuality(Long waterQuality) 
    {
        this.waterQuality = waterQuality;
    }

    public Long getWaterQuality() 
    {
        return waterQuality;
    }
    public void setWaterQualityAverage(Long waterQualityAverage) 
    {
        this.waterQualityAverage = waterQualityAverage;
    }

    public Long getWaterQualityAverage() 
    {
        return waterQualityAverage;
    }
    public void setAccessEquipment(Long accessEquipment) 
    {
        this.accessEquipment = accessEquipment;
    }

    public Long getAccessEquipment() 
    {
        return accessEquipment;
    }
    public void setOnlineEquipment(Long onlineEquipment) 
    {
        this.onlineEquipment = onlineEquipment;
    }

    public Long getOnlineEquipment() 
    {
        return onlineEquipment;
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
            .append("airQuality", getAirQuality())
            .append("solidWasteCapacity", getSolidWasteCapacity())
            .append("waterQuality", getWaterQuality())
            .append("waterQualityAverage", getWaterQualityAverage())
            .append("accessEquipment", getAccessEquipment())
            .append("onlineEquipment", getOnlineEquipment())
            .append("dataType", getDataType())
            .toString();
    }
}
