package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预警信息对象 yj_warn
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
public class YjWarn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 预警级别 */
    @Excel(name = "预警级别")
    private Integer warnLevel;

    /** 预警时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预警时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warnTime;

    /** 预警详情 */
    @Excel(name = "预警详情")
    private String warnInfo;

    /** 预警类型 */
    @Excel(name = "预警类型")
    private Integer warnType;

    /** 事件状态 */
    @Excel(name = "事件状态")
    private Integer incidentType;

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
    public void setWarnLevel(Integer warnLevel) 
    {
        this.warnLevel = warnLevel;
    }

    public Integer getWarnLevel() 
    {
        return warnLevel;
    }
    public void setWarnTime(Date warnTime) 
    {
        this.warnTime = warnTime;
    }

    public Date getWarnTime() 
    {
        return warnTime;
    }
    public void setWarnInfo(String warnInfo) 
    {
        this.warnInfo = warnInfo;
    }

    public String getWarnInfo() 
    {
        return warnInfo;
    }
    public void setWarnType(Integer warnType) 
    {
        this.warnType = warnType;
    }

    public Integer getWarnType() 
    {
        return warnType;
    }
    public void setIncidentType(Integer incidentType) 
    {
        this.incidentType = incidentType;
    }

    public Integer getIncidentType() 
    {
        return incidentType;
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
            .append("warnLevel", getWarnLevel())
            .append("warnTime", getWarnTime())
            .append("warnInfo", getWarnInfo())
            .append("warnType", getWarnType())
            .append("incidentType", getIncidentType())
            .append("dataType", getDataType())
            .toString();
    }
}
