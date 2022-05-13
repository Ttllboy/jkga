package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频设备对象 gd_video
 *
 * @author ruoyi
 * @date 2022-04-12
 */
public class GdVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 工地ID */
    @Excel(name = "工地ID")
    private Long buildingSiteId;

    /** 视频设备名称 */
    @Excel(name = "视频设备名称")
    private String videoName;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Integer equipmentType;

    /** 在线状态 */
    @Excel(name = "在线状态")
    private Integer onlineType;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 经度 */
    @Excel(name = "经度")
    private String gpsx;

    /** 纬度 */
    @Excel(name = "纬度")
    private String gpsy;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private Integer dataType;

    /** 工地名称 */
    @Excel(name = "工地名称")
    private String buildingSiteName;

    public String getBuildingSiteName() {
        return buildingSiteName;
    }

    public void setBuildingSiteName(String buildingSiteName) {
        this.buildingSiteName = buildingSiteName;
    }

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
    public void setChannelCode(String channelCode)
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode()
    {
        return channelCode;
    }
    public void setGpsx(String gpsx)
    {
        this.gpsx = gpsx;
    }

    public String getGpsx()
    {
        return gpsx;
    }
    public void setGpsy(String gpsy)
    {
        this.gpsy = gpsy;
    }

    public String getGpsy()
    {
        return gpsy;
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
                .append("channelCode", getChannelCode())
                .append("gpsx", getGpsx())
                .append("gpsy", getGpsy())
                .append("dataType", getDataType())
                .toString();
    }
}
