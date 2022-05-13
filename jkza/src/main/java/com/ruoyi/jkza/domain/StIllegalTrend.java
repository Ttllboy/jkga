package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 非法趋势对象 st_illegal_trend
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public class StIllegalTrend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 非法捕捞数 */
    @Excel(name = "非法捕捞数")
    private Long illegalFish;

    /** 非法倾倒数 */
    @Excel(name = "非法倾倒数")
    private Long illegalDump;

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
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
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
            .append("time", getTime())
            .append("illegalFish", getIllegalFish())
            .append("illegalDump", getIllegalDump())
            .append("dataType", getDataType())
            .toString();
    }
}
