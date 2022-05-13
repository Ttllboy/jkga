package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预警占比对象 st_warn_proportion
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class StWarnProportion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 总数 */
    @Excel(name = "总数")
    private Long total;

    /** 非法捕捞 */
    @Excel(name = "非法捕捞")
    private Long illegalFish;

    /** 非法倾倒 */
    @Excel(name = "非法倾倒")
    private Long illegalDump;

    /** 重点人员 */
    @Excel(name = "重点人员")
    private Long keyPerson;

    /** 重点车辆 */
    @Excel(name = "重点车辆")
    private Long keyCar;

    /** 非法捕捞比例 */
    @Excel(name = "非法捕捞比例")
    private Double illegalFishProportion;

    /** 非法倾倒比例 */
    @Excel(name = "非法倾倒比例")
    private Double illegalDumpProportion;

    /** 重点人员比例 */
    @Excel(name = "重点人员比例")
    private Double keyPersonProportion;

    /** 重点车辆比例 */
    @Excel(name = "重点车辆比例")
    private Double keyCarProportion;

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
    public void setTotal(Long total) 
    {
        this.total = total;
    }

    public Long getTotal() 
    {
        return total;
    }
    public void setIllegalFish(Long illegalFish) 
    {
        this.illegalFish = illegalFish;
    }

    public Long getIllegalFish() 
    {
        return illegalFish;
    }
    public void setIllegalDump(Long illegalDump) 
    {
        this.illegalDump = illegalDump;
    }

    public Long getIllegalDump() 
    {
        return illegalDump;
    }
    public void setKeyPerson(Long keyPerson) 
    {
        this.keyPerson = keyPerson;
    }

    public Long getKeyPerson() 
    {
        return keyPerson;
    }
    public void setKeyCar(Long keyCar) 
    {
        this.keyCar = keyCar;
    }

    public Long getKeyCar() 
    {
        return keyCar;
    }
    public void setIllegalFishProportion(Double illegalFishProportion)
    {
        this.illegalFishProportion = illegalFishProportion;
    }

    public Double getIllegalFishProportion()
    {
        return illegalFishProportion;
    }
    public void setIllegalDumpProportion(Double illegalDumpProportion)
    {
        this.illegalDumpProportion = illegalDumpProportion;
    }

    public Double getIllegalDumpProportion()
    {
        return illegalDumpProportion;
    }
    public void setKeyPersonProportion(Double keyPersonProportion)
    {
        this.keyPersonProportion = keyPersonProportion;
    }

    public Double getKeyPersonProportion()
    {
        return keyPersonProportion;
    }
    public void setKeyCarProportion(Double keyCarProportion)
    {
        this.keyCarProportion = keyCarProportion;
    }

    public Double getKeyCarProportion()
    {
        return keyCarProportion;
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
            .append("total", getTotal())
            .append("illegalFish", getIllegalFish())
            .append("illegalDump", getIllegalDump())
            .append("keyPerson", getKeyPerson())
            .append("keyCar", getKeyCar())
            .append("illegalFishProportion", getIllegalFishProportion())
            .append("illegalDumpProportion", getIllegalDumpProportion())
            .append("keyPersonProportion", getKeyPersonProportion())
            .append("keyCarProportion", getKeyCarProportion())
            .append("dataType", getDataType())
            .toString();
    }
}
