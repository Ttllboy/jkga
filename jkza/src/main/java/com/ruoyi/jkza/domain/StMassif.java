package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地块数量对象 st_massif
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
public class StMassif extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 塘汇街道 */
    @Excel(name = "塘汇街道")
    private Integer thStreet;

    /** 嘉北街道 */
    @Excel(name = "嘉北街道")
    private Integer jbStreet;

    /** 城南街道 */
    @Excel(name = "城南街道")
    private Integer cnStreet;

    /** 长水街道 */
    @Excel(name = "长水街道")
    private Integer csStreet;

    /** 安全使用率 */
    @Excel(name = "安全使用率")
    private Double safeUse;

    /** 年份 */
    @Excel(name = "年份")
    private Integer year;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setThStreet(Integer thStreet) 
    {
        this.thStreet = thStreet;
    }

    public Integer getThStreet() 
    {
        return thStreet;
    }
    public void setJbStreet(Integer jbStreet) 
    {
        this.jbStreet = jbStreet;
    }

    public Integer getJbStreet() 
    {
        return jbStreet;
    }
    public void setCnStreet(Integer cnStreet) 
    {
        this.cnStreet = cnStreet;
    }

    public Integer getCnStreet() 
    {
        return cnStreet;
    }
    public void setCsStreet(Integer csStreet) 
    {
        this.csStreet = csStreet;
    }

    public Integer getCsStreet() 
    {
        return csStreet;
    }
    public void setSafeUse(Double safeUse) 
    {
        this.safeUse = safeUse;
    }

    public Double getSafeUse() 
    {
        return safeUse;
    }
    public void setYear(Integer year) 
    {
        this.year = year;
    }

    public Integer getYear() 
    {
        return year;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("thStreet", getThStreet())
            .append("jbStreet", getJbStreet())
            .append("cnStreet", getCnStreet())
            .append("csStreet", getCsStreet())
            .append("safeUse", getSafeUse())
            .append("year", getYear())
            .toString();
    }
}
