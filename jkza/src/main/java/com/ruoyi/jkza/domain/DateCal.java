package com.ruoyi.jkza.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class DateCal extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer Year;
    private Integer Month;
    private String ProjectGuid;

    public String getProjectGuid() {
        return ProjectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        ProjectGuid = projectGuid;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public Integer getMonth() {
        return Month;
    }

    public void setMonth(Integer month) {
        Month = month;
    }
}
