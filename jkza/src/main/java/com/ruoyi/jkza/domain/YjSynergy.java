package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 多跨协同对象 yj_synergy
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
public class YjSynergy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 所属街道 */
    @Excel(name = "所属街道")
    private Long belongStreet;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 事件描述 */
    @Excel(name = "事件描述")
    private String incidentDescribe;

    /** 事件类型 */
    @Excel(name = "事件类型")
    private Integer incidentType;

    /** 查看详情 */
    @Excel(name = "查看详情")
    private String checkInfo;

    /** 回复查看 */
    @Excel(name = "回复查看")
    private Integer replyCheck;

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
    public void setBelongStreet(Long belongStreet) 
    {
        this.belongStreet = belongStreet;
    }

    public Long getBelongStreet() 
    {
        return belongStreet;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setIncidentDescribe(String incidentDescribe) 
    {
        this.incidentDescribe = incidentDescribe;
    }

    public String getIncidentDescribe() 
    {
        return incidentDescribe;
    }
    public void setIncidentType(Integer incidentType) 
    {
        this.incidentType = incidentType;
    }

    public Integer getIncidentType() 
    {
        return incidentType;
    }
    public void setCheckInfo(String checkInfo) 
    {
        this.checkInfo = checkInfo;
    }

    public String getCheckInfo() 
    {
        return checkInfo;
    }
    public void setReplyCheck(Integer replyCheck) 
    {
        this.replyCheck = replyCheck;
    }

    public Integer getReplyCheck() 
    {
        return replyCheck;
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
            .append("belongStreet", getBelongStreet())
            .append("checkTime", getCheckTime())
            .append("incidentDescribe", getIncidentDescribe())
            .append("incidentType", getIncidentType())
            .append("checkInfo", getCheckInfo())
            .append("replyCheck", getReplyCheck())
            .append("dataType", getDataType())
            .toString();
    }
}
