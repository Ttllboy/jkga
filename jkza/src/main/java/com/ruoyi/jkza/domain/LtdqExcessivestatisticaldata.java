package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 扬尘数据对象 ltdq_excessivestatisticaldata
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
public class LtdqExcessivestatisticaldata extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目信息编号 */
    @Excel(name = "项目信息编号")
    private String projectguid;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String devicesn;

    /** 统计时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdatet;

    /** PM2.5 */
    @Excel(name = "PM2.5")
    private Double pm25;

    /** PM10 */
    @Excel(name = "PM10")
    private Double pm10;

    /** 温度 */
    @Excel(name = "温度")
    private Double pd04;

    /** 湿度 */
    @Excel(name = "湿度")
    private Double pd05;

    /** 噪音 */
    @Excel(name = "噪音")
    private Double pd09;

    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectguid(String projectguid) 
    {
        this.projectguid = projectguid;
    }

    public String getProjectguid() 
    {
        return projectguid;
    }
    public void setDevicesn(String devicesn) 
    {
        this.devicesn = devicesn;
    }

    public String getDevicesn() 
    {
        return devicesn;
    }
    public void setCreatedatet(Date createdatet) 
    {
        this.createdatet = createdatet;
    }

    public Date getCreatedatet() 
    {
        return createdatet;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getPd04() {
        return pd04;
    }

    public void setPd04(Double pd04) {
        this.pd04 = pd04;
    }

    public Double getPd05() {
        return pd05;
    }

    public void setPd05(Double pd05) {
        this.pd05 = pd05;
    }

    public Double getPd09() {
        return pd09;
    }

    public void setPd09(Double pd09) {
        this.pd09 = pd09;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectguid", getProjectguid())
            .append("devicesn", getDevicesn())
            .append("createdatet", getCreatedatet())
            .append("pm25", getPm25())
            .append("pm10", getPm10())
            .append("pd04", getPd04())
            .append("pd05", getPd05())
            .append("pd09", getPd09())
            .append("createTime", getCreateTime())
            .toString();
    }
}
