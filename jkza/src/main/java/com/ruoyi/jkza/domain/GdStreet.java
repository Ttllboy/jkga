package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 街道信息对象 gd_street
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public class GdStreet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 街道名称 */
    @Excel(name = "街道名称")
    private String streetName;

    /** 数据类型(0:接口数据 1:手动数据) */
    @Excel(name = "数据类型(0:接口数据 1:手动数据)")
    private Integer dataType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStreetName(String streetName) 
    {
        this.streetName = streetName;
    }

    public String getStreetName() 
    {
        return streetName;
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
            .append("streetName", getStreetName())
            .append("dataType", getDataType())
            .toString();
    }
}
