package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人脸抓怕对象 mq_rlzp
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
public class MqRlzp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceId;

    /** 记录编码 */
    @Excel(name = "记录编码")
    private String recordId;

    /** 抓拍时间 */
    @Excel(name = "抓拍时间")
    private Long capTime;

    /** 场景大图 */
    @Excel(name = "场景大图")
    private String imgUrl;

    /** 抓拍人脸 */
    @Excel(name = "抓拍人脸")
    private String faceImgUrl;

    /** 人脸类型 */
    @Excel(name = "人脸类型")
    private Integer faceImgType;

    /** 数据来源类型 */
    @Excel(name = "数据来源类型")
    private Integer dataSource;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setChannelId(String channelId) 
    {
        this.channelId = channelId;
    }

    public String getChannelId() 
    {
        return channelId;
    }
    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }
    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
    }
    public void setCapTime(Long capTime)
    {
        this.capTime = capTime;
    }

    public Long getCapTime()
    {
        return capTime;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setFaceImgUrl(String faceImgUrl) 
    {
        this.faceImgUrl = faceImgUrl;
    }

    public String getFaceImgUrl() 
    {
        return faceImgUrl;
    }
    public void setFaceImgType(Integer faceImgType) 
    {
        this.faceImgType = faceImgType;
    }

    public Integer getFaceImgType() 
    {
        return faceImgType;
    }
    public void setDataSource(Integer dataSource) 
    {
        this.dataSource = dataSource;
    }

    public Integer getDataSource() 
    {
        return dataSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelId", getChannelId())
            .append("deviceId", getDeviceId())
            .append("recordId", getRecordId())
            .append("capTime", getCapTime())
            .append("imgUrl", getImgUrl())
            .append("faceImgUrl", getFaceImgUrl())
            .append("faceImgType", getFaceImgType())
            .append("dataSource", getDataSource())
            .toString();
    }
}
