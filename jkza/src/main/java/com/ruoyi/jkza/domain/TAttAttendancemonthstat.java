package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 管理人员考勤数据对象 t_att_attendancemonthstat
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
public class TAttAttendancemonthstat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目信息编号 */
    @Excel(name = "项目信息编号")
    private String projectguid;

    /** 人员信息编号 */
    @Excel(name = "人员信息编号")
    private String personguid;

    //岗位名称
    private String postName;

    /** 年度 */
    @Excel(name = "年度")
    private Long year;

    /** 月份 */
    @Excel(name = "月份")
    private Long month;

    /** 到岗次数 */
    @Excel(name = "到岗次数")
    private Long degree;

    public void setProjectguid(String projectguid) 
    {
        this.projectguid = projectguid;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getProjectguid()
    {
        return projectguid;
    }
    public void setPersonguid(String personguid) 
    {
        this.personguid = personguid;
    }

    public String getPersonguid() 
    {
        return personguid;
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
    public void setDegree(Long degree) 
    {
        this.degree = degree;
    }

    public Long getDegree() 
    {
        return degree;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectguid", getProjectguid())
            .append("personguid", getPersonguid())
            .append("year", getYear())
            .append("month", getMonth())
            .append("degree", getDegree())
            .append("createTime", getCreateTime())
            .toString();
    }
}
