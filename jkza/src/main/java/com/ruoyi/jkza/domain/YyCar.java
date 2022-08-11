package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医院车辆对象 yy_car
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public class YyCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private Integer hosName;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carCard;

    /** 姓名 */
    @Excel(name = "姓名")
    private String personName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHosName(Integer hosName) 
    {
        this.hosName = hosName;
    }

    public Integer getHosName() 
    {
        return hosName;
    }
    public void setCarCard(String carCard) 
    {
        this.carCard = carCard;
    }

    public String getCarCard() 
    {
        return carCard;
    }
    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getPersonName() 
    {
        return personName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hosName", getHosName())
            .append("carCard", getCarCard())
            .append("personName", getPersonName())
            .toString();
    }
}
