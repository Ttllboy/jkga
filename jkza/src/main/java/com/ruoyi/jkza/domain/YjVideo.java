package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频设备对象 yj_video
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public class YjVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 低洼点ID */
    @Excel(name = "低洼点ID")
    private Long lowpointId;

    /** 避灾点ID */
    @Excel(name = "避灾点ID")
    private Long bzdId;

    /** 驾驶舱ID */
    @Excel(name = "驾驶舱ID")
    private Integer jscId;

    /** 视频设备名称 */
    @Excel(name = "视频设备名称")
    private String channelName;

    /** 摄像机类型 */
    @Excel(name = "摄像机类型")
    private Integer cameraType;

    /** 在线状态 */
    @Excel(name = "在线状态")
    private Integer networkStatus;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 经度 */
    @Excel(name = "经度")
    private Double gpsX;

    /** 纬度 */
    @Excel(name = "纬度")
    private Double gpsY;

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
    public void setLowpointId(Long lowpointId) 
    {
        this.lowpointId = lowpointId;
    }

    public Long getLowpointId() 
    {
        return lowpointId;
    }
    public void setBzdId(Long bzdId) 
    {
        this.bzdId = bzdId;
    }

    public Long getBzdId() 
    {
        return bzdId;
    }
    public void setJscId(Integer jscId) 
    {
        this.jscId = jscId;
    }

    public Integer getJscId() 
    {
        return jscId;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setCameraType(Integer cameraType) 
    {
        this.cameraType = cameraType;
    }

    public Integer getCameraType() 
    {
        return cameraType;
    }
    public void setNetworkStatus(Integer networkStatus) 
    {
        this.networkStatus = networkStatus;
    }

    public Integer getNetworkStatus() 
    {
        return networkStatus;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setGpsX(Double gpsX)
    {
        this.gpsX = gpsX;
    }

    public Double getGpsX()
    {
        return gpsX;
    }
    public void setGpsY(Double gpsY)
    {
        this.gpsY = gpsY;
    }

    public Double getGpsY()
    {
        return gpsY;
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
            .append("lowpointId", getLowpointId())
            .append("bzdId", getBzdId())
            .append("jscId", getJscId())
            .append("channelName", getChannelName())
            .append("cameraType", getCameraType())
            .append("networkStatus", getNetworkStatus())
            .append("channelCode", getChannelCode())
            .append("gpsX", getGpsX())
            .append("gpsY", getGpsY())
            .append("dataType", getDataType())
            .toString();
    }
}
