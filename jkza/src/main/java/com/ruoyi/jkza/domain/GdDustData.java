package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 扬尘设备数据对象 gd_dust_data
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public class GdDustData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 所属工地 */
    @Excel(name = "所属工地")
    private Long buildingId;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String equipmentNum;

    /** 统计时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** PM2.5值 */
    @Excel(name = "PM2.5值")
    private Double pm25;

    /** PM10值 */
    @Excel(name = "PM10值")
    private Double pm10;

    /** 温度 */
    @Excel(name = "温度")
    private Double temperature;

    /** 湿度 */
    @Excel(name = "湿度")
    private Double humidity;

    /** 噪音 */
    @Excel(name = "噪音")
    private Double noise;

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
    public void setBuildingId(Long buildingId)
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId()
    {
        return buildingId;
    }
    public void setEquipmentNum(String equipmentNum)
    {
        this.equipmentNum = equipmentNum;
    }

    public String getEquipmentNum()
    {
        return equipmentNum;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }
    public void setPm25(Double pm25)
    {
        this.pm25 = pm25;
    }

    public Double getPm25()
    {
        return pm25;
    }
    public void setPm10(Double pm10)
    {
        this.pm10 = pm10;
    }

    public Double getPm10()
    {
        return pm10;
    }
    public void setTemperature(Double temperature)
    {
        this.temperature = temperature;
    }

    public Double getTemperature()
    {
        return temperature;
    }
    public void setHumidity(Double humidity)
    {
        this.humidity = humidity;
    }

    public Double getHumidity()
    {
        return humidity;
    }
    public void setNoise(Double noise)
    {
        this.noise = noise;
    }

    public Double getNoise()
    {
        return noise;
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
            .append("buildingId", getBuildingId())
            .append("equipmentNum", getEquipmentNum())
            .append("date", getDate())
            .append("pm25", getPm25())
            .append("pm10", getPm10())
            .append("temperature", getTemperature())
            .append("humidity", getHumidity())
            .append("noise", getNoise())
            .append("dataType", getDataType())
            .toString();
    }
}
