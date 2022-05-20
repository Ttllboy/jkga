package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工人信息对象 v_ps_workerbase
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
public class VPsWorkerbase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 姓名 */
    @Excel(name = "姓名")
    private String personname;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 照片路径 */
    @Excel(name = "照片路径")
    private String imgurl;

    /** 人员类型 */
    @Excel(name = "人员类型")
    private String persontypes;

    /** 项目信息编号 */
    @Excel(name = "项目信息编号")
    private String projectguid;

    /** 工地名称 */
    @Excel(name = "工地名称")
    private String projectname;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String jiguan;

    /** 户籍所在地 */
    @Excel(name = "户籍所在地")
    private String address;

    /** 民族 */
    @Excel(name = "民族")
    private String personnation;

    /** 电话 */
    @Excel(name = "电话")
    private String personphone;

    /** 照片 */
    @Excel(name = "照片")
    private String jpgbuffer;

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
    public void setImgurl(String imgurl) 
    {
        this.imgurl = imgurl;
    }

    public String getImgurl() 
    {
        return imgurl;
    }
    public void setPersontypes(String persontypes) 
    {
        this.persontypes = persontypes;
    }

    public String getPersontypes() 
    {
        return persontypes;
    }
    public void setProjectguid(String projectguid) 
    {
        this.projectguid = projectguid;
    }

    public String getProjectguid() 
    {
        return projectguid;
    }
    public void setProjectname(String projectname) 
    {
        this.projectname = projectname;
    }

    public String getProjectname() 
    {
        return projectname;
    }
    public void setJiguan(String jiguan) 
    {
        this.jiguan = jiguan;
    }

    public String getJiguan() 
    {
        return jiguan;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPersonnation(String personnation) 
    {
        this.personnation = personnation;
    }

    public String getPersonnation() 
    {
        return personnation;
    }
    public void setPersonphone(String personphone) 
    {
        this.personphone = personphone;
    }

    public String getPersonphone() 
    {
        return personphone;
    }
    public void setJpgbuffer(String jpgbuffer) 
    {
        this.jpgbuffer = jpgbuffer;
    }

    public String getJpgbuffer() 
    {
        return jpgbuffer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("personname", getPersonname())
            .append("idcard", getIdcard())
            .append("imgurl", getImgurl())
            .append("persontypes", getPersontypes())
            .append("projectguid", getProjectguid())
            .append("projectname", getProjectname())
            .append("jiguan", getJiguan())
            .append("address", getAddress())
            .append("personnation", getPersonnation())
            .append("personphone", getPersonphone())
            .append("jpgbuffer", getJpgbuffer())
            .append("createTime", getCreateTime())
            .toString();
    }
}
