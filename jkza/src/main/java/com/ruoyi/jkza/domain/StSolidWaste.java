package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 固废点对象 st_solid_waste
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class StSolidWaste extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 固废点名称 */
    @Excel(name = "固废点名称")
    private String solidName;

    /** 固废点总容量 */
    @Excel(name = "固废点总容量")
    private Long capacity;

    /** 当前场内固废 */
    @Excel(name = "当前场内固废")
    private Long currentSoildWaste;

    /** 总处理固废 */
    @Excel(name = "总处理固废")
    private Long processSoildWaste;

    /** 坐标 */
    @Excel(name = "坐标")
    private String coordinate;

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
    public void setSolidName(String solidName) 
    {
        this.solidName = solidName;
    }

    public String getSolidName() 
    {
        return solidName;
    }
    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
    }
    public void setCurrentSoildWaste(Long currentSoildWaste)

    {
        this.currentSoildWaste = currentSoildWaste;
    }

    public void setCoordinate(String coordinate)
    {
        this.coordinate = coordinate;
    }

    public String getCoordinate()
    {
        return coordinate;
    }

    public Long getCurrentSoildWaste() 
    {
        return currentSoildWaste;
    }
    public void setProcessSoildWaste(Long processSoildWaste) 
    {
        this.processSoildWaste = processSoildWaste;
    }

    public Long getProcessSoildWaste() 
    {
        return processSoildWaste;
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
            .append("solidName", getSolidName())
            .append("capacity", getCapacity())
            .append("currentSoildWaste", getCurrentSoildWaste())
            .append("processSoildWaste", getProcessSoildWaste())
            .append("dataType", getDataType())
            .toString();
    }
}
