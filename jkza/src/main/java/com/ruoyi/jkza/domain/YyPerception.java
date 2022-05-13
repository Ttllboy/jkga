package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 感知信息对象 yy_perception
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class YyPerception extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 人脸抓拍 */
    @Excel(name = "人脸抓拍")
    private Long faceSnap;

    /** 车辆抓拍 */
    @Excel(name = "车辆抓拍")
    private Long carSnap;

    /** 测温人数 */
    @Excel(name = "测温人数")
    private Long temperatureSnap;

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
    public void setFaceSnap(Long faceSnap) 
    {
        this.faceSnap = faceSnap;
    }

    public Long getFaceSnap() 
    {
        return faceSnap;
    }
    public void setCarSnap(Long carSnap) 
    {
        this.carSnap = carSnap;
    }

    public Long getCarSnap() 
    {
        return carSnap;
    }
    public void setTemperatureSnap(Long temperatureSnap) 
    {
        this.temperatureSnap = temperatureSnap;
    }

    public Long getTemperatureSnap() 
    {
        return temperatureSnap;
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
            .append("faceSnap", getFaceSnap())
            .append("carSnap", getCarSnap())
            .append("temperatureSnap", getTemperatureSnap())
            .append("dataType", getDataType())
            .toString();
    }
}
