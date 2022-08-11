package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 天气预警对象 yj_tqyj_id
 * 
 * @author ruoyi
 * @date 2022-08-05
 */
public class YjTqyjId extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 预警ID */
    @Excel(name = "预警ID")
    private String tqyjId;

    /** 天气预警数据 */
    @Excel(name = "天气预警数据")
    private String tqyjText;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTqyjId(String tqyjId) 
    {
        this.tqyjId = tqyjId;
    }

    public String getTqyjId() 
    {
        return tqyjId;
    }
    public void setTqyjText(String tqyjText) 
    {
        this.tqyjText = tqyjText;
    }

    public String getTqyjText() 
    {
        return tqyjText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tqyjId", getTqyjId())
            .append("tqyjText", getTqyjText())
            .toString();
    }
}
