package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物资信息对象 yj_material
 * 
 * @author ruoyi
 * @date 2022-05-27
 */
public class YjMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 物资库名称 */
    @Excel(name = "物资库名称")
    private String materialStoreName;

    /** 物资等级 */
    @Excel(name = "物资等级")
    private String materialLv;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailAddress;

    /** 所属单位 */
    @Excel(name = "所属单位")
    private String affiliatedUnit;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系人联系方式 */
    @Excel(name = "联系人联系方式")
    private String contactPhone;

    /** 应急物资名字 */
    @Excel(name = "应急物资名字")
    private String emergencyMaterialName;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 应急物资数量 */
    @Excel(name = "应急物资数量")
    private Long emergencyMaterialNum;

    /** 规格 */
    @Excel(name = "规格")
    private String measuringUnit;

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
    public void setMaterialStoreName(String materialStoreName) 
    {
        this.materialStoreName = materialStoreName;
    }

    public String getMaterialStoreName() 
    {
        return materialStoreName;
    }
    public void setMaterialLv(String materialLv) 
    {
        this.materialLv = materialLv;
    }

    public String getMaterialLv() 
    {
        return materialLv;
    }
    public void setDetailAddress(String detailAddress) 
    {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() 
    {
        return detailAddress;
    }
    public void setAffiliatedUnit(String affiliatedUnit) 
    {
        this.affiliatedUnit = affiliatedUnit;
    }

    public String getAffiliatedUnit() 
    {
        return affiliatedUnit;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setEmergencyMaterialName(String emergencyMaterialName) 
    {
        this.emergencyMaterialName = emergencyMaterialName;
    }

    public String getEmergencyMaterialName() 
    {
        return emergencyMaterialName;
    }
    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }
    public void setEmergencyMaterialNum(Long emergencyMaterialNum) 
    {
        this.emergencyMaterialNum = emergencyMaterialNum;
    }

    public Long getEmergencyMaterialNum() 
    {
        return emergencyMaterialNum;
    }
    public void setMeasuringUnit(String measuringUnit) 
    {
        this.measuringUnit = measuringUnit;
    }

    public String getMeasuringUnit() 
    {
        return measuringUnit;
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
            .append("materialStoreName", getMaterialStoreName())
            .append("materialLv", getMaterialLv())
            .append("detailAddress", getDetailAddress())
            .append("affiliatedUnit", getAffiliatedUnit())
            .append("contactName", getContactName())
            .append("contactPhone", getContactPhone())
            .append("emergencyMaterialName", getEmergencyMaterialName())
            .append("specification", getSpecification())
            .append("emergencyMaterialNum", getEmergencyMaterialNum())
            .append("measuringUnit", getMeasuringUnit())
            .append("dataType", getDataType())
            .toString();
    }
}
