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
 * @date 2022-07-08
 */
public class YjSynergy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 事件ID */
    @Excel(name = "事件ID")
    private Long eventId;

    /** 所属街道 */
    @Excel(name = "所属街道")
    private Long belongStreet;

    /** 街道名称 */
    @Excel(name = "街道名称")
    private String streetName;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 事件描述 */
    @Excel(name = "事件描述")
    private String incidentDescribe;

    /** 事件类型 */
    @Excel(name = "事件类型")
    private String incidentType;

    /** 回复查看 */
    @Excel(name = "回复查看")
    private Integer replyCheck;

    /** 查看详情 */
    @Excel(name = "查看详情")
    private String checkInfo;

    /** 事件大类型 */
    @Excel(name = "事件大类型")
    private Integer eventType;

    /** 报警人 */
    @Excel(name = "报警人")
    private String alarmName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String alarmPhone;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String treatmentResult;

    /** 主管单位 */
    @Excel(name = "主管单位")
    private String competentUnit;

    /** 警情地址 */
    @Excel(name = "警情地址")
    private String address;

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
    public void setEventId(Long eventId)
    {
        this.eventId = eventId;
    }

    public Long getEventId()
    {
        return eventId;
    }
    public void setBelongStreet(Long belongStreet)
    {
        this.belongStreet = belongStreet;
    }

    public Long getBelongStreet()
    {
        return belongStreet;
    }
    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public String getStreetName()
    {
        return streetName;
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
    public void setIncidentType(String incidentType)
    {
        this.incidentType = incidentType;
    }

    public String getIncidentType()
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
    public void setCheckInfo(String checkInfo)
    {
        this.checkInfo = checkInfo;
    }

    public String getCheckInfo()
    {
        return checkInfo;
    }
    public void setEventType(Integer eventType)
    {
        this.eventType = eventType;
    }

    public Integer getEventType()
    {
        return eventType;
    }
    public void setAlarmName(String alarmName)
    {
        this.alarmName = alarmName;
    }

    public String getAlarmName()
    {
        return alarmName;
    }
    public void setAlarmPhone(String alarmPhone)
    {
        this.alarmPhone = alarmPhone;
    }

    public String getAlarmPhone()
    {
        return alarmPhone;
    }
    public void setTreatmentResult(String treatmentResult)
    {
        this.treatmentResult = treatmentResult;
    }

    public String getTreatmentResult()
    {
        return treatmentResult;
    }
    public void setCompetentUnit(String competentUnit)
    {
        this.competentUnit = competentUnit;
    }

    public String getCompetentUnit()
    {
        return competentUnit;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
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
                .append("eventId", getEventId())
                .append("belongStreet", getBelongStreet())
                .append("streetName", getStreetName())
                .append("checkTime", getCheckTime())
                .append("incidentDescribe", getIncidentDescribe())
                .append("incidentType", getIncidentType())
                .append("replyCheck", getReplyCheck())
                .append("checkInfo", getCheckInfo())
                .append("eventType", getEventType())
                .append("alarmName", getAlarmName())
                .append("alarmPhone", getAlarmPhone())
                .append("treatmentResult", getTreatmentResult())
                .append("competentUnit", getCompetentUnit())
                .append("address", getAddress())
                .append("dataType", getDataType())
                .toString();
    }

}
