package com.ruoyi.jkza.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 扬尘数据总对象 gd_dust_data_total
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
public class GdDustDataTotal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 统计时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** pm10 */
    @Excel(name = "pm10")
    private Double pm10;

    /** pm25 */
    @Excel(name = "pm25")
    private Double pm25;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setPm10(Double pm10) 
    {
        this.pm10 = pm10;
    }

    public Double getPm10() 
    {
        return pm10;
    }
    public void setPm25(Double pm25) 
    {
        this.pm25 = pm25;
    }

    public Double getPm25() 
    {
        return pm25;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("date", getDate())
            .append("pm10", getPm10())
            .append("pm25", getPm25())
            .toString();
    }
}
