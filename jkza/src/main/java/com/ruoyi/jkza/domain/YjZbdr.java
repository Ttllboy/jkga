package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 值班导入对象 yj_zbdr
 * 
 * @author ruoyi
 * @date 2022-05-25
 */
public class YjZbdr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 日期 */
    @Excel(name = "日期")
    private String zbDate;

    /** 星期 */
    @Excel(name = "星期")
    private String weekDate;

    /** 带班领导 */
    @Excel(name = "带班领导")
    private String leaderName;

    /** 带班领导电话 */
    @Excel(name = "带班领导电话")
    private String leaderPhone;

    /** 值班长 */
    @Excel(name = "值班长")
    private String chiefName;

    /** 值班长电话 */
    @Excel(name = "值班长电话")
    private String chiefPhone;

    /** 白班一 */
    @Excel(name = "白班一")
    private String dayNameOne;

    /** 白班二 */
    @Excel(name = "白班二")
    private String dayNameTwo;

    /** 夜班一 */
    @Excel(name = "夜班一")
    private String nightNameOne;

    /** 夜班二 */
    @Excel(name = "夜班二")
    private String nightNameTwo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setZbDate(String zbDate) 
    {
        this.zbDate = zbDate;
    }

    public String getZbDate() 
    {
        return zbDate;
    }
    public void setWeekDate(String weekDate) 
    {
        this.weekDate = weekDate;
    }

    public String getWeekDate() 
    {
        return weekDate;
    }
    public void setLeaderName(String leaderName) 
    {
        this.leaderName = leaderName;
    }

    public String getLeaderName() 
    {
        return leaderName;
    }
    public void setLeaderPhone(String leaderPhone) 
    {
        this.leaderPhone = leaderPhone;
    }

    public String getLeaderPhone() 
    {
        return leaderPhone;
    }
    public void setChiefName(String chiefName) 
    {
        this.chiefName = chiefName;
    }

    public String getChiefName() 
    {
        return chiefName;
    }
    public void setChiefPhone(String chiefPhone) 
    {
        this.chiefPhone = chiefPhone;
    }

    public String getChiefPhone() 
    {
        return chiefPhone;
    }
    public void setDayNameOne(String dayNameOne) 
    {
        this.dayNameOne = dayNameOne;
    }

    public String getDayNameOne() 
    {
        return dayNameOne;
    }
    public void setDayNameTwo(String dayNameTwo) 
    {
        this.dayNameTwo = dayNameTwo;
    }

    public String getDayNameTwo() 
    {
        return dayNameTwo;
    }
    public void setNightNameOne(String nightNameOne) 
    {
        this.nightNameOne = nightNameOne;
    }

    public String getNightNameOne() 
    {
        return nightNameOne;
    }
    public void setNightNameTwo(String nightNameTwo) 
    {
        this.nightNameTwo = nightNameTwo;
    }

    public String getNightNameTwo() 
    {
        return nightNameTwo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("zbDate", getZbDate())
            .append("weekDate", getWeekDate())
            .append("leaderName", getLeaderName())
            .append("leaderPhone", getLeaderPhone())
            .append("chiefName", getChiefName())
            .append("chiefPhone", getChiefPhone())
            .append("dayNameOne", getDayNameOne())
            .append("dayNameTwo", getDayNameTwo())
            .append("nightNameOne", getNightNameOne())
            .append("nightNameTwo", getNightNameTwo())
            .toString();
    }
}
