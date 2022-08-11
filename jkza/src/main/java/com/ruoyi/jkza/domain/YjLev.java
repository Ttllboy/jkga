package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应急等级对象 yj_lev
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
public class YjLev extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 应急响应等级 */
    @Excel(name = "应急响应等级")
    private Long lev;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLev(Long lev) 
    {
        this.lev = lev;
    }

    public Long getLev() 
    {
        return lev;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("lev", getLev())
            .toString();
    }
}
