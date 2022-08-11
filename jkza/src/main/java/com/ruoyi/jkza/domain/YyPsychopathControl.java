package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病人管控对象 yy_psychopath_control
 * 
 * @author ruoyi
 * @date 2022-07-15
 */
public class YyPsychopathControl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 所属街道 */
    @Excel(name = "所属街道")
    private Long streetId;

    /** 病人数 */
    @Excel(name = "病人数")
    private Long patientCount;

    /** 新增病人数 */
    @Excel(name = "新增病人数")
    private Long addPatientCount;

    /** 年份 */
    @Excel(name = "年份")
    private Long year;

    /** 月份 */
    @Excel(name = "月份")
    private Long month;

    /** 街道名称 */
    @Excel(name = "街道名称")
    private String streetName;

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
    public void setPatientCount(Long patientCount) 
    {
        this.patientCount = patientCount;
    }

    public Long getPatientCount() 
    {
        return patientCount;
    }
    public void setAddPatientCount(Long addPatientCount) 
    {
        this.addPatientCount = addPatientCount;
    }

    public Long getAddPatientCount() 
    {
        return addPatientCount;
    }
    public void setYear(Long year) 
    {
        this.year = year;
    }

    public Long getYear() 
    {
        return year;
    }
    public void setMonth(Long month) 
    {
        this.month = month;
    }

    public Long getMonth() 
    {
        return month;
    }
    public void setStreetName(String streetName) 
    {
        this.streetName = streetName;
    }

    public String getStreetName() 
    {
        return streetName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("streetId", getStreetId())
            .append("patientCount", getPatientCount())
            .append("addPatientCount", getAddPatientCount())
            .append("year", getYear())
            .append("month", getMonth())
            .append("streetName", getStreetName())
            .toString();
    }
}
