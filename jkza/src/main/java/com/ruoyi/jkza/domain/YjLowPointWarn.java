package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 低洼点预警对象 yj_low_point_warn
 * 
 * @author ruoyi
 * @date 2022-06-24
 */
public class YjLowPointWarn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 报警唯一ID */
    @Excel(name = "报警唯一ID")
    private String alarmCode;

    /** 报警图片 */
    @Excel(name = "报警图片")
    private String alarmPicture;

    /** 报警类型 */
    @Excel(name = "报警类型")
    private Long alarmType;

    /** 报警时间 */
    @Excel(name = "报警时间")
    private String alarmTime;

    /** 组织编码 */
    @Excel(name = "组织编码")
    private String orgCode;

    /** 组织名称 */
    @Excel(name = "组织名称")
    private String orgName;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 预警状态 */
    @Excel(name = "预警状态")
    private Long alarmStat;

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
    public void setAlarmCode(String alarmCode) 
    {
        this.alarmCode = alarmCode;
    }

    public String getAlarmCode() 
    {
        return alarmCode;
    }
    public void setAlarmPicture(String alarmPicture) 
    {
        this.alarmPicture = alarmPicture;
    }

    public String getAlarmPicture() 
    {
        return alarmPicture;
    }
    public void setAlarmType(Long alarmType) 
    {
        this.alarmType = alarmType;
    }

    public Long getAlarmType() 
    {
        return alarmType;
    }
    public void setAlarmTime(String alarmTime) 
    {
        this.alarmTime = alarmTime;
    }

    public String getAlarmTime() 
    {
        return alarmTime;
    }
    public void setOrgCode(String orgCode) 
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode() 
    {
        return orgCode;
    }
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }

    public String getOrgName() 
    {
        return orgName;
    }
    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }
    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setAlarmStat(Long alarmStat) 
    {
        this.alarmStat = alarmStat;
    }

    public Long getAlarmStat() 
    {
        return alarmStat;
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
            .append("alarmCode", getAlarmCode())
            .append("alarmPicture", getAlarmPicture())
            .append("alarmType", getAlarmType())
            .append("alarmTime", getAlarmTime())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("deviceCode", getDeviceCode())
            .append("deviceName", getDeviceName())
            .append("channelCode", getChannelCode())
            .append("channelName", getChannelName())
            .append("alarmStat", getAlarmStat())
            .append("dataType", getDataType())
            .toString();
    }
}
