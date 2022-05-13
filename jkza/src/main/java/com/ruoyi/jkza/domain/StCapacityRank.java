package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 容量排名对象 st_capacity_rank
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class StCapacityRank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 排名 */
    @Excel(name = "排名")
    private Long rank;

    /** 固废点名称 */
    @Excel(name = "固废点名称")
    private String solidName;

    /** 固废点总容量 */
    @Excel(name = "固废点总容量")
    private Long capacity;

    /** 固废点已用量 */
    @Excel(name = "固废点已用量")
    private Long capacityUse;

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
    public void setRank(Long rank) 
    {
        this.rank = rank;
    }

    public Long getRank() 
    {
        return rank;
    }
    public void setSolidName(String solidName) 
    {
        this.solidName = solidName;
    }

    public void setCapacityUse(Long capacityUse)
    {
        this.capacityUse = capacityUse;
    }

    public Long getCapacityUse()
    {
        return capacityUse;
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
            .append("rank", getRank())
            .append("solidName", getSolidName())
            .append("capacity", getCapacity())
            .append("dataType", getDataType())
            .toString();
    }
}
