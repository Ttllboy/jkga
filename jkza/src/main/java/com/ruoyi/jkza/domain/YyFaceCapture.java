package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人脸抓拍对象 yy_face_capture
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class YyFaceCapture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 人脸照片 */
    @Excel(name = "人脸照片")
    private String faceImg;

    /** 抓拍时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抓拍时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date snapTime;

    /** 抓拍位置 */
    @Excel(name = "抓拍位置")
    private String snapPlace;

    /** 体温数据 */
    @Excel(name = "体温数据")
    private Double temperature;

    /** 健康码 */
    @Excel(name = "健康码")
    private Integer healthCode;

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
    public void setFaceImg(String faceImg) 
    {
        this.faceImg = faceImg;
    }

    public String getFaceImg() 
    {
        return faceImg;
    }
    public void setSnapTime(Date snapTime) 
    {
        this.snapTime = snapTime;
    }

    public Date getSnapTime() 
    {
        return snapTime;
    }
    public void setSnapPlace(String snapPlace) 
    {
        this.snapPlace = snapPlace;
    }

    public String getSnapPlace() 
    {
        return snapPlace;
    }
    public void setTemperature(Double temperature)
    {
        this.temperature = temperature;
    }

    public Double getTemperature()
    {
        return temperature;
    }
    public void setHealthCode(Integer healthCode) 
    {
        this.healthCode = healthCode;
    }

    public Integer getHealthCode() 
    {
        return healthCode;
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
            .append("faceImg", getFaceImg())
            .append("snapTime", getSnapTime())
            .append("snapPlace", getSnapPlace())
            .append("temperature", getTemperature())
            .append("healthCode", getHealthCode())
            .append("dataType", getDataType())
            .toString();
    }
}
