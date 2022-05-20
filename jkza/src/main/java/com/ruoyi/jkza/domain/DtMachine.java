package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 起重设备对象 dt_machine
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
public class DtMachine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备安装数量 */
    @Excel(name = "设备安装数量")
    private Long install;

    /** 设备使用数量 */
    @Excel(name = "设备使用数量")
    private Long detection;

    /** 设备拆卸数量 */
    @Excel(name = "设备拆卸数量")
    private Long uninstall;

    public void setInstall(Long install) 
    {
        this.install = install;
    }

    public Long getInstall() 
    {
        return install;
    }
    public void setDetection(Long detection) 
    {
        this.detection = detection;
    }

    public Long getDetection() 
    {
        return detection;
    }
    public void setUninstall(Long uninstall) 
    {
        this.uninstall = uninstall;
    }

    public Long getUninstall() 
    {
        return uninstall;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("install", getInstall())
            .append("detection", getDetection())
            .append("uninstall", getUninstall())
            .append("createTime", getCreateTime())
            .toString();
    }
}
