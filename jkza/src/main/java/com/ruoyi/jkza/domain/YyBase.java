package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础数据对象 yy_base
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class YyBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 医院总数 */
    @Excel(name = "医院总数")
    private Long hosTotal;

    /** 接入医院数 */
    @Excel(name = "接入医院数")
    private Long hosAccess;

    /** 监控数量 */
    @Excel(name = "监控数量")
    private Long monitor;

    /** 在线监控数量 */
    @Excel(name = "在线监控数量")
    private Long monitorOnline;

    /** 测温一体机数量 */
    @Excel(name = "测温一体机数量")
    private Long temperature;

    /** 测温一体机在线数 */
    @Excel(name = "测温一体机在线数")
    private Long temperatureOnline;

    /** 人脸抓拍摄像头数 */
    @Excel(name = "人脸抓拍摄像头数")
    private Long faceCamera;

    /** 人脸抓拍摄像头在线数 */
    @Excel(name = "人脸抓拍摄像头在线数")
    private Long faceCameraOnline;

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
    public void setHosTotal(Long hosTotal) 
    {
        this.hosTotal = hosTotal;
    }

    public Long getHosTotal() 
    {
        return hosTotal;
    }
    public void setHosAccess(Long hosAccess) 
    {
        this.hosAccess = hosAccess;
    }

    public Long getHosAccess() 
    {
        return hosAccess;
    }
    public void setMonitor(Long monitor) 
    {
        this.monitor = monitor;
    }

    public Long getMonitor() 
    {
        return monitor;
    }
    public void setMonitorOnline(Long monitorOnline) 
    {
        this.monitorOnline = monitorOnline;
    }

    public Long getMonitorOnline() 
    {
        return monitorOnline;
    }
    public void setTemperature(Long temperature) 
    {
        this.temperature = temperature;
    }

    public Long getTemperature() 
    {
        return temperature;
    }
    public void setTemperatureOnline(Long temperatureOnline) 
    {
        this.temperatureOnline = temperatureOnline;
    }

    public Long getTemperatureOnline() 
    {
        return temperatureOnline;
    }
    public void setFaceCamera(Long faceCamera) 
    {
        this.faceCamera = faceCamera;
    }

    public Long getFaceCamera() 
    {
        return faceCamera;
    }
    public void setFaceCameraOnline(Long faceCameraOnline) 
    {
        this.faceCameraOnline = faceCameraOnline;
    }

    public Long getFaceCameraOnline() 
    {
        return faceCameraOnline;
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
            .append("hosTotal", getHosTotal())
            .append("hosAccess", getHosAccess())
            .append("monitor", getMonitor())
            .append("monitorOnline", getMonitorOnline())
            .append("temperature", getTemperature())
            .append("temperatureOnline", getTemperatureOnline())
            .append("faceCamera", getFaceCamera())
            .append("faceCameraOnline", getFaceCameraOnline())
            .append("dataType", getDataType())
            .toString();
    }
}
