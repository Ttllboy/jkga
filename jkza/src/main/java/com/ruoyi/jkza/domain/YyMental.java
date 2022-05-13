package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 精神病人对象 yy_mental
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class YyMental extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 所属街道 */
    @Excel(name = "所属街道")
    private Long streetId;

    /** 检测日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 病人数量 */
    @Excel(name = "病人数量")
    private Long people;

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
    public void setStreetId(Long streetId) 
    {
        this.streetId = streetId;
    }

    public Long getStreetId() 
    {
        return streetId;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setPeople(Long people) 
    {
        this.people = people;
    }

    public Long getPeople() 
    {
        return people;
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
            .append("streetId", getStreetId())
            .append("checkTime", getCheckTime())
            .append("people", getPeople())
            .append("dataType", getDataType())
            .toString();
    }
}
