package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 值班人员对象 yj_operator
 * 
 * @author ruoyi
 * @date 2022-04-26
 */
public class YjOperator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 值班人员类型 */
    @Excel(name = "值班人员类型")
    private String operatorType;

    /** 值班人员名称 */
    @Excel(name = "值班人员名称")
    private String operatorName;

    /** 值班人员电话 */
    @Excel(name = "值班人员电话")
    private Long operatorPhone;

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
    public void setOperatorType(String operatorType) 
    {
        this.operatorType = operatorType;
    }

    public String getOperatorType() 
    {
        return operatorType;
    }
    public void setOperatorName(String operatorName) 
    {
        this.operatorName = operatorName;
    }

    public String getOperatorName() 
    {
        return operatorName;
    }
    public void setOperatorPhone(Long operatorPhone) 
    {
        this.operatorPhone = operatorPhone;
    }

    public Long getOperatorPhone() 
    {
        return operatorPhone;
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
            .append("operatorType", getOperatorType())
            .append("operatorName", getOperatorName())
            .append("operatorPhone", getOperatorPhone())
            .append("dataType", getDataType())
            .toString();
    }
}
