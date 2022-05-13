package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物资信息对象 yj_material
 * 
 * @author ruoyi
 * @date 2022-04-17
 */
public class YjMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 应急物资名字 */
    @Excel(name = "应急物资名字")
    private String emergencyMaterialName;

    /** 应急物资数量 */
    @Excel(name = "应急物资数量")
    private Long emergencyMaterialNum;

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
    public void setEmergencyMaterialName(String emergencyMaterialName) 
    {
        this.emergencyMaterialName = emergencyMaterialName;
    }

    public String getEmergencyMaterialName() 
    {
        return emergencyMaterialName;
    }
    public void setEmergencyMaterialNum(Long emergencyMaterialNum) 
    {
        this.emergencyMaterialNum = emergencyMaterialNum;
    }

    public Long getEmergencyMaterialNum() 
    {
        return emergencyMaterialNum;
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
            .append("emergencyMaterialName", getEmergencyMaterialName())
            .append("emergencyMaterialNum", getEmergencyMaterialNum())
            .append("dataType", getDataType())
            .toString();
    }
}
