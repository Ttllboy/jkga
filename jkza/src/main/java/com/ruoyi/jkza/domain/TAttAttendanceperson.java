package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 管理人员信息对象 t_att_attendanceperson
 * 
 * @author ruoyi
 * @date 2022-05-12
 */
public class TAttAttendanceperson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目信息编号 */
    @Excel(name = "项目信息编号")
    private String projectguid;

    /** 人员信息编号 */
    @Excel(name = "人员信息编号")
    private String personguid;

    /** 姓名 */
    @Excel(name = "姓名")
    private String personname;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 岗位 */
    @Excel(name = "岗位")
    private String postname;

    public void setProjectguid(String projectguid) 
    {
        this.projectguid = projectguid;
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
    public void setPersonname(String personname) 
    {
        this.personname = personname;
    }

    public String getPersonname() 
    {
        return personname;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setPostname(String postname) 
    {
        this.postname = postname;
    }

    public String getPostname() 
    {
        return postname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectguid", getProjectguid())
            .append("personguid", getPersonguid())
            .append("personname", getPersonname())
            .append("idcard", getIdcard())
            .append("postname", getPostname())
            .append("createTime", getCreateTime())
            .toString();
    }
}
