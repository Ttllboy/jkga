package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆卡口对象 mq_car
 * 
 * @author ruoyi
 * @date 2022-06-23
 */
public class MqCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 抓拍记录的唯一 id */
    @Excel(name = "抓拍记录的唯一 id")
    private String recordId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String plateNum;

    /** 抓拍时间 */
    @Excel(name = "抓拍时间")
    private Long capTime;

    /** 车牌类型 */
    @Excel(name = "车牌类型")
    private String plateType;

    /** 车辆抓拍图片 */
    @Excel(name = "车辆抓拍图片")
    private String carImgUrl;

    /** 车牌照图片 */
    @Excel(name = "车牌照图片")
    private String plateNumUrl;

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
    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
    }
    public void setPlateNum(String plateNum) 
    {
        this.plateNum = plateNum;
    }

    public String getPlateNum() 
    {
        return plateNum;
    }
    public void setCapTime(Long capTime)
    {
        this.capTime = capTime;
    }

    public Long getCapTime()
    {
        return capTime;
    }
    public void setPlateType(String plateType) 
    {
        this.plateType = plateType;
    }

    public String getPlateType() 
    {
        return plateType;
    }
    public void setCarImgUrl(String carImgUrl) 
    {
        this.carImgUrl = carImgUrl;
    }

    public String getCarImgUrl() 
    {
        return carImgUrl;
    }
    public void setPlateNumUrl(String plateNumUrl) 
    {
        this.plateNumUrl = plateNumUrl;
    }

    public String getPlateNumUrl() 
    {
        return plateNumUrl;
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
            .append("deviceId", getDeviceId())
            .append("channelCode", getChannelCode())
            .append("recordId", getRecordId())
            .append("plateNum", getPlateNum())
            .append("capTime", getCapTime())
            .append("plateType", getPlateType())
            .append("carImgUrl", getCarImgUrl())
            .append("plateNumUrl", getPlateNumUrl())
            .append("dataType", getDataType())
            .toString();
    }
}
