package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 管理人员考勤对象 gd_manager_check
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public class GdManagerCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目经理总人数 */
    @Excel(name = "项目经理总人数")
    private Long projectManagerSum;

    /** 项目经理考勤人数 */
    @Excel(name = "项目经理考勤人数")
    private Long projectManagerCheck;

    /** 安全员总人数 */
    @Excel(name = "安全员总人数")
    private Long safetyWorkerSum;

    /** 安全员考勤人数 */
    @Excel(name = "安全员考勤人数")
    private Long safetyWorkerCheck;

    /** 注册监理总人数 */
    @Excel(name = "注册监理总人数")
    private Long registeredSupervisionSum;

    /** 注册监理考勤人数 */
    @Excel(name = "注册监理考勤人数")
    private Long registeredSupervisionCheck;

    /** 专业监理总人数 */
    @Excel(name = "专业监理总人数")
    private Long professionalSupervisionSum;

    /** 专业监理考勤人数 */
    @Excel(name = "专业监理考勤人数")
    private Long professionalSupervisionCheck;

    /** 所属工地 */
    @Excel(name = "所属工地")
    private Long buildingId;

    /** 数据类型(0:接口数据 1:手动数据) */
    @Excel(name = "数据类型(0:接口数据 1:手动数据)")
    private Integer dataType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProjectManagerSum(Long projectManagerSum) 
    {
        this.projectManagerSum = projectManagerSum;
    }

    public Long getProjectManagerSum() 
    {
        return projectManagerSum;
    }
    public void setProjectManagerCheck(Long projectManagerCheck) 
    {
        this.projectManagerCheck = projectManagerCheck;
    }

    public Long getProjectManagerCheck() 
    {
        return projectManagerCheck;
    }
    public void setSafetyWorkerSum(Long safetyWorkerSum) 
    {
        this.safetyWorkerSum = safetyWorkerSum;
    }

    public Long getSafetyWorkerSum() 
    {
        return safetyWorkerSum;
    }
    public void setSafetyWorkerCheck(Long safetyWorkerCheck) 
    {
        this.safetyWorkerCheck = safetyWorkerCheck;
    }

    public Long getSafetyWorkerCheck() 
    {
        return safetyWorkerCheck;
    }
    public void setBuildingId(Long buildingId)
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId()
    {
        return buildingId;
    }
    public void setRegisteredSupervisionSum(Long registeredSupervisionSum) 
    {
        this.registeredSupervisionSum = registeredSupervisionSum;
    }

    public Long getRegisteredSupervisionSum() 
    {
        return registeredSupervisionSum;
    }
    public void setRegisteredSupervisionCheck(Long registeredSupervisionCheck) 
    {
        this.registeredSupervisionCheck = registeredSupervisionCheck;
    }

    public Long getRegisteredSupervisionCheck() 
    {
        return registeredSupervisionCheck;
    }
    public void setProfessionalSupervisionSum(Long professionalSupervisionSum) 
    {
        this.professionalSupervisionSum = professionalSupervisionSum;
    }

    public Long getProfessionalSupervisionSum() 
    {
        return professionalSupervisionSum;
    }
    public void setProfessionalSupervisionCheck(Long professionalSupervisionCheck) 
    {
        this.professionalSupervisionCheck = professionalSupervisionCheck;
    }

    public Long getProfessionalSupervisionCheck() 
    {
        return professionalSupervisionCheck;
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
            .append("projectManagerSum", getProjectManagerSum())
            .append("projectManagerCheck", getProjectManagerCheck())
            .append("safetyWorkerSum", getSafetyWorkerSum())
            .append("safetyWorkerCheck", getSafetyWorkerCheck())
            .append("registeredSupervisionSum", getRegisteredSupervisionSum())
            .append("registeredSupervisionCheck", getRegisteredSupervisionCheck())
            .append("professionalSupervisionSum", getProfessionalSupervisionSum())
            .append("professionalSupervisionCheck", getProfessionalSupervisionCheck())
            .append("dataType", getDataType())
            .toString();
    }
}
