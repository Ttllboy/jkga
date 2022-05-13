package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 整改通知对象 gd_synergy
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public class GdSynergy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 检测日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检测日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;

    /** 街道ID */
    @Excel(name = "街道ID")
    private Long streetId;

    /** 工地ID */
    @Excel(name = "工地ID")
    private Long buildingId;

    /** 检查部位 */
    @Excel(name = "检查部位")
    private String checkPosition;

    /** 通知书类型 */
    @Excel(name = "通知书类型")
    private Integer notificationType;

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

    public String getCheckPosition() {
        return checkPosition;
    }

    public void setCheckPosition(String checkPosition) {
        this.checkPosition = checkPosition;
    }

    public Long getId()
    {
        return id;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setStreetId(Long streetId) 
    {
        this.streetId = streetId;
    }

    public Long getStreetId() 
    {
        return streetId;
    }
    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }
    public void setNotificationType(Integer notificationType) 
    {
        this.notificationType = notificationType;
    }

    public Integer getNotificationType() 
    {
        return notificationType;
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
            .append("projectName", getProjectName())
            .append("checkTime", getCheckTime())
            .append("streetId", getStreetId())
            .append("buildingId", getBuildingId())
            .append("notificationType", getNotificationType())
            .append("replyCheck", getReplyCheck())
            .append("replyInfo", getReplyInfo())
            .append("dataType", getDataType())
            .toString();
    }
}
