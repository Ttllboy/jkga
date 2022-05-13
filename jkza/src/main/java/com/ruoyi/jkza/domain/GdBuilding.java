package com.ruoyi.jkza.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工地信息对象 gd_building
 *
 * @author ruoyi
 * @date 2022-04-22
 */
public class GdBuilding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 工地名称 */
    @Excel(name = "工地名称")
    private String buildingSiteName;

    /** 街道名称 */
    @Excel(name = "街道名称")
    private String streetName;

    /** 工地编号 */
    @Excel(name = "工地编号")
    private String buildingSiteNum;

    /** 工程编号 */
    @Excel(name = "工程编号")
    private String projectNum;

    /** 项目信息编号 */
    @Excel(name = "项目信息编号")
    private String projectInfoNum;

    /** 施工许可证 */
    @Excel(name = "施工许可证")
    private String buildingLicense;

    /** 工地实有人口 */
    @Excel(name = "工地实有人口")
    private Long workerNum;

    /** 工地地址 */
    @Excel(name = "工地地址")
    private String buildingSiteLocation;

    /** 面积 */
    @Excel(name = "面积")
    private BigDecimal space;

    /** 造价 */
    @Excel(name = "造价")
    private BigDecimal price;

    /** 所属街道 */
    @Excel(name = "所属街道")
    private Long streetId;

    /** 坐标 */
    @Excel(name = "坐标")
    private String coordinate;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private Integer dataType;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBuildingSiteName(String buildingSiteName)
    {
        this.buildingSiteName = buildingSiteName;
    }

    public String getBuildingSiteName()
    {
        return buildingSiteName;
    }
    public void setBuildingSiteNum(String buildingSiteNum)
    {
        this.buildingSiteNum = buildingSiteNum;
    }

    public String getBuildingSiteNum()
    {
        return buildingSiteNum;
    }
    public void setProjectNum(String projectNum)
    {
        this.projectNum = projectNum;
    }

    public String getProjectNum()
    {
        return projectNum;
    }
    public void setProjectInfoNum(String projectInfoNum)
    {
        this.projectInfoNum = projectInfoNum;
    }

    public String getProjectInfoNum()
    {
        return projectInfoNum;
    }
    public void setBuildingLicense(String buildingLicense)
    {
        this.buildingLicense = buildingLicense;
    }

    public String getBuildingLicense()
    {
        return buildingLicense;
    }
    public void setWorkerNum(Long workerNum)
    {
        this.workerNum = workerNum;
    }

    public Long getWorkerNum()
    {
        return workerNum;
    }
    public void setBuildingSiteLocation(String buildingSiteLocation)
    {
        this.buildingSiteLocation = buildingSiteLocation;
    }

    public String getBuildingSiteLocation()
    {
        return buildingSiteLocation;
    }
    public void setSpace(BigDecimal space)
    {
        this.space = space;
    }

    public BigDecimal getSpace()
    {
        return space;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setStreetId(Long streetId)
    {
        this.streetId = streetId;
    }

    public Long getStreetId()
    {
        return streetId;
    }
    public void setCoordinate(String coordinate)
    {
        this.coordinate = coordinate;
    }

    public String getCoordinate()
    {
        return coordinate;
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
                .append("buildingSiteName", getBuildingSiteName())
                .append("buildingSiteNum", getBuildingSiteNum())
                .append("projectNum", getProjectNum())
                .append("projectInfoNum", getProjectInfoNum())
                .append("buildingLicense", getBuildingLicense())
                .append("workerNum", getWorkerNum())
                .append("buildingSiteLocation", getBuildingSiteLocation())
                .append("space", getSpace())
                .append("price", getPrice())
                .append("streetId", getStreetId())
                .append("coordinate", getCoordinate())
                .append("dataType", getDataType())
                .toString();
    }
}
