package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 防疫详情对象 yy_epidemic_prevention
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class YyEpidemicPrevention extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 绿码人数 */
    @Excel(name = "绿码人数")
    private Long green;

    /** 黄码人数 */
    @Excel(name = "黄码人数")
    private Long yellow;

    /** 红码人数 */
    @Excel(name = "红码人数")
    private Long red;

    /** 测温异常人数 */
    @Excel(name = "测温异常人数")
    private Long temperatureAbnormal;

    /** 绿码人数比例 */
    @Excel(name = "绿码人数比例")
    private Double greenProportion;

    /** 黄码人数比例 */
    @Excel(name = "黄码人数比例")
    private Double yellowProportion;

    /** 红码人数比例 */
    @Excel(name = "红码人数比例")
    private Double redProportion;

    /** 测温异常人数比例 */
    @Excel(name = "测温异常人数比例")
    private Double temperatureProportion;

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
    public void setGreen(Long green) 
    {
        this.green = green;
    }

    public Long getGreen() 
    {
        return green;
    }
    public void setYellow(Long yellow) 
    {
        this.yellow = yellow;
    }

    public Long getYellow() 
    {
        return yellow;
    }
    public void setRed(Long red) 
    {
        this.red = red;
    }

    public Long getRed() 
    {
        return red;
    }
    public void setTemperatureAbnormal(Long temperatureAbnormal) 
    {
        this.temperatureAbnormal = temperatureAbnormal;
    }

    public Long getTemperatureAbnormal() 
    {
        return temperatureAbnormal;
    }
    public void setGreenProportion(Double greenProportion)
    {
        this.greenProportion = greenProportion;
    }

    public Double getGreenProportion()
    {
        return greenProportion;
    }
    public void setYellowProportion(Double yellowProportion)
    {
        this.yellowProportion = yellowProportion;
    }

    public Double getYellowProportion()
    {
        return yellowProportion;
    }
    public void setRedProportion(Double redProportion)
    {
        this.redProportion = redProportion;
    }

    public Double getRedProportion()
    {
        return redProportion;
    }
    public void setTemperatureProportion(Double temperatureProportion)
    {
        this.temperatureProportion = temperatureProportion;
    }

    public Double getTemperatureProportion()
    {
        return temperatureProportion;
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
            .append("green", getGreen())
            .append("yellow", getYellow())
            .append("red", getRed())
            .append("temperatureAbnormal", getTemperatureAbnormal())
            .append("greenProportion", getGreenProportion())
            .append("yellowProportion", getYellowProportion())
            .append("redProportion", getRedProportion())
            .append("temperatureProportion", getTemperatureProportion())
            .append("dataType", getDataType())
            .toString();
    }
}
