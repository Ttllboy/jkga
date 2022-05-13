package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频设备对象 yj_video
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
public class YjVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 低洼点ID */
    @Excel(name = "低洼点ID")
    private Long buildingSiteId;

    /** 视频设备名称 */
    @Excel(name = "视频设备名称")
    private String videoName;

    /** 低洼点名称 */
    @Excel(name = "低洼点名称")
    private String lowPointName;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Integer equipmentType;

    /** 在线状态 */
    @Excel(name = "在线状态")
    private Integer onlineType;

    /** 坐标 */
    @Excel(name = "坐标")
    private String coordinate;

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
    public void setBuildingSiteId(Long buildingSiteId) 
    {
        this.buildingSiteId = buildingSiteId;
    }

    public Long getBuildingSiteId() 
    {
        return buildingSiteId;
    }
    public void setVideoName(String videoName) 
    {
        this.videoName = videoName;
    }

    public String getLowPointName() {
        return lowPointName;
    }
    public void setCoordinate(String coordinate)
    {
        this.coordinate = coordinate;
    }

    public String getCoordinate()
    {
        return coordinate;
    }

    public void setLowPointName(String lowPointName) {
        this.lowPointName = lowPointName;
    }

    public String getVideoName()
    {
        return videoName;
    }
    public void setEquipmentType(Integer equipmentType) 
    {
        this.equipmentType = equipmentType;
    }

    public Integer getEquipmentType() 
    {
        return equipmentType;
    }
    public void setOnlineType(Integer onlineType) 
    {
        this.onlineType = onlineType;
    }

    public Integer getOnlineType() 
    {
        return onlineType;
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
            .append("buildingSiteId", getBuildingSiteId())
            .append("videoName", getVideoName())
            .append("equipmentType", getEquipmentType())
            .append("onlineType", getOnlineType())
            .append("dataType", getDataType())
            .toString();
    }
}
