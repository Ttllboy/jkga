package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 事件处理对象 st_incident_process
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class StIncidentProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 事件名称 */
    @Excel(name = "事件名称")
    private String incidentName;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 相关地点 */
    @Excel(name = "相关地点")
    private String place;

    /** 事件类型 */
    @Excel(name = "事件类型")
    private Integer incidentType;

    /** 回复查看 */
    @Excel(name = "回复查看")
    private Integer replyCheck;

    /** 回复信息 */
    @Excel(name = "回复信息")
    private String replyInfo;

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
    public void setIncidentName(String incidentName) 
    {
        this.incidentName = incidentName;
    }

    public String getIncidentName() 
    {
        return incidentName;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getPlace() 
    {
        return place;
    }
    public void setIncidentType(Integer incidentType) 
    {
        this.incidentType = incidentType;
    }

    public Integer getIncidentType() 
    {
        return incidentType;
    }
    public void setReplyCheck(Integer replyCheck) 
    {
        this.replyCheck = replyCheck;
    }

    public Integer getReplyCheck() 
    {
        return replyCheck;
    }
    public void setReplyInfo(String replyInfo) 
    {
        this.replyInfo = replyInfo;
    }

    public String getReplyInfo() 
    {
        return replyInfo;
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
            .append("incidentName", getIncidentName())
            .append("checkTime", getCheckTime())
            .append("place", getPlace())
            .append("incidentType", getIncidentType())
            .append("replyCheck", getReplyCheck())
            .append("replyInfo", getReplyInfo())
            .append("dataType", getDataType())
            .toString();
    }
}
