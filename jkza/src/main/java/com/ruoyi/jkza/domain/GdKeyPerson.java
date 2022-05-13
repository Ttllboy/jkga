package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 重点人员预警对象 gd_key_person
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public class GdKeyPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 预警画面 */
    @Excel(name = "预警画面")
    private String warnImg;

    /** 预警时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预警时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date warnTime;

    /** 预警地点 */
    @Excel(name = "预警地点")
    private String warnSite;

    /** 预警类型 */
    @Excel(name = "预警类型")
    private String warnType;

    /** 事件状态 */
    @Excel(name = "事件状态")
    private Integer incidentType;

    /** 所属工地 */
    @Excel(name = "所属工地")
    private Long buildingId;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private Integer dataType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getId()
    {
        return id;
    }
    public void setWarnImg(String warnImg) 
    {
        this.warnImg = warnImg;
    }

    public String getWarnImg() 
    {
        return warnImg;
    }
    public void setWarnTime(Date warnTime) 
    {
        this.warnTime = warnTime;
    }

    public Date getWarnTime() 
    {
        return warnTime;
    }
    public void setWarnSite(String warnSite) 
    {
        this.warnSite = warnSite;
    }

    public String getWarnSite() 
    {
        return warnSite;
    }
    public void setWarnType(String warnType) 
    {
        this.warnType = warnType;
    }

    public String getWarnType() 
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
            .append("warnImg", getWarnImg())
            .append("warnTime", getWarnTime())
            .append("warnSite", getWarnSite())
            .append("warnType", getWarnType())
            .append("incidentType", getIncidentType())
            .append("dataType", getDataType())
            .toString();
    }
}
