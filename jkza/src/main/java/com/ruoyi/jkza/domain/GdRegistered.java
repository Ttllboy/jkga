package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 登记信息对象 gd_registered
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public class GdRegistered extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 工人姓名 */
    @Excel(name = "工人姓名")
    private String workerName;

    /** 工人身份证号 */
    @Excel(name = "工人身份证号")
    private String workerLicense;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 工地ID */
    @Excel(name = "工地ID")
    private Long buildingId;

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
    public void setWorkerName(String workerName) 
    {
        this.workerName = workerName;
    }

    public String getWorkerName() 
    {
        return workerName;
    }
    public void setWorkerLicense(String workerLicense) 
    {
        this.workerLicense = workerLicense;
    }

    public String getWorkerLicense() 
    {
        return workerLicense;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
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
            .append("workerName", getWorkerName())
            .append("workerLicense", getWorkerLicense())
            .append("phone", getPhone())
            .append("buildingId", getBuildingId())
            .append("dataType", getDataType())
            .toString();
    }
}
