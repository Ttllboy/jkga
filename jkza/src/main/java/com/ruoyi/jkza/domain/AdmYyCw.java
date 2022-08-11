package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医院对应对象 adm_yy_cw
 * 
 * @author ruoyi
 * @date 2022-07-04
 */
public class AdmYyCw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 在线状态 */
    @Excel(name = "在线状态")
    private Integer onlineType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setOnlineType(Integer onlineType) 
    {
        this.onlineType = onlineType;
    }

    public Integer getOnlineType() 
    {
        return onlineType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelCode", getChannelCode())
            .append("channelName", getChannelName())
            .append("deviceCode", getDeviceCode())
            .append("deviceName", getDeviceName())
            .append("onlineType", getOnlineType())
            .toString();
    }
}
