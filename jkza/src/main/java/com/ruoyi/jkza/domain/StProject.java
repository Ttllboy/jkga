package com.ruoyi.jkza.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目基本信息对象 st_project
 * 
 * @author ruoyi
 * @date 2022-05-09
 */
public class StProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信息编号 */
    @Excel(name = "信息编号")
    private String guid;

    /** 工地名称 */
    @Excel(name = "工地名称")
    private String projectname;

    /** 施工许可证 */
    @Excel(name = "施工许可证")
    private String segmentid;

    /** 工地地址 */
    @Excel(name = "工地地址")
    private String projectaddress;

    /** 所属街道 */
    @Excel(name = "所属街道")
    private String projectarea;

    /** 面积 */
    @Excel(name = "面积")
    private BigDecimal buildarea;

    /** 造价 */
    @Excel(name = "造价")
    private BigDecimal projectprice;

    /** 坐标 */
    @Excel(name = "坐标")
    private String coordinate;

    public void setGuid(String guid) 
    {
        this.guid = guid;
    }

    public String getGuid() 
    {
        return guid;
    }
    public void setProjectname(String projectname) 
    {
        this.projectname = projectname;
    }

    public String getProjectname() 
    {
        return projectname;
    }
    public void setSegmentid(String segmentid) 
    {
        this.segmentid = segmentid;
    }

    public String getSegmentid() 
    {
        return segmentid;
    }
    public void setProjectaddress(String projectaddress) 
    {
        this.projectaddress = projectaddress;
    }

    public String getProjectaddress() 
    {
        return projectaddress;
    }
    public void setProjectarea(String projectarea) 
    {
        this.projectarea = projectarea;
    }

    public String getProjectarea() 
    {
        return projectarea;
    }
    public void setBuildarea(BigDecimal buildarea) 
    {
        this.buildarea = buildarea;
    }

    public BigDecimal getBuildarea() 
    {
        return buildarea;
    }
    public void setProjectprice(BigDecimal projectprice) 
    {
        this.projectprice = projectprice;
    }

    public BigDecimal getProjectprice() 
    {
        return projectprice;
    }
    public void setCoordinate(String coordinate) 
    {
        this.coordinate = coordinate;
    }

    public String getCoordinate() 
    {
        return coordinate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("guid", getGuid())
            .append("projectname", getProjectname())
            .append("segmentid", getSegmentid())
            .append("projectaddress", getProjectaddress())
            .append("projectarea", getProjectarea())
            .append("buildarea", getBuildarea())
            .append("projectprice", getProjectprice())
            .append("coordinate", getCoordinate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
