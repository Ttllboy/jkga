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
 * @date 2022-06-26
 */
public class GdSynergy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目信息编号 */
    @Excel(name = "项目信息编号")
    private String projectguid;

    /** 整改编号 */
    @Excel(name = "整改编号")
    private String noticenum;

    /** 检查部分 */
    @Excel(name = "检查部分")
    private String checkposition;

    /** 通知书类型 */
    @Excel(name = "通知书类型")
    private String noticetype;

    /** 检查日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkdatet;

    /** 是否回复 */
    @Excel(name = "是否回复")
    private String isreply;

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
    public void setProjectguid(String projectguid) 
    {
        this.projectguid = projectguid;
    }

    public String getProjectguid() 
    {
        return projectguid;
    }
    public void setNoticenum(String noticenum) 
    {
        this.noticenum = noticenum;
    }

    public String getNoticenum() 
    {
        return noticenum;
    }
    public void setCheckposition(String checkposition) 
    {
        this.checkposition = checkposition;
    }

    public String getCheckposition() 
    {
        return checkposition;
    }
    public void setNoticetype(String noticetype) 
    {
        this.noticetype = noticetype;
    }

    public String getNoticetype() 
    {
        return noticetype;
    }
    public void setCheckdatet(Date checkdatet) 
    {
        this.checkdatet = checkdatet;
    }

    public Date getCheckdatet() 
    {
        return checkdatet;
    }
    public void setIsreply(String isreply) 
    {
        this.isreply = isreply;
    }

    public String getIsreply() 
    {
        return isreply;
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
            .append("projectguid", getProjectguid())
            .append("noticenum", getNoticenum())
            .append("checkposition", getCheckposition())
            .append("noticetype", getNoticetype())
            .append("checkdatet", getCheckdatet())
            .append("isreply", getIsreply())
            .append("createTime", getCreateTime())
            .append("dataType", getDataType())
            .toString();
    }
}
