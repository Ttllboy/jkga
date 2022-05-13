package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 低洼点对象 yj_low_point
 *
 * @author ruoyi
 * @date 2022-04-18
 */
public class YjLowPoint extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 低洼点名称
     */
    @Excel(name = "低洼点名称")
    private String lowPointName;

    /**
     * 当前水位
     */
    @Excel(name = "当前水位")
    private Long nowWaterLevel;

    /**
     * 水位预警线
     */
    @Excel(name = "水位预警线")
    private Long waterLevelWarn;

    /**
     * 坐标
     */
    @Excel(name = "坐标")
    private String coordinate;

    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    private Integer dataType;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLowPointName(String lowPointName) {
        this.lowPointName = lowPointName;
    }

    public String getLowPointName() {
        return lowPointName;
    }

    public void setNowWaterLevel(Long nowWaterLevel) {
        this.nowWaterLevel = nowWaterLevel;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public Long getNowWaterLevel() {
        return nowWaterLevel;
    }

    public void setWaterLevelWarn(Long waterLevelWarn) {
        this.waterLevelWarn = waterLevelWarn;
    }

    public Long getWaterLevelWarn() {
        return waterLevelWarn;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getDataType() {
        return dataType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("lowPointName", getLowPointName())
                .append("nowWaterLevel", getNowWaterLevel())
                .append("waterLevelWarn", getWaterLevelWarn())
                .append("coordinate", getCoordinate())
                .append("dataType", getDataType())
                .toString();
    }
}
