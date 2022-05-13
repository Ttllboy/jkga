package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频列表对象 yj_video_base
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
public class YjVideoBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 视频总数 */
    @Excel(name = "视频总数")
    private Long videoSum;

    /** 视频在线数 */
    @Excel(name = "视频在线数")
    private Long videoDuty;

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
    public void setVideoSum(Long videoSum) 
    {
        this.videoSum = videoSum;
    }

    public Long getVideoSum() 
    {
        return videoSum;
    }
    public void setVideoDuty(Long videoDuty) 
    {
        this.videoDuty = videoDuty;
    }

    public Long getVideoDuty() 
    {
        return videoDuty;
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
            .append("videoSum", getVideoSum())
            .append("videoDuty", getVideoDuty())
            .append("dataType", getDataType())
            .toString();
    }
}
