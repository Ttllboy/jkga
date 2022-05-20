package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工人考勤对象 t_personinfo
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
public class TPersoninfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目信息编号 */
    @Excel(name = "项目信息编号")
    private String projectguid;

    /** 工人姓名 */
    @Excel(name = "工人姓名")
    private String personname;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 签入时间 */
    @Excel(name = "签入时间")
    private String checkoint;

    /** 签出时间 */
    @Excel(name = "签出时间")
    private String checkoutt;

    private String personphone;

    public String getPersonphone() {
        return personphone;
    }

    public void setPersonphone(String personphone) {
        this.personphone = personphone;
    }

    public void setProjectguid(String projectguid)
    {
        this.projectguid = projectguid;
    }

    public String getProjectguid() 
    {
        return projectguid;
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
    public void setCheckoint(String checkoint) 
    {
        this.checkoint = checkoint;
    }

    public String getCheckoint() 
    {
        return checkoint;
    }
    public void setCheckoutt(String checkoutt) 
    {
        this.checkoutt = checkoutt;
    }

    public String getCheckoutt() 
    {
        return checkoutt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectguid", getProjectguid())
            .append("personname", getPersonname())
            .append("idcard", getIdcard())
            .append("checkoint", getCheckoint())
            .append("checkoutt", getCheckoutt())
            .append("createTime", getCreateTime())
            .toString();
    }
}
