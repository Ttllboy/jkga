package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预警信息对象 st_warn
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class StWarn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 预警名称 */
    @Excel(name = "预警名称")
    private String warnName;

    /** 预警时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预警时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warnTime;

    /** 预警地点 */
    @Excel(name = "预警地点")
    private String warnPlace;

    /** 预警类型 */
    @Excel(name = "预警类型")
    private Integer warnType;

    /** 操作 */
    @Excel(name = "操作")
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
    public void setWarnName(String warnName) 
    {
        this.warnName = warnName;
    }

    public String getWarnName() 
    {
        return warnName;
    }
    public void setWarnTime(Date warnTime) 
    {
        this.warnTime = warnTime;
    }

    public Date getWarnTime() 
    {
        return warnTime;
    }
    public void setWarnPlace(String warnPlace) 
    {
        this.warnPlace = warnPlace;
    }

    public String getWarnPlace() 
    {
        return warnPlace;
    }
    public void setWarnType(Integer warnType) 
    {
        this.warnType = warnType;
    }

    public Integer getWarnType() 
    {
        return warnType;
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
            .append("warnName", getWarnName())
            .append("warnTime", getWarnTime())
            .append("warnPlace", getWarnPlace())
            .append("warnType", getWarnType())
            .append("replyCheck", getReplyCheck())
            .append("replyInfo", getReplyInfo())
            .append("dataType", getDataType())
            .toString();
    }
}
