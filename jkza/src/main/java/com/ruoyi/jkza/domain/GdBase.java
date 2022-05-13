package com.ruoyi.jkza.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础数据对象 gd_base
 * 
 * @author ruoyi
 * @date 2022-04-11
 */

public class GdBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 接入工地数 */
    @Excel(name = "接入工地数")
    private Long buildingSite;

    /** 工地总造价 */
    @Excel(name = "工地总造价")
    private BigDecimal buildingSitePrice;

    /** 工地人员总数 */
    @Excel(name = "工地人员总数")
    private Long buildingSiteWorker;

    /** 在岗人员总数 */
    @Excel(name = "在岗人员总数")
    private Long buildingSiteWorkerDuty;

    /** 起重设备数 */
    @Excel(name = "起重设备数")
    private Long liftingEquipment;

    /** 起重设备备案数 */
    @Excel(name = "起重设备备案数")
    private Long liftingEquipmentFiling;

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
    public void setBuildingSite(Long buildingSite) 
    {
        this.buildingSite = buildingSite;
    }

    public Long getBuildingSite() 
    {
        return buildingSite;
    }
    public void setBuildingSitePrice(BigDecimal buildingSitePrice) 
    {
        this.buildingSitePrice = buildingSitePrice;
    }

    public BigDecimal getBuildingSitePrice() 
    {
        return buildingSitePrice;
    }
    public void setBuildingSiteWorker(Long buildingSiteWorker) 
    {
        this.buildingSiteWorker = buildingSiteWorker;
    }

    public Long getBuildingSiteWorker() 
    {
        return buildingSiteWorker;
    }
    public void setBuildingSiteWorkerDuty(Long buildingSiteWorkerDuty) 
    {
        this.buildingSiteWorkerDuty = buildingSiteWorkerDuty;
    }

    public Long getBuildingSiteWorkerDuty() 
    {
        return buildingSiteWorkerDuty;
    }
    public void setLiftingEquipment(Long liftingEquipment) 
    {
        this.liftingEquipment = liftingEquipment;
    }

    public Long getLiftingEquipment() 
    {
        return liftingEquipment;
    }
    public void setLiftingEquipmentFiling(Long liftingEquipmentFiling) 
    {
        this.liftingEquipmentFiling = liftingEquipmentFiling;
    }

    public Long getLiftingEquipmentFiling() 
    {
        return liftingEquipmentFiling;
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
            .append("buildingSite", getBuildingSite())
            .append("buildingSitePrice", getBuildingSitePrice())
            .append("buildingSiteWorker", getBuildingSiteWorker())
            .append("buildingSiteWorkerDuty", getBuildingSiteWorkerDuty())
            .append("liftingEquipment", getLiftingEquipment())
            .append("liftingEquipmentFiling", getLiftingEquipmentFiling())
            .append("dataType", getDataType())
            .toString();
    }
}
