package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 感知筛选对象 yy_perception_type
 * 
 * @author ruoyi
 * @date 2022-08-01
 */
public class YyPerceptionType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String hosName;

    /** 车辆抓拍图片 */
    @Excel(name = "车辆抓拍图片")
    private String imgUrl;

    /** 抓拍时间 */
    @Excel(name = "抓拍时间")
    private Long capTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setHosName(String hosName) 
    {
        this.hosName = hosName;
    }

    public String getHosName() 
    {
        return hosName;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setCapTime(Long capTime) 
    {
        this.capTime = capTime;
    }

    public Long getCapTime() 
    {
        return capTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("hosName", getHosName())
            .append("imgUrl", getImgUrl())
            .append("capTime", getCapTime())
            .toString();
    }
}
