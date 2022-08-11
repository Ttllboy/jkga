package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医院信息对象 yy_yy
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class YyYy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String hosName;

    /** 医院床位数 */
    @Excel(name = "医院床位数")
    private Long bed;

    /** 医院停车数 */
    @Excel(name = "医院停车数")
    private Long car;

    /** 安保人员数 */
    @Excel(name = "安保人员数")
    private Long securityStaff;

    /** 在岗安保数 */
    @Excel(name = "在岗安保数")
    private Long securityStaffOnline;

    /** 坐标 */
    @Excel(name = "坐标")
    private String coordinate;

    /** 安保车辆数 */
    @Excel(name = "安保车辆数")
    private Long securityCar;

    /** 在岗安保车辆数 */
    @Excel(name = "在岗安保车辆数")
    private Long securityCarOnline;

    /** 消控室 */
    @Excel(name = "消控室")
    private Long disappearRoom;

    /** 危险源 */
    @Excel(name = "危险源")
    private Long dangerSource;

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
    public void setHosName(String hosName) 
    {
        this.hosName = hosName;
    }

    public String getHosName() 
    {
        return hosName;
    }
    public void setBed(Long bed) 
    {
        this.bed = bed;
    }

    public Long getSecurityCar() {
        return securityCar;
    }

    public void setSecurityCar(Long securityCar) {
        this.securityCar = securityCar;
    }

    public Long getDisappearRoom() {
        return disappearRoom;
    }

    public void setDisappearRoom(Long disappearRoom) {
        this.disappearRoom = disappearRoom;
    }

    public Long getDangerSource() {
        return dangerSource;
    }

    public void setDangerSource(Long dangerSource) {
        this.dangerSource = dangerSource;
    }

    public Long getSecurityCarOnline() {
        return securityCarOnline;
    }

    public void setSecurityCarOnline(Long securityCarOnline) {
        this.securityCarOnline = securityCarOnline;
    }

    public void setCoordinate(String coordinate)
    {
        this.coordinate = coordinate;
    }

    public String getCoordinate()
    {
        return coordinate;
    }

    public Long getBed() 
    {
        return bed;
    }
    public void setCar(Long car) 
    {
        this.car = car;
    }

    public Long getCar() 
    {
        return car;
    }
    public void setSecurityStaff(Long securityStaff) 
    {
        this.securityStaff = securityStaff;
    }

    public Long getSecurityStaff() 
    {
        return securityStaff;
    }
    public void setSecurityStaffOnline(Long securityStaffOnline) 
    {
        this.securityStaffOnline = securityStaffOnline;
    }

    public Long getSecurityStaffOnline() 
    {
        return securityStaffOnline;
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
            .append("hosName", getHosName())
            .append("bed", getBed())
            .append("car", getCar())
            .append("securityStaff", getSecurityStaff())
            .append("securityStaffOnline", getSecurityStaffOnline())
            .append("dataType", getDataType())
            .toString();
    }
}
