package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 塔吊预警对象 gd_tower
 * 
 * @author ruoyi
 * @date 2022-06-25
 */
public class GdTower extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目id */
    @Excel(name = "项目id")
    private String projectId;

    /** 数据时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warnTime;

    /** 备案号 */
    @Excel(name = "备案号")
    private String recordNo;

    /** 力矩 */
    @Excel(name = "力矩")
    private Double moment;

    /** 载重 */
    @Excel(name = "载重")
    private Double loadWeight;

    /** 幅度 */
    @Excel(name = "幅度")
    private Double margin;

    /** 高度上 */
    @Excel(name = "高度上")
    private Double heightUp;

    /** 高度下 */
    @Excel(name = "高度下")
    private Double heightLower;

    /** 回转 */
    @Excel(name = "回转")
    private Double rotation;

    /** 风速 */
    @Excel(name = "风速")
    private Double windSpeed;

    /** 载重预警 */
    @Excel(name = "载重预警")
    private String weightWarn;

    /** 力矩预警 */
    @Excel(name = "力矩预警")
    private String momentWarn;

    /** 右回转预警 */
    @Excel(name = "右回转预警")
    private String rightRotationWarn;

    /** 左回转预警 */
    @Excel(name = "左回转预警")
    private String leftRotationWarn;

    /** 高度下预警 */
    @Excel(name = "高度下预警")
    private String heightLowerWarn;

    /** 高度上预警 */
    @Excel(name = "高度上预警")
    private String heightUpWarn;

    /** Y轴倾角预警 */
    @Excel(name = "Y轴倾角预警")
    private String inclinationYWarn;

    /** X轴倾角预警 */
    @Excel(name = "X轴倾角预警")
    private String inclinationXWarn;

    /** 风速预警 */
    @Excel(name = "风速预警")
    private String windSpeedWarn;

    /** 小幅预警 */
    @Excel(name = "小幅预警")
    private String smallMarginWarn;

    /** 大幅预警 */
    @Excel(name = "大幅预警")
    private String bigMarginWarn;

    /** 传感器状态 */
    @Excel(name = "传感器状态")
    private String sensorStatus;

    /** 传感器类型1异常0正常 */
    @Excel(name = "传感器类型1异常0正常")
    private Integer sensorType;

    /** 工地名称 */
    @Excel(name = "工地名称")
    private String projectName;

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
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setWarnTime(Date warnTime) 
    {
        this.warnTime = warnTime;
    }

    public Date getWarnTime() 
    {
        return warnTime;
    }
    public void setRecordNo(String recordNo) 
    {
        this.recordNo = recordNo;
    }

    public String getRecordNo() 
    {
        return recordNo;
    }
    public void setMoment(Double moment) 
    {
        this.moment = moment;
    }

    public Double getMoment() 
    {
        return moment;
    }
    public void setLoadWeight(Double loadWeight) 
    {
        this.loadWeight = loadWeight;
    }

    public Double getLoadWeight() 
    {
        return loadWeight;
    }
    public void setMargin(Double margin) 
    {
        this.margin = margin;
    }

    public Double getMargin() 
    {
        return margin;
    }
    public void setHeightUp(Double heightUp) 
    {
        this.heightUp = heightUp;
    }

    public Double getHeightUp() 
    {
        return heightUp;
    }
    public void setHeightLower(Double heightLower) 
    {
        this.heightLower = heightLower;
    }

    public Double getHeightLower() 
    {
        return heightLower;
    }
    public void setRotation(Double rotation) 
    {
        this.rotation = rotation;
    }

    public Double getRotation() 
    {
        return rotation;
    }
    public void setWindSpeed(Double windSpeed) 
    {
        this.windSpeed = windSpeed;
    }

    public Double getWindSpeed() 
    {
        return windSpeed;
    }
    public void setWeightWarn(String weightWarn) 
    {
        this.weightWarn = weightWarn;
    }

    public String getWeightWarn() 
    {
        return weightWarn;
    }
    public void setMomentWarn(String momentWarn) 
    {
        this.momentWarn = momentWarn;
    }

    public String getMomentWarn() 
    {
        return momentWarn;
    }
    public void setRightRotationWarn(String rightRotationWarn) 
    {
        this.rightRotationWarn = rightRotationWarn;
    }

    public String getRightRotationWarn() 
    {
        return rightRotationWarn;
    }
    public void setLeftRotationWarn(String leftRotationWarn) 
    {
        this.leftRotationWarn = leftRotationWarn;
    }

    public String getLeftRotationWarn() 
    {
        return leftRotationWarn;
    }
    public void setHeightLowerWarn(String heightLowerWarn) 
    {
        this.heightLowerWarn = heightLowerWarn;
    }

    public String getHeightLowerWarn() 
    {
        return heightLowerWarn;
    }
    public void setHeightUpWarn(String heightUpWarn) 
    {
        this.heightUpWarn = heightUpWarn;
    }

    public String getHeightUpWarn() 
    {
        return heightUpWarn;
    }
    public void setInclinationYWarn(String inclinationYWarn) 
    {
        this.inclinationYWarn = inclinationYWarn;
    }

    public String getInclinationYWarn() 
    {
        return inclinationYWarn;
    }
    public void setInclinationXWarn(String inclinationXWarn) 
    {
        this.inclinationXWarn = inclinationXWarn;
    }

    public String getInclinationXWarn() 
    {
        return inclinationXWarn;
    }
    public void setWindSpeedWarn(String windSpeedWarn) 
    {
        this.windSpeedWarn = windSpeedWarn;
    }

    public String getWindSpeedWarn() 
    {
        return windSpeedWarn;
    }
    public void setSmallMarginWarn(String smallMarginWarn) 
    {
        this.smallMarginWarn = smallMarginWarn;
    }

    public String getSmallMarginWarn() 
    {
        return smallMarginWarn;
    }
    public void setBigMarginWarn(String bigMarginWarn) 
    {
        this.bigMarginWarn = bigMarginWarn;
    }

    public String getBigMarginWarn() 
    {
        return bigMarginWarn;
    }
    public void setSensorStatus(String sensorStatus) 
    {
        this.sensorStatus = sensorStatus;
    }

    public String getSensorStatus() 
    {
        return sensorStatus;
    }
    public void setSensorType(Integer sensorType) 
    {
        this.sensorType = sensorType;
    }

    public Integer getSensorType() 
    {
        return sensorType;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
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
            .append("projectId", getProjectId())
            .append("warnTime", getWarnTime())
            .append("recordNo", getRecordNo())
            .append("moment", getMoment())
            .append("loadWeight", getLoadWeight())
            .append("margin", getMargin())
            .append("heightUp", getHeightUp())
            .append("heightLower", getHeightLower())
            .append("rotation", getRotation())
            .append("windSpeed", getWindSpeed())
            .append("weightWarn", getWeightWarn())
            .append("momentWarn", getMomentWarn())
            .append("rightRotationWarn", getRightRotationWarn())
            .append("leftRotationWarn", getLeftRotationWarn())
            .append("heightLowerWarn", getHeightLowerWarn())
            .append("heightUpWarn", getHeightUpWarn())
            .append("inclinationYWarn", getInclinationYWarn())
            .append("inclinationXWarn", getInclinationXWarn())
            .append("windSpeedWarn", getWindSpeedWarn())
            .append("smallMarginWarn", getSmallMarginWarn())
            .append("bigMarginWarn", getBigMarginWarn())
            .append("sensorStatus", getSensorStatus())
            .append("sensorType", getSensorType())
            .append("projectName", getProjectName())
            .append("dataType", getDataType())
            .toString();
    }
}
