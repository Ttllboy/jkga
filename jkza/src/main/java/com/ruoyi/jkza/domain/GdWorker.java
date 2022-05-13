package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工人信息对象 gd_worker
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public class GdWorker extends BaseEntity
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


    /** 在岗状态 */
    @Excel(name = "在岗状态")
    private Integer type;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private Integer dataType;

    /** 人员类型 */
    @Excel(name = "人员类型")
    private Integer peopleType;

    public Integer getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(Integer peopleType) {
        this.peopleType = peopleType;
    }

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
    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
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
            .append("buildingId", getBuildingId())
            .append("type", getType())
            .append("dataType", getDataType())
            .toString();
    }
}
