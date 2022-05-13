package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 避灾场所对象 yj_avoid_disaster
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
public class YjAvoidDisaster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 避灾场所名字 */
    @Excel(name = "避灾场所名字")
    private String placeName;

    /** 场所状态 */
    @Excel(name = "场所状态")
    private Integer placeType;

    /** 当前转入人数 */
    @Excel(name = "当前转入人数")
    private Long intoNum;

    /** 当前转出人数 */
    @Excel(name = "当前转出人数")
    private Long outNum;

    /** 当前场内人数 */
    @Excel(name = "当前场内人数")
    private Long innerNum;

    /** 还可容纳人数 */
    @Excel(name = "还可容纳人数")
    private Long remainNum;

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
    public void setPlaceName(String placeName) 
    {
        this.placeName = placeName;
    }

    public String getPlaceName() 
    {
        return placeName;
    }
    public void setPlaceType(Integer placeType) 
    {
        this.placeType = placeType;
    }

    public Integer getPlaceType() 
    {
        return placeType;
    }
    public void setIntoNum(Long intoNum) 
    {
        this.intoNum = intoNum;
    }

    public void setCoordinate(String coordinate)
    {
        this.coordinate = coordinate;
    }

    public String getCoordinate()
    {
        return coordinate;
    }

    public Long getIntoNum() 
    {
        return intoNum;
    }
    public void setOutNum(Long outNum) 
    {
        this.outNum = outNum;
    }

    public Long getOutNum() 
    {
        return outNum;
    }
    public void setInnerNum(Long innerNum) 
    {
        this.innerNum = innerNum;
    }

    public Long getInnerNum() 
    {
        return innerNum;
    }
    public void setRemainNum(Long remainNum) 
    {
        this.remainNum = remainNum;
    }

    public Long getRemainNum() 
    {
        return remainNum;
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
            .append("placeName", getPlaceName())
            .append("placeType", getPlaceType())
            .append("intoNum", getIntoNum())
            .append("outNum", getOutNum())
            .append("innerNum", getInnerNum())
            .append("remainNum", getRemainNum())
            .append("dataType", getDataType())
            .toString();
    }
}
